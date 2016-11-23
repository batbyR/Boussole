package com.batbyR.app.Boussole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/path")
public class AppController {	

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Path findPath (@RequestParam(value="start") String start, @RequestParam(value="end") String end) {
		return new Path(start, end);
	}

}
