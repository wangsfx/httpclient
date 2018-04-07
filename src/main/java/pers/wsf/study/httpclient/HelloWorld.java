package pers.wsf.study.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class HelloWorld {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:application-context.xml");

		RestTemplate restTemplate = context.getBean(RestTemplate.class);
		try {
			String result = restTemplate.getForObject("http://localhost:8080/get", String.class);

		} finally {
			restTemplate.getForObject("http://localhost2:8080/get", String.class);
		}
	}

}
