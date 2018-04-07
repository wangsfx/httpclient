package pers.wsf.study.httpclient.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
		return new RestTemplate(clientHttpRequestFactory);
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Bean
	public HttpClient httpClient(HttpClientConnectionManager httpClientConnectionManager, RequestConfig requestConfig,
			HttpRequestRetryHandler httpRequestRetryHandler) {
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(httpClientConnectionManager);
		httpClientBuilder.setDefaultRequestConfig(requestConfig);
		httpClientBuilder.setRetryHandler(httpRequestRetryHandler);
		return httpClientBuilder.build();
	}

	@Bean(destroyMethod = "close")
	public HttpClientConnectionManager httpClientConnectionManager(@Value("${http.pool.maxTotal}") Integer maxTotal,
			@Value("${http.pool.maxTotal}") Integer defaultMaxPerRoute,
			@Value("${http.pool.validateAfterInactivity}") Integer validateAfterInactivity) {
		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
		httpClientConnectionManager.setMaxTotal(maxTotal);
		httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		httpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivity);
		return httpClientConnectionManager;
	}

	@Bean
	public RequestConfig requestConfig(
			@Value("${http.request.connectionRequestTimeout}") Integer connectionRequestTimeout,
			@Value("${http.request.connectTimeout}") Integer connectTimeout,
			@Value("${http.request.socketTimeout}") Integer socketTimeout) {
		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
		requestConfigBuilder.setConnectTimeout(connectTimeout);
		requestConfigBuilder.setSocketTimeout(socketTimeout);
		return requestConfigBuilder.build();
	}

	@Bean
	public HttpRequestRetryHandler httpRequestRetryHandler(@Value("${http.retry.retryCount}") Integer retryCount,
			@Value("${http.retry.requestSentRetryEnabled}") Boolean requestSentRetryEnabled) {
		return new DefaultHttpRequestRetryHandler(retryCount, requestSentRetryEnabled);
	}

}
