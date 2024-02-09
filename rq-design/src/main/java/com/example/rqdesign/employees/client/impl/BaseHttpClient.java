package com.example.rqdesign.employees.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseHttpClient {

    @Autowired
    public RestTemplate restTemplate;

    private ResponseEntity<Object> exchange(String url, HttpMethod httpMethod, HttpEntity requestEntity, Class responseType, Map<String, String> params) {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseType, params);
        return  responseEntity;
    }

    private ResponseEntity<Object> exchange(RequestEntity requestEntity, Class responseType) {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, responseType);
        return  responseEntity;
    }

    private ResponseEntity<Object> exchange(String url, Object body, HttpMethod httpMethod, Class responseType, Map<String, String> params, Map<String, String> headers) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        URI uri = getUri(url, params);
        return exchange(new RequestEntity<>(body, httpHeaders, httpMethod, uri), responseType);
    }

    public ResponseEntity<Object> callGetHttpClient(String url, Class responseType, Map<String, String> params, Map<String, String> headers) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        URI uri = getUri(url, params);
        return exchange(new RequestEntity<>(null, httpHeaders, HttpMethod.GET, uri), responseType);
    }

    public ResponseEntity<Object> callDeleteHttpClient(String url, Class responseType, Map<String, String> headers) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        URI uri = getUri(url, null);
        return exchange(new RequestEntity<>(null, httpHeaders, HttpMethod.DELETE, uri), responseType);
    }

    public ResponseEntity<Object> callPutHttpClient(String url, Object body, Class responseType, Map<String, String> headers) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        URI uri = getUri(url, null);
        return exchange(new RequestEntity<>(body, httpHeaders, HttpMethod.PUT, uri), responseType);
    }

    public ResponseEntity<Object> callPostHttpClient(String url, Object body, Class responseType, Map<String, String> headers) {
        HttpHeaders httpHeaders = getHttpHeaders(headers);
        URI uri = getUri(url, null);
        return exchange(new RequestEntity<>(body, httpHeaders, HttpMethod.POST, uri), responseType);
    }

    private static HttpHeaders getHttpHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (headers != null) {
            headers.entrySet().forEach(entry -> {
                httpHeaders.add(entry.getKey(), entry.getValue());
            });
        }
        return httpHeaders;
    }

    private URI getUri(String url, Map<String, String> params) {
        try {
            URI uri = new URI(url);

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(
                    params.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> Arrays.asList(e.getValue())))
            );
            UriComponents uriComponents = UriComponentsBuilder.fromUri(uri).queryParams(multiValueMap).build();
            return uriComponents.toUri();

        } catch (URISyntaxException e) {
            System.out.println("Exception in BaseHttpClient");
        }
        return null;
    }
}
