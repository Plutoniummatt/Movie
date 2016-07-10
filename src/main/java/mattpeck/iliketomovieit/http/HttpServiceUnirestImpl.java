package mattpeck.iliketomovieit.http;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * {@link Unirest} implementation of {@link HttpService}
 *
 * @author Matt Peck
 */
public class HttpServiceUnirestImpl implements HttpService {

	@Override
	public String get(final String url, final long timeout) throws HttpException {
		try {
			Unirest.setTimeouts(timeout, timeout);
			return Unirest.get(url).asString().getBody();
		} catch (final UnirestException e) {
			throw new HttpException(e);
		}
	}
}