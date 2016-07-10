package mattpeck.iliketomovieit.movieapi.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
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
public class MovieApiTmdbImpl implements MovieApi {

	private final HttpService httpService;


	@Inject
	public MovieApiTmdbImpl(final HttpService httpService) {
		this.httpService = httpService;
	}


	/**
	 * @see mattpeck.iliketomovieit.movieapi.MovieApi#getMovies(java.lang.String)
	 */
	@Override
	public List<Movie> getMovies(final String title) throws HttpException {
		final String cleansed = title.trim().replaceAll(" +", "+");
		final List<Movie> movies = Lists.newLinkedList();

		final String string = httpService.get("http://api.themoviedb.org/3/search/movie?api_key=6e940a50f408c433af4fea8a64699b5f&query=" + cleansed, 20000);

		final JsonArray searchResults = new JsonParser().parse(string).getAsJsonObject().get("results").getAsJsonArray();

		int count = 0;

		for (final JsonElement result : searchResults) {
			if (count >= 10) {
				System.out.println("Too many results, only displaying first 10, please refine the search term");
				break;
			}

			final String movie = httpService.get("http://api.themoviedb.org/3/movie/" + result.getAsJsonObject().get("id").getAsString() + "?api_key=6e940a50f408c433af4fea8a64699b5f", 20000);
			final String credits = httpService.get("http://api.themoviedb.org/3/movie/" + result.getAsJsonObject().get("id").getAsString() + "/credits?api_key=6e940a50f408c433af4fea8a64699b5f", 20000);

			final JsonObject movieJson = new JsonParser().parse(movie).getAsJsonObject();
			final JsonObject creditsJson = new JsonParser().parse(credits).getAsJsonObject();

			final Optional<JsonElement> director = Iterables.tryFind(creditsJson.get("crew").getAsJsonArray(), crew -> {
				return StringUtils.equals(crew.getAsJsonObject().get("job").getAsString(), "Director");
			});

			movies.add(
				new Movie(
					movieJson.get("imdb_id").getAsString(),
					movieJson.get("original_title").getAsString(),
					movieJson.get("release_date").getAsString().split("-")[0],
					director.isPresent() ? director.get().getAsJsonObject().get("name").getAsString() : "N/A"
				)
			);

			count++;
		};

		return movies;
	}
}