package com.example.demo.controller;

import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@GetMapping("/v1/dateTime")
	public String getDate() {
		return String.valueOf(new Date());
	}
}
