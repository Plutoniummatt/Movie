package mattpeck.iliketomovieit.http;

/**
 * Exception thrown during http method calls via {@link HttpService}
 *
 * @author Matt Peck
 */
public class HttpException extends Exception {
	private static final long serialVersionUID = -3457241812423039590L;

	public HttpException(final Exception e) {
		super(e);
	}
}
