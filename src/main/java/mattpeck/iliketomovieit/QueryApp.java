package mattpeck.iliketomovieit;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Injector;

import mattpeck.iliketomovieit.http.HttpException;
import mattpeck.iliketomovieit.movieapi.Movie;
import mattpeck.iliketomovieit.movieapi.SupportedApis;

/**
 * Main application class
 */
public class QueryApp {

	private final Injector injector;
	private final MoviePrinter moviePrinter;

	@Inject
	QueryApp(final Injector injector, final MoviePrinter moviePrinter) {
		this.injector = injector;
		this.moviePrinter = moviePrinter;
	}


	/**
	 * Runs the query app!
	 */
	public void run(final String title, final String api) throws ValidationException, HttpException {
		// Validate inputs
		if (!validateInput(api, title)) {
			throw new ValidationException();
		};

		// Call through to API
		final List<Movie> movies = injector.getInstance(
			SupportedApis.valueOf(api.toUpperCase()).getImplementation()
		).getMovies(title);

		System.out.println("---------------------- Query Results ----------------------");
		movies.stream().forEach(movie -> {
			moviePrinter.print(movie);
		});

		if (movies.isEmpty()) {
			System.out.println("No movies found matching title: [" + title + "] using API: [" + api + "]");
		}
	}


	/**
	 * Validates the API and Movie input
	 *
	 * @param api
	 * @param movie
	 *
	 * @return whether the inputs are valid
	 */
	private boolean validateInput(final String api, final String movie) {
		if (isBlank(api) || isBlank(movie)) {
			printInstructions();
			return false;
		}

		try {
			SupportedApis.valueOf(api.toUpperCase());
		} catch (final IllegalArgumentException e) {
			System.out.println("Unsupported API: [" + api + "]");
			System.out.println();
			printInstructions();
			return false;
		}

		return true;
	}


	/**
	 * Prints instructions on how to use the program
	 */
	private void printInstructions() {
		System.out.println("To use this tool, specify an API to use, and the name of a movie to search for");
		System.out.println("as JVM system properties 'api' and 'movie' respectively");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Available APIs:");
		for (final SupportedApis supportedApi : SupportedApis.values()) {
			System.out.println("\"" + supportedApi.toString() + "\"" + " - " + supportedApi.getUrl());
		}
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Example usage:");
		System.out.println("java ­Dapi=\"OMDB\" ­Dmovie=\"Indiana Jones\" query.jar");
	}
}
