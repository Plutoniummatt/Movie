package mattpeck.iliketomovieit.movieapi;

import java.util.List;

import mattpeck.iliketomovieit.http.HttpException;

/**
 * Interface representing external API for movie information
 *
 * @author Matt Peck
 */
public interface MovieApi {

	/**
	 * Queries the API and returns a list of {@link Movie} based on the response
	 *
	 * @return The result(s) of the query
	 */
	public List<Movie> getMovies(String title) throws HttpException;
}