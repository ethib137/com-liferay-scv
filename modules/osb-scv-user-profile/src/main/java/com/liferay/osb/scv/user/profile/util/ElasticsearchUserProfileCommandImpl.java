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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.InetAddress;

import java.util.Iterator;
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

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

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

	@Override
	public void add(String id, JSONObject jsonObject) {
		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			_INDEX_NAME, _DOCUMENT_TYPE, id);

		indexRequestBuilder.setRefresh(true);
		indexRequestBuilder.setSource(jsonObject.toString());

		IndexResponse indexResponse = indexRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(indexResponse.toString());
		}
	}

	@Override
	public void delete(String id) {
		DeleteRequestBuilder deleteRequestBuilder = _client.prepareDelete(
			_INDEX_NAME, _DOCUMENT_TYPE, id);

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

		searchRequestBuilder.setTypes(_DOCUMENT_TYPE);

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
	public void deleteField(String field) {
		BulkRequestBuilder bulkRequestBuilder = _client.prepareBulk();

		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch(
			_INDEX_NAME);

		searchRequestBuilder.setTypes(_DOCUMENT_TYPE);

		searchRequestBuilder.setScroll(_SEARCH_SCROLL_KEEP_ALIVE_TIME_VALUE);
		searchRequestBuilder.setSearchType(SearchType.SCAN);

		SearchResponse searchResponse = searchRequestBuilder.get();

		while (true) {
			for (SearchHit searchHit : getSearchHitArray(searchResponse)) {
				IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
					_INDEX_NAME, _DOCUMENT_TYPE, searchHit.getId());

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

	@Override
	public JSONObject getSCVUserProfile(String id) throws Exception {
		GetRequestBuilder getRequestBuilder = _client.prepareGet(
			_INDEX_NAME, _DOCUMENT_TYPE, id);

		GetResponse getResponse = getRequestBuilder.get();

		return JSONFactoryUtil.createJSONObject(
			getResponse.getSourceAsString());
	}

	@Override
	public JSONArray search(JSONObject jsonObject) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch(
			_INDEX_NAME);

		Iterator<String> iterator = jsonObject.keys();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		while (iterator.hasNext()) {
			String key = iterator.next();

			MatchQueryBuilder matchQueryBuilder =
				QueryBuilders.matchPhraseQuery(key, jsonObject.getString(key));

			boolQueryBuilder.must(matchQueryBuilder);
		}

		searchRequestBuilder.setQuery(boolQueryBuilder);

		searchRequestBuilder.setTypes(_DOCUMENT_TYPE);
		searchRequestBuilder.setSize(1);

		SearchResponse searchResponse = searchRequestBuilder.get();

		SearchHit[] searchHitsArray = getSearchHitArray(searchResponse);

		for (int i = 0; i < searchHitsArray.length; i++) {
			SearchHit searchHit = searchHitsArray[i];

			JSONObject searchHitJSONObject = JSONFactoryUtil.createJSONObject();

			searchHitJSONObject.put("id", searchHit.getId());
			searchHitJSONObject.put("source", searchHit.getSourceAsString());

			jsonArray.put(searchHitJSONObject);
		}

		return jsonArray;
	}

	@Override
	public void update(String id, JSONObject jsonObject) {
		UpdateRequestBuilder updateRequestBuilder = _client.prepareUpdate(
			_INDEX_NAME, _DOCUMENT_TYPE, id);

		updateRequestBuilder.setDoc(jsonObject.toString());
		updateRequestBuilder.setRefresh(true);

		UpdateResponse updateResponse = updateRequestBuilder.get();

		if (_log.isDebugEnabled()) {
			_log.debug(updateResponse.toString());
		}
	}

	@Override
	public void update(String id, String field, Object value) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(field, value);

		update(id, jsonObject);
	}

	@Deactivate
	protected void deactivate() {
		_client.close();
	}

	protected SearchHit[] getSearchHitArray(SearchResponse searchResponse) {
		SearchHits searchHits = searchResponse.getHits();

		return searchHits.getHits();
	}

	private static final String _CLUSTER_NAME = "elastictest";

	private static final String _DOCUMENT_TYPE = "SCVUserProfile";

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