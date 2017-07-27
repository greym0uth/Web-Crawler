package edu.neumont.jeb.httpconnect;

import edu.neumont.jeb.webcrawler.MyRegexUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
	private static HttpConnection ourInstance = new HttpConnection();

	public static HttpConnection getInstance() {
		return ourInstance;
	}

	private HttpConnection() {
	}

	public String getSource(String sUrl) {
		String source = "";
		URL url;

		//Convert https to http
		sUrl = new MyRegexUtility().httpsToHttp(sUrl);

		try {
			url = new URL(sUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			try (InputStream in = con.getInputStream()) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
					StringBuilder lines = new StringBuilder();
					while (br.ready()) {
						lines.append(br.readLine()).append("\n");
					}
					source = lines.toString();
					return source;
				}
			}
		} catch (IOException e) {
			return source;
		}
	}
}
