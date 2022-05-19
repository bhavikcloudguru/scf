package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class Consumer {

	@Autowired
	private EurekaClient client;
	@Autowired
	private RestTemplateBuilder builder;

	@RequestMapping("/")
	public String callservice() {

		RestTemplate temp  = builder.build();
		InstanceInfo info = client.getNextServerFromEureka("service", false);
		String baseUrl = info.getHomePageUrl();
		ResponseEntity<String> r = temp.exchange(baseUrl, HttpMethod.GET,null,String.class);
		
		return r.getBody();
	}
}
