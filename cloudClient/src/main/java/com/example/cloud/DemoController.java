package com.example.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Value("${service.instance.name}")
	private String instance;
	
	@RequestMapping("/")
	public String method() {
		return "Hello from "+instance;
	}
}
