package com.payconiq.stocks.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Sonee
 *
 */
@RestController
public class HomeController {

	@Value("${server.port}")
	private String port;
	@Value("${server.address}")
	private String host;

	@RequestMapping("/")
	public void home(HttpServletResponse response) throws IOException {

		response.sendRedirect(new StringBuilder().append("http://").append(host).append(":").append(port)
				.append("/index.html").toString());
	}
}
