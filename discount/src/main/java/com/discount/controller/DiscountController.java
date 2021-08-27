package com.discount.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/discount")
public class DiscountController {
	@RequestMapping("/{price}")
	public double getContact(@PathVariable("price")double price){
		
		if(price>=5000&&price<=10000)
		{
			return 500;
		}
		else if(price>10000&&price<=20000)
		{
			return 1000;
		}
		else
		{
			return 2500;
		}
		
	}
	
}
