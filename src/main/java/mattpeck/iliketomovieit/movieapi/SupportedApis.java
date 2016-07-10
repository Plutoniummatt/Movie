package mattpeck.iliketomovieit.movieapi;

import mattpeck.iliketomovieit.movieapi.impl.MovieApiOmdbImpl;
import mattpeck.iliketomovieit.movieapi.impl.MovieApiTmdbImpl;

/**
 * Represents a supported external movie API
 *
 * @author Matt Peck
 */
public enum SupportedApis {
	OMDB(MovieApiOmdbImpl.class, "http://www.omdbapi.com"),
	TMDB(MovieApiTmdbImpl.class, "http://www.themoviedb.org");

	private SupportedApis(final Class<? extends MovieApi> implementation, final String url) {
		this.implementation = implementation;
		this.url = url;
	}

	/**
	 * Implementation of the API
	 */
	private final Class<? extends MovieApi> implementation;

	/**
	 * Website URL of the API
	 */
	private String url;

	/**
	 * @return the implementation class of this API
	 */
	public Class<? extends MovieApi> getImplementation() {
		return implementation;
	}

	/**
	 * @return The URL to the website of this API
	 */
	public String getUrl() {
		return url;
	}
}
