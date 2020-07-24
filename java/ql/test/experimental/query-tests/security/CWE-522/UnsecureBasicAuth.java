import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Base64;

public class UnsecureBasicAuth {
	/**
	 * Test basic authentication with Apache HTTP POST request.
	 */
	public void testApacheHttpRequest(String username, String password) {
		String host = "www.example.com";
		HttpRequestBase post = new HttpPost("http://"+host+"/rest/getuser.do?uid=abcdx");
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		
		String authString = username + ":" + password;
		byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
		String authStringEnc = new String(authEncBytes);

		post.addHeader("Authorization", "Basic " + authStringEnc);
	}

	/**
	 * Test basic authentication with Apache HTTP GET request.
	 */
	public void testApacheHttpRequest2(String url) throws java.io.IOException {
		url = "http://dashboardHost:dashboardPort/payment/retrieve";
		HttpGet get = new HttpGet(url);
		get.setHeader("Accept", "application/json");
		get.setHeader("Authorization", "Basic " + new String(Base64.getEncoder().encode("admin:test".getBytes())));
	}

	/**
	 * Test basic authentication with Java HTTP URL connection.
	 */
	public void testHttpUrlConnection(String username, String password) {
		String urlStr = "http://www.example.com/rest/getuser.do?uid=abcdx";
		String authString = username + ":" + password;
		String encoding = Base64.getEncoder().encodeToString(authString.getBytes("UTF-8"));
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Authorization", "Basic " + encoding);
	}
}