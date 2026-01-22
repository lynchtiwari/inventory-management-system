package com.accenture.lkm.web.controller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins="http://localhost:3000")
public class HomeController {

	private static Logger LOGGER = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView showHomePage() {
		LOGGER.info("Execution Started [showHomePage()]");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Welcome");

		LOGGER.info("Execution Over [showHomePage()]");
		return modelAndView;
	}
}
