package com.mysite.extraclass.discount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscountController {

	@GetMapping("/discount")
	public String discount() {
		return "discount/discount";
	}
}
