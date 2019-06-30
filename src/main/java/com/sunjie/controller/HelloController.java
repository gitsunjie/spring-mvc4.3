package com.sunjie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String getName() throws Exception{
		return "hello world!";
	}
}
