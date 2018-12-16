package com.tl.job002.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/dynamic")
	public String index(){
		return "bs_basic_test";
	}
}
