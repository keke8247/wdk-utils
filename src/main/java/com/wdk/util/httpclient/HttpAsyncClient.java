package com.wdk.util.httpclient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.wdk.util.date.DateUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpAsyncClient {

	private static final String APPLICATION_FORM = "application/x-www-form-urlencoded";

	private static final Logger log = LoggerFactory
			.getLogger(HttpAsyncClient.class);

	public static void sendJsonData(String url, String parameters,
			String charset) {

		final RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(30000).setSocketTimeout(30000).build();

		// CloseableHttpAsyncClient httpclient =
		// HttpAsyncClients.createDefault();

		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
				.setDefaultRequestConfig(requestConfig).build();

		httpclient.start();

		final CountDownLatch latch = new CountDownLatch(1);

		final HttpPost request = new HttpPost(url);

		request.addHeader(HTTP.CONTENT_TYPE, APPLICATION_FORM);
		request.setHeader("Accept", "application/json");

		request.setEntity(new StringEntity(parameters, Charset.forName(charset)));

		log.info("caller thread id is:" + Thread.currentThread().getId());

		httpclient.execute(request, new FutureCallback<HttpResponse>() {

			public void completed(HttpResponse response) {
				latch.countDown();
				System.out.println(" callback thread id is : "
						+ Thread.currentThread().getId());
				System.out.println(request.getRequestLine() + "->"
						+ response.getStatusLine());
				try {
					String content = EntityUtils.toString(response.getEntity(),
							"UTF-8");
					System.out.println(" response content is : " + content);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void failed(Exception ex) {
				latch.countDown();
				System.out.println(request.getRequestLine() + "->" + ex);
				System.out.println(" callback thread id is : "
						+ Thread.currentThread().getId());
			}

			public void cancelled() {
				latch.countDown();
				System.out.println(request.getRequestLine() + " cancelled");
				System.out.println(" callback thread id is : "
						+ Thread.currentThread().getId());
			}
		});

		try {
			latch.await();
			httpclient.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		Map<String, Object> signMap = new HashMap<String, Object>();

		String roundStr = MD5Util.getRandomString(10);
		signMap.put("client_id", MD5Util.clientID);
		signMap.put("round_str", roundStr);
		signMap.put("token", MD5Util.createToken(roundStr));
		signMap.put("identifier", "A");
		signMap.put("req_id", "209129392113BQ1");
		signMap.put("name", "测试接口");
		signMap.put("sex", 1);
		signMap.put("category", 1);
		signMap.put("card_num", "330104198111142321");
		signMap.put("phone", "15867176651");
		signMap.put("farmers_flag", 2);

		String signStr = MD5Util.mapSort(signMap) + "&app_key="
				+ MD5Util.appKey;
		System.out.println("签名数据明文:" + signStr);

		String sign = MD5Util.md5(signStr);
		System.out.println("签名数据密文:" + sign);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.putAll(signMap);
		dataMap.put("app_key", MD5Util.appKey);
		dataMap.put("education", "大学本科");
		dataMap.put("fax", "91923981");
		dataMap.put("email", "test@yahoo.com");
		dataMap.put("marriage", "未婚");
		dataMap.put("nationality", "中国");
		dataMap.put("birth_date",
				DateUtil.getDate("1981-11-14", DateUtil.DATE_PATTERN));
		dataMap.put("nation", "汉族");
		dataMap.put("address_province", "浙江");
		dataMap.put("address_city", "杭州");
		dataMap.put("address_district", "西湖区");
		dataMap.put("address", "丰潭路天行国际");
		dataMap.put("post_code", "320000");
		dataMap.put("residence", "浙江杭州西湖区");
		dataMap.put("native_place", "浙江");
		dataMap.put("work_unit", "融都科技");
		dataMap.put("post", "融都科技");
		dataMap.put("create_time",
				DateUtil.dateStr(new Date(), DateUtil.DATETIME_PATTERN));
		dataMap.put("update_time",
				DateUtil.dateStr(new Date(), DateUtil.DATETIME_PATTERN));
		dataMap.put("sign", sign);

		sendJsonData(
				"https://183.63.254.107/api/jrjApi/v2/pushPersonalCustomerData",
				MD5Util.mapSort(dataMap), "UTF-8");
	}
}
