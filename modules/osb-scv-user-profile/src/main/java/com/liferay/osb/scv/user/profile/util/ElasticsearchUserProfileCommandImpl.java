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

package com.liferay.osb.scv.user.profile.util;

import com.liferay.osb.scv.user.profile.model.DataSourceEntry;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Matthew Kong
 */
@Component(immediate = true, service = UserProfileCommand.class)
public class ElasticsearchUserProfileCommandImpl implements UserProfileCommand {

	@Activate
	public void activate() {
		TransportClient.Builder transportClientBuilder =
			TransportClient.builder();

		Settings.Builder settingsBuilder = Settings.settingsBuilder();

		settingsBuilder.put("client.transport.sniff", true);
		settingsBuilder.put("cluster.name", _CLUSTER_NAME);

		transportClientBuilder.settings(settingsBuilder.build());

		TransportClient transportClient = transportClientBuilder.build();

		try {
			transportClient.addTransportAddress(
				new InetSocketTransportAddress(
					InetAddress.getByName(_TRANSPORT_ADDRESS),
					_TRANSPORT_ADDRESS_PORT));
		}
		catch (Exception e) {
			_log.error("Unable to initialize elasticsearch");
		}

		_client = transportClient;

		deleteAll();
	}

	@Modified
	protected synchronized void modified() {
		if (_client != null) {
			deactivate();

			activate();
		}
	}

	@Override
	public void add(DataSourceEntry dataSourceEntry, String documentType) {
		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			_INDEX_NAME, documentType, dataSourceEntry.getDataSourceEntryId());

		indexRequestBuilder.setRefresh(true);
		indexRequestBuilder.setSource(dataSourceEntry.getSource());

		IndexResponse indexResponse = indexRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(indexResponse.toString());
		}
	}

	@Override
	public void delete(String dataSourceEntryId, String documentType) {
		DeleteRequestBuilder deleteRequestBuilder = _client.prepareDelete(
			_INDEX_NAME, documentType, dataSourceEntryId);

		DeleteResponse deleteResponse = deleteRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(deleteResponse.toString());
		}
	}

	@Override
	public void deleteAll() {
		BulkRequestBuilder bulkRequestBuilder = _client.prepareBulk();

		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch(
			_INDEX_NAME);

		searchRequestBuilder.setTypes(
			UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE,
			UserProfileConstants.DOCUMENT_TYPE_VERSIONING,
			UserProfileConstants.DOCUMENT_TYPE_ASSOCIATION);

		searchRequestBuilder.setScroll(_SEARCH_SCROLL_KEEP_ALIVE_TIME_VALUE);
		searchRequestBuilder.setSearchType(SearchType.SCAN);

		SearchResponse searchResponse = searchRequestBuilder.get();

		while (true) {
			for (SearchHit searchHit : getSearchHitArray(searchResponse)) {
				DeleteRequestBuilder deleteRequestBuilder =
					_client.prepareDelete();

				deleteRequestBuilder.setIndex(searchHit.getIndex());
				deleteRequestBuilder.setId(searchHit.getId());
				deleteRequestBuilder.setType(searchHit.getType());

				bulkRequestBuilder.add(deleteRequestBuilder);
			}

			SearchScrollRequestBuilder searchScrollRequestBuilder =
				_client.prepareSearchScroll(searchResponse.getScrollId());

			searchScrollRequestBuilder.setScroll(
				_SEARCH_SCROLL_KEEP_ALIVE_TIME_VALUE);

			searchResponse = searchScrollRequestBuilder.get();

			SearchHit[] searchHitsArray = getSearchHitArray(searchResponse);

			if (searchHitsArray.length == 0) {
				break;
			}
		}

		if (bulkRequestBuilder.numberOfActions() == 0) {
			return;
		}

		BulkResponse bulkResponse = bulkRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(bulkResponse.toString());
		}
	}

	@Override
	public void deleteField(String field, String documentType) {
		BulkRequestBuilder bulkRequestBuilder = _client.prepareBulk();

		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch(
			_INDEX_NAME);

		searchRequestBuilder.setTypes(documentType);

		searchRequestBuilder.setScroll(_SEARCH_SCROLL_KEEP_ALIVE_TIME_VALUE);
		searchRequestBuilder.setSearchType(SearchType.SCAN);

		SearchResponse searchResponse = searchRequestBuilder.get();

		while (true) {
			for (SearchHit searchHit : getSearchHitArray(searchResponse)) {
				IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
					_INDEX_NAME, documentType, searchHit.getId());

				Map<String, Object> source = searchHit.getSource();

				source.remove(field);

				indexRequestBuilder.setSource(source);

				bulkRequestBuilder.add(indexRequestBuilder);
			}

			SearchScrollRequestBuilder searchScrollRequestBuilder =
				_client.prepareSearchScroll(searchResponse.getScrollId());

			searchScrollRequestBuilder.setScroll(
				_SEARCH_SCROLL_KEEP_ALIVE_TIME_VALUE);

			searchResponse = searchScrollRequestBuilder.get();

			SearchHit[] searchHitsArray = getSearchHitArray(searchResponse);

			if (searchHitsArray.length == 0) {
				break;
			}
		}

		if (bulkRequestBuilder.numberOfActions() == 0) {
			return;
		}

		BulkResponse bulkResponse = bulkRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(bulkResponse.toString());
		}
	}

	public List<String> search(String field, String documentType) {
		List<String> searchResults = new ArrayList<String>();

		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch();

		searchRequestBuilder.setIndices(_INDEX_NAME);
		searchRequestBuilder.setSize(0);
		searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
		searchRequestBuilder.setTypes(documentType);

		TermsBuilder termsBuilder = AggregationBuilders.terms("ids");

		termsBuilder.field(field);
		termsBuilder.size(0);

		searchRequestBuilder.addAggregation(termsBuilder);

		SearchResponse searchResponse = searchRequestBuilder.get();

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("ids");

		List<Terms.Bucket> buckets = terms.getBuckets();

		for (int i = 0; i < buckets.size(); i++) {
			Terms.Bucket bucket = buckets.get(i);

			searchResults.add((String)bucket.getKey());
		}

		return searchResults;
	}

	@Override
	public DataSourceEntry getDataSourceEntry(
		String dataSourceEntryId, String documentType) {

		GetRequestBuilder getRequestBuilder = _client.prepareGet(
			_INDEX_NAME, documentType, dataSourceEntryId);

		GetResponse getResponse = getRequestBuilder.get();

		return new DataSourceEntry(
			getResponse.getId(), getResponse.getSourceAsString());
	}

	@Override
	public List<DataSourceEntry> search(
		JSONObject jsonObject, String documentType) {

		List<DataSourceEntry> dataSourceEntries = new ArrayList<>();

		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch(
			_INDEX_NAME);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			MatchQueryBuilder matchQueryBuilder =
				QueryBuilders.matchPhraseQuery(key, jsonObject.getString(key));

			boolQueryBuilder.must(matchQueryBuilder);
		}

		searchRequestBuilder.setQuery(boolQueryBuilder);

		searchRequestBuilder.setTypes(documentType);

		SearchResponse searchResponse = searchRequestBuilder.get();

		SearchHit[] searchHitsArray = getSearchHitArray(searchResponse);

		for (int i = 0; i < searchHitsArray.length; i++) {
			SearchHit searchHit = searchHitsArray[i];

			dataSourceEntries.add(
				new DataSourceEntry(
					searchHit.getId(), searchHit.getSourceAsString()));
		}

		return dataSourceEntries;
	}

	@Override
	public void update(DataSourceEntry dataSourceEntry, String documentType) {
		UpdateRequestBuilder updateRequestBuilder = _client.prepareUpdate(
			_INDEX_NAME, documentType, dataSourceEntry.getDataSourceEntryId());

		updateRequestBuilder.setDoc(dataSourceEntry.getSource());
		updateRequestBuilder.setRefresh(true);

		UpdateResponse updateResponse = updateRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(updateResponse.toString());
		}
	}

	@Override
	public void update(
		String dataSourceEntryId, String field, Object value,
		String documentType) {

		DataSourceEntry dataSourceEntry = new DataSourceEntry(
			dataSourceEntryId);

		dataSourceEntry.addProperty(field, value);

		update(dataSourceEntry, documentType);
	}

	@Deactivate
	protected void deactivate() {
		_client.close();

		_client = null;
	}

	protected SearchHit[] getSearchHitArray(SearchResponse searchResponse) {
		SearchHits searchHits = searchResponse.getHits();

		return searchHits.getHits();
	}

	private static final String _CLUSTER_NAME = "elastictest";

	private static final String _INDEX_NAME = "scv";

	private static final TimeValue _SEARCH_SCROLL_KEEP_ALIVE_TIME_VALUE =
		new TimeValue(1, TimeUnit.MINUTES);

	private static final String _TRANSPORT_ADDRESS =
		"elastic-cluster-1.lax.liferay.com";

	private static final int _TRANSPORT_ADDRESS_PORT = 9300;

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchUserProfileCommandImpl.class);

	private Client _client;

}