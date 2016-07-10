package mattpeck.iliketomovieit.movieapi;

import org.junit.Assert;
import org.junit.Test;

import mattpeck.iliketomovieit.movieapi.Movie;

/**
 * Test class for {@link Movie}
 *
 * @author Matt Peck
 */
public class TestMovie {

	/**
	 * Tests the method {@link Movie#toString()}
	 */
	@Test
	public void testToString() {
		final Movie movie = new Movie("AA123456", "That Awesome Test Movie", "2016", "Solid Snake");
		Assert.assertEquals("Title: That Awesome Test Movie, Year: 2016, Director: Solid Snake", movie.toString());
	}
}
