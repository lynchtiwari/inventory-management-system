package com.accenture.lkm.web.controller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class HomeController {

	private static Logger LOGGER = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	
	public String showHomePage() {
		LOGGER.info("Execution Started [showHomePage()]");


		LOGGER.info("Execution Over [showHomePage()]");
		return "Inventory Management System running";
	}
}
