package cob.com.communication.handler;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.netty.handler.codec.http.HttpHeaders;

public class HandshakeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1149698313482442333L;

	private HttpHeaders headers;
	private InetSocketAddress address;
	private Date time = new Date();
	private InetSocketAddress local;
	private String url;
	private Map<String, List<String>> urlParams;

	// needed for correct deserialization
	public HandshakeData() {
	}

	public HandshakeData(HttpHeaders headers, Map<String, List<String>> urlParams, InetSocketAddress address,
			String url) {
		this(headers, urlParams, address, null, url);
	}

	public HandshakeData(HttpHeaders headers, Map<String, List<String>> urlParams, InetSocketAddress address,
			InetSocketAddress local, String url) {
		super();
		this.headers = headers;
		this.urlParams = urlParams;
		this.address = address;
		this.local = local;
		this.url = url;
	}

	/**
	 * Client network address
	 *
	 * @return network address
	 */
	public InetSocketAddress getAddress() {
		return address;
	}

	/**
	 * Connection local address
	 *
	 * @return local address
	 */
	public InetSocketAddress getLocal() {
		return local;
	}

	/**
	 * Http headers sent during first client request
	 *
	 * @return headers
	 */
	public HttpHeaders getHttpHeaders() {
		return headers;
	}

	/**
	 * Client connection date
	 *
	 * @return date
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Url used by client during first request
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Url params stored in url used by client during first request
	 *
	 * @return map
	 */
	public Map<String, List<String>> getUrlParams() {
		return urlParams;
	}

	public String getSingleUrlParam(String name) {
		List<String> values = urlParams.get(name);
		if (values != null && values.size() == 1) {
			return values.iterator().next();
		}
		return null;
	}
}