package mattpeck.iliketomovieit.movieapi.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;

import mattpeck.iliketomovieit.http.HttpException;
import mattpeck.iliketomovieit.http.HttpService;
import mattpeck.iliketomovieit.movieapi.Movie;
import mattpeck.iliketomovieit.movieapi.MovieApi;

/**
 * OMDB implementation of {@link MovieApi}
 *
 * @author Matt Peck
 */
public class MovieApiOmdbImpl implements MovieApi {

	private final HttpService httpService;


	@Inject
	public MovieApiOmdbImpl(final HttpService httpService) {
		this.httpService = httpService;
	}


	/**
	 * @see mattpeck.iliketomovieit.movieapi.MovieApi#getMovies(java.lang.String)
	 */
	@Override
	public List<Movie> getMovies(final String title) throws HttpException {
		final String cleansed = title.trim().replaceAll(" +", "+");
		final List<Movie> movies = Lists.newLinkedList();

		final String string = httpService.get("http://www.omdbapi.com/?s=" + cleansed + "&y=&plot=short&r=json&type=movie", 20000);

		final JsonArray searchResults = new JsonParser().parse(string).getAsJsonObject().get("Search").getAsJsonArray();

		int i = 0;
		for (final JsonElement result : searchResults) {
			if (i >= 10) {
				System.out.println("Too many results, only displaying first 10, please refine the search term");
				break;
			}

			final String response = httpService.get("http://www.omdbapi.com/?i=" + result.getAsJsonObject().get("imdbID").getAsString(), 20000);

			final JsonObject responseJsonObject = new JsonParser().parse(response).getAsJsonObject();

			movies.add(
				new Movie(
					responseJsonObject.get("imdbID").getAsString(),
					responseJsonObject.get("Title").getAsString(),
					responseJsonObject.get("Year").getAsString(),
					responseJsonObject.get("Director").getAsString()
				)
			);

			i++;
		};

		return movies;
	}
}