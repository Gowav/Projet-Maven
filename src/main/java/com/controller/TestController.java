package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/test")
public class TestController {
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public String get(@RequestParam(required=false, value="value")String value) {
		System.out.println("Appel GET");
		System.out.println("value : "+value);
		
		return value;
	}
}
