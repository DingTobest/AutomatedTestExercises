package testcase;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SingleTest {
	@Test
	public void doGetTest() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			String url = "http://127.0.0.1:8000/stock/get_index_analysis_info";

			HttpGet request = new HttpGet();
			URIBuilder getURIBuilder;
			getURIBuilder = new URIBuilder(url);
			getURIBuilder.setParameter("index_code", "qzzs.sc");
			URI getURI = getURIBuilder.build();
			request.setURI(getURI);

			response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println("返回结果");
				System.out.println(content);
			}
		} 
		finally {
			if (response != null) {
				response.close();
			}
			httpClient.close();
		}
	}
}
