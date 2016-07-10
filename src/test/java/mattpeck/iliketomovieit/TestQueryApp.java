package mattpeck.iliketomovieit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

import mattpeck.iliketomovieit.MoviePrinter;
import mattpeck.iliketomovieit.QueryApp;
import mattpeck.iliketomovieit.ValidationException;
import mattpeck.iliketomovieit.http.HttpException;
import mattpeck.iliketomovieit.movieapi.Movie;
import mattpeck.iliketomovieit.movieapi.impl.MovieApiOmdbImpl;

/**
 * Test class for {@link QueryApp}
 *
 * @author Matt Peck
 */
public class TestQueryApp {

	@Mock
	private Injector mockInjector;

	@Mock
	private MovieApiOmdbImpl mockHttpService;

	@Mock
	private MoviePrinter moviePrinter;

	private QueryApp toTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		toTest = new QueryApp(mockInjector, moviePrinter);
	}


	/**
	 * Tests that a {@link ValidationException} is thrown if attempting to use
	 * an unsupported API
	 */
	@Test(expected = ValidationException.class)
	public void testUnsupportedApi() throws ValidationException, HttpException {
		toTest.run("That Movie", "WHAT");
	}


	/**
	 * Expect {@link ValidationException} if a blank movie is provided
	 */
	@Test(expected = ValidationException.class)
	public void testBlankMovieInput() throws ValidationException, HttpException {
		toTest.run("", "WHAT");
	}


	/**
	 * Expect {@link ValidationException} if a blank API is provided
	 */
	@Test(expected = ValidationException.class)
	public void testBlankApiInput() throws ValidationException, HttpException {
		toTest.run("That Movie", "");
	}


	/**
	 * Tests that a {@link HttpException} is thrown if caught
	 */
	@Test(expected = HttpException.class)
	public void testHttpExceptionThrown() throws ValidationException, HttpException {
		when(mockInjector.getInstance(MovieApiOmdbImpl.class)).thenReturn(mockHttpService);
		when(mockHttpService.getMovies("That Movie")).thenThrow(new HttpException(null));
		toTest.run("That Movie", "OMDB");
	}


	/**
	 * Tests movies are printed as expected if no errors occur
	 */
	@Test()
	public void testMoviesPrinted() throws ValidationException, HttpException {
		// Given
		when(mockInjector.getInstance(MovieApiOmdbImpl.class)).thenReturn(mockHttpService);

		final Movie movie1 = new Movie("1234", "That Movie: The Movie", "2016", "Aidan Owens");
		final Movie movie2 = new Movie("1235", "That Movie 2: Nope", "2016", "Edd Yardy");
		when(mockHttpService.getMovies("That Movie")).thenReturn(
			Lists.newArrayList(
				movie1,
				movie2
			)
		);

		// When
		toTest.run("That Movie", "OMDB");

		// Then verify movies is printed
		verify(moviePrinter, times(1)).print(movie1);
		verify(moviePrinter, times(1)).print(movie2);
	}
}