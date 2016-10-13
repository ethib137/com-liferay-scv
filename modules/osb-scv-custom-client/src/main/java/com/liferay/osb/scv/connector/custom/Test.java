/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.scv.connector.custom;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shinn Lok
 */
public class Test {

	public static void main(String[] args) throws Exception {
		setup();

		importSql("create.sql");

		String path = System.getProperty("user.home") + "/.scvtest";

		System.out.println(Paths.get(path).toString());

		long mappingDataSourceId = 0;

		if (Files.exists(Paths.get(path))) {
			importCsv("job2.csv");

			List<String> strings = Files.readAllLines(Paths.get(path));

			mappingDataSourceId = Long.valueOf(strings.get(0));
		}
		else {
			importCsv("job.csv");

			mappingDataSourceId = addDataSource();

			Path path1 = Paths.get(path);
			FileWriter fw = new FileWriter(path1.toFile().getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.valueOf(mappingDataSourceId));
			bw.close();
			addUserMappingRules(mappingDataSourceId);
		}

		addData(mappingDataSourceId);
	}

	protected static void addUserMappingRules(long mappingDataSourceId)
		throws Exception {

		for (Map.Entry<String, Map<String, String>> entry :
				_tableMap.entrySet()) {

			String tableName = entry.getKey();
			Map<String, String> fields = entry.getValue();

			for (String field : fields.keySet()) {
				addUserMappingRule(
					mappingDataSourceId, tableName, field, splitCamelCase(field));
			}
		}
	}

	protected static String splitCamelCase(String s) {
		s = s.replaceAll(
				String.format("%s|%s|%s",
						"(?<=[A-Z])(?=[A-Z][a-z])",
						"(?<=[^A-Z])(?=[A-Z])",
						"(?<=[A-Za-z])(?=[^A-Za-z])"
				),
				" "
			);

		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	protected static void addUserMappingRule(
			long mappingDataSourceId, String tableName, String sourceField,
			String destinationField)
		throws Exception {

		HttpPost httpPost = new HttpPost(
			"http://localhost:8080/api/jsonws/Cloud.cloud/add-user-mapping-rule/");

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("mappingDataSourceId", mappingDataSourceId);
		parameters.put("modelName", tableName);
		parameters.put("sourceField", sourceField);
		parameters.put("destinationField", destinationField);

		HttpEntity urlEncodedFormEntity = getURLEncodedFormEntity(parameters);

		httpPost.setEntity(urlEncodedFormEntity);

		HttpResponse execute = getHttpClient().execute(httpPost);

		EntityUtils.consume(execute.getEntity());
	}

	protected static void addData(long mappingDataSourceId) throws Exception {
		HttpPost httpPost = new HttpPost(
			"http://localhost:8080/api/jsonws/Cloud.cloud/add-data/");

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("mappingDataSourceId", mappingDataSourceId);

		Statement statement = _connection.createStatement();

		Map<String, List<Map<String, String>>> fieldMap = new HashMap<>();

		getAvailableFields();

		for (Map.Entry<String, Map<String, String>> entry :
				_tableMap.entrySet()) {

			List<Map<String, String>> objects = new ArrayList<>();

			Map<String, String> value = entry.getValue();

			ResultSet resultSet = statement.executeQuery(
				"select * from " + entry.getKey());

			while (resultSet.next()) {
				Map<String, String> map = new HashMap<>();
				Set<String> keySet = value.keySet();
				for (String key : keySet) {
					map.put(key, resultSet.getString(key));
				}

				objects.add(map);
			}

			fieldMap.put(entry.getKey(), objects);
		}

		Gson gson = new Gson();

		parameters.put("json", gson.toJson(fieldMap));

		HttpEntity httpEntity = getURLEncodedFormEntity(parameters);

		httpPost.setEntity(httpEntity);

		HttpResponse execute = getHttpClient().execute(httpPost);

		EntityUtils.consume(execute.getEntity());
	}

	protected static long addDataSource() throws Exception {
		HttpPost httpPost = new HttpPost(
			"http://localhost:8080/api/jsonws/Cloud.cloud/add-data-source/");

		Map<String, Object> map = new HashMap<>();

		map.put("name", "Custom Data Source #9");
		map.put("availableFields", getAvailableFields());

		HttpEntity httpEntity = getURLEncodedFormEntity(map);

		httpPost.setEntity(httpEntity);

		HttpResponse response = getHttpClient().execute(httpPost);

		HttpEntity entity = response.getEntity();

		String mappingDataSourceId = EntityUtils.toString(entity);

		return Long.parseLong(mappingDataSourceId.replaceAll("\"", ""));
	}

	protected static String getAvailableFields()
		throws Exception{

		File tempFile = createTempFile("create.sql");

		String content = new String(Files.readAllBytes(tempFile.toPath()));

		Pattern pattern = Pattern.compile("create table (.*) \\(");

		Matcher matcher = pattern.matcher(content);

		String tableName = null;

		if (matcher.find()) {
			tableName = matcher.group(1);
		}

		Pattern pattern2 = Pattern.compile("([^\\s]+ [^\\s]+),");

		Matcher matcher2 = pattern2.matcher(content);

		Map<String, String> fieldMap = new HashMap<>();

		while (matcher2.find()) {
			String field = matcher2.group(1);

			String[] split = field.split(" ");

			String type = null;

			if (split[1].startsWith("VARCHAR")) {
				type = "String";
			}

			fieldMap.put(split[0], type);
		}

		_tableMap.put(tableName, fieldMap);

		Gson gson = new Gson();

		return gson.toJson(_tableMap);
	}

	protected static HttpEntity getURLEncodedFormEntity(
			Map<String, Object> parameters)
		throws Exception {

		List<NameValuePair> nameValuePairs = new ArrayList<>();

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			NameValuePair nameValuePair = new BasicNameValuePair(
				entry.getKey(), String.valueOf(entry.getValue()));

			nameValuePairs.add(nameValuePair);
		}

		return new UrlEncodedFormEntity(nameValuePairs);
	}

	protected static HttpClient getHttpClient() {
		if (_httpClient != null) {
			return _httpClient;
		}

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		CredentialsProvider provider = new BasicCredentialsProvider();

		provider.setCredentials(
			new AuthScope(
				AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM),
			new UsernamePasswordCredentials("test@liferay.com", "test"));

		httpClientBuilder.setDefaultCredentialsProvider(provider);

		_httpClient = httpClientBuilder.build();

		return _httpClient;
	}

	protected static void setup() throws Exception {
		_connection = getConnection();

		try {
			Statement statement = _connection.createStatement();

			statement.executeUpdate("truncate People");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static File createTempFile(String fileName) throws Exception {
		File tempFile = File.createTempFile("scv", "scv");

		Class<?> clazz = Test.class;

		InputStream inputStream = clazz.getResourceAsStream(fileName);

		Files.copy(
			inputStream, tempFile.toPath(),
			StandardCopyOption.REPLACE_EXISTING);

		return tempFile;
	}

	protected static void importCsv(String fileName) throws Exception {
		File tempFile = createTempFile(fileName);

		Statement statement = getConnection().createStatement();

		String s =
			"LOAD DATA LOCAL INFILE '" + tempFile.getAbsolutePath() +"' INTO TABLE people " +
			"FIELDS TERMINATED BY ',' enclosed by '\"' lines terminated by '\n'(firstName,lastName,emailAddress,jobTitle,salary,expertise,company)";

		statement.executeUpdate(s);

		tempFile.delete();
	}

	protected static void importSql(String fileName) throws Exception {
		File tempFile = createTempFile(fileName);

		Runtime runtime = Runtime.getRuntime();

		Process pr = runtime.exec(
			new String[]{
				"/bin/bash",
				"-c",
				"mysql -u root --force testscv < " +
					tempFile.getAbsolutePath() + ";"});

		pr.waitFor();

		tempFile.delete();
	}

	protected static Connection getConnection() throws Exception {
		Connection connection = null;

		String databaseName = "testscv";

		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/" + databaseName +
					"?serverTimezone=UTC", "root", null);

			return connection;
		}
		catch (Exception e) {
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306?serverTimezone=UTC", "root", null);

			Statement statement = connection.createStatement();

			statement.executeUpdate(
				"CREATE DATABASE " + databaseName + " charset utf8");

			connection.close();

			return getConnection();
		}
	}

	protected static Map<String, Map<String, String>> _tableMap =
		new HashMap<>();
	private static HttpClient _httpClient;
	private static Connection _connection;

	protected static List<String> syncFields = Arrays.asList("emailAddress", "companyName", "jobTitle", "salary");

}