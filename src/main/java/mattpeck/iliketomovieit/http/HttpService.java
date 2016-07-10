package mattpeck.iliketomovieit.http;

import com.google.inject.ImplementedBy;

/**
 * HTTP Service interface, exposes HTTP methods
 *
 * @author Matt Peck
 */
@ImplementedBy(HttpServiceUnirestImpl.class)
public interface HttpService {

	/**
	 * Performs a GET request to the specified URL, returning the response body as a String
	 *
	 * @param url target url
	 * @return the response body as a String
	 * @throws HttpException
	 */
	public String get(String url, long timeout) throws HttpException;
}
