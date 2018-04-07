package pers.wsf.study.httpclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

	private Logger logger = LoggerFactory.getLogger(QueryService.class);

	public void query() {
		logger.info("Querying...");
	}
}
