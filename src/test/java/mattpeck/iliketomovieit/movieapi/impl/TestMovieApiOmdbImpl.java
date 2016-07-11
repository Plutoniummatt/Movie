package mattpeck.iliketomovieit.movieapi.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mattpeck.iliketomovieit.http.HttpException;
import mattpeck.iliketomovieit.http.HttpService;
import mattpeck.iliketomovieit.movieapi.Movie;

/**
 * Test class for {@link MovieApiOmdbImpl}
 *
 * @author Matt Peck
 */
public class TestMovieApiOmdbImpl {

	@Mock
	private HttpService mockHttpService;

	private MovieApiOmdbImpl toTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		toTest = new MovieApiOmdbImpl(mockHttpService);
	}


	/**
	 * Tests the method {@link MovieApiOmdbImpl#getMovies(String)}, assuming everything goes smoothly
	 */
	@Test
	public void testMoviesReturned() throws HttpException {
		when(mockHttpService.get("http://www.omdbapi.com/?s=That+Amazing+Test+Movie&y=&plot=short&r=json&type=movie", 20000L)).thenReturn(
			"{\"Search\":[{\"Title\":\"Empire of the Sun\",\"Year\":\"1987\",\"imdbID\":\"tt0092965\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTI1Nzk0MjI5Ml5BMl5BanBnXkFtZTYwMDc1NDc5._V1_SX300.jpg\"},{\"Title\":\"The China Odyssey: 'Empire of the Sun', a Film by Steven Spielberg\",\"Year\":\"1987\",\"imdbID\":\"tt0258494\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BNmFjMjUxMDMtYTVkYS00OWY3LWE0MjAtMzkzNzE2MDY0Mjc1XkEyXkFqcGdeQXVyNDk0MDg4NDk@._V1_SX300.jpg\"},{\"Title\":\"Empire of the Sun: Alive\",\"Year\":\"2013\",\"imdbID\":\"tt5311112\",\"Type\":\"movie\",\"Poster\":\"N/A\"}],\"totalResults\":\"3\",\"Response\":\"True\"}"
		);

		when(mockHttpService.get("http://www.omdbapi.com/?i=tt0092965", 20000L)).thenReturn("{\"Title\":\"Empire of the Sun\",\"Year\":\"1987\",\"Rated\":\"PG\",\"Released\":\"25 Dec 1987\",\"Runtime\":\"153 min\",\"Genre\":\"Drama, History, War\",\"Director\":\"Steven Spielberg\",\"Writer\":\"J.G. Ballard (novel), Tom Stoppard (screenplay)\",\"Actors\":\"Christian Bale, John Malkovich, Miranda Richardson, Nigel Havers\",\"Plot\":\"A young English boy struggles to survive under Japanese occupation during World War II.\",\"Language\":\"English, Japanese, Mandarin\",\"Country\":\"USA\",\"Awards\":\"Nominated for 6 Oscars. Another 12 wins & 10 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTI1Nzk0MjI5Ml5BMl5BanBnXkFtZTYwMDc1NDc5._V1_SX300.jpg\",\"Metascore\":\"62\",\"imdbRating\":\"7.8\",\"imdbVotes\":\"88,884\",\"imdbID\":\"tt0092965\",\"Type\":\"movie\",\"Response\":\"True\"}");
		when(mockHttpService.get("http://www.omdbapi.com/?i=tt0258494", 20000L)).thenReturn("{\"Title\":\"The China Odyssey: 'Empire of the Sun', a Film by Steven Spielberg\",\"Year\":\"1987\",\"Rated\":\"N/A\",\"Released\":\"30 Dec 1987\",\"Runtime\":\"49 min\",\"Genre\":\"Documentary\",\"Director\":\"Les Mayfield\",\"Writer\":\"William Rus\",\"Actors\":\"Martin Sheen, Steven Spielberg, J.G. Ballard, Christian Bale\",\"Plot\":\"A documentary about the making of director Steven Spielberg's 1987 film \\\"Empire of the Sun.\\\"\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"N/A\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BNmFjMjUxMDMtYTVkYS00OWY3LWE0MjAtMzkzNzE2MDY0Mjc1XkEyXkFqcGdeQXVyNDk0MDg4NDk@._V1_SX300.jpg\",\"Metascore\":\"N/A\",\"imdbRating\":\"6.7\",\"imdbVotes\":\"178\",\"imdbID\":\"tt0258494\",\"Type\":\"movie\",\"Response\":\"True\"}");
		when(mockHttpService.get("http://www.omdbapi.com/?i=tt5311112", 20000L)).thenReturn("{\"Title\":\"Empire of the Sun: Alive\",\"Year\":\"2013\",\"Rated\":\"N/A\",\"Released\":\"29 Apr 2013\",\"Runtime\":\"4 min\",\"Genre\":\"Short, Music\",\"Director\":\"Charles Scott IV, Alex Theurer\",\"Writer\":\"J.D. Dillard, Charles Scott IV, Alex Theurer\",\"Actors\":\"N/A\",\"Plot\":\"N/A\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"N/A\",\"Poster\":\"N/A\",\"Metascore\":\"N/A\",\"imdbRating\":\"N/A\",\"imdbVotes\":\"N/A\",\"imdbID\":\"tt5311112\",\"Type\":\"movie\",\"Response\":\"True\"}");

		final List<Movie> movies = toTest.getMovies("That Amazing Test Movie");

		assertEquals(3, movies.size());

		movies.sort((m1, m2) -> {
			return m1.getUniqueId().compareTo(m2.getUniqueId());
		});

		assertEquals("1987", movies.get(0).getYear());
		assertEquals("Steven Spielberg", movies.get(0).getDirector());
		assertEquals("Empire of the Sun", movies.get(0).getTitle());
		assertEquals("tt0092965", movies.get(0).getUniqueId());

		assertEquals("1987", movies.get(1).getYear());
		assertEquals("Les Mayfield", movies.get(1).getDirector());
		assertEquals("The China Odyssey: 'Empire of the Sun', a Film by Steven Spielberg", movies.get(1).getTitle());
		assertEquals("tt0258494", movies.get(1).getUniqueId());

		assertEquals("2013", movies.get(2).getYear());
		assertEquals("Charles Scott IV, Alex Theurer", movies.get(2).getDirector());
		assertEquals("Empire of the Sun: Alive", movies.get(2).getTitle());
		assertEquals("tt5311112", movies.get(2).getUniqueId());
	}


	/**
	 * Tests that if the movie is not found, an empty movie list is returned
	 */
	@Test
	public void testMovieNotFound() throws HttpException {
		when(mockHttpService.get(eq("http://www.omdbapi.com/?s=NOPE&y=&plot=short&r=json&type=movie"), anyLong())).thenReturn("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}");

		assertEquals(0, toTest.getMovies("NOPE").size());
	}


	/**
	 * Tests that if an {@link HttpException} is caught when calling
	 * {@link MovieApiOmdbImpl#getMovies(String)}, then the exception is thrown
	 */
	@Test(expected = HttpException.class)
	public void testCaughtHttpExceptionThrown() throws HttpException {
		when(mockHttpService.get(anyString(), anyLong())).thenThrow(new HttpException(null));

		toTest.getMovies("That Amazing Test Movie");
	}
}