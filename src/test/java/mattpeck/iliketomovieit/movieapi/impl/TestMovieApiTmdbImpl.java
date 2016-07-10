package mattpeck.iliketomovieit.movieapi.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import mattpeck.iliketomovieit.http.HttpException;
import mattpeck.iliketomovieit.http.HttpService;
import mattpeck.iliketomovieit.movieapi.Movie;
import mattpeck.iliketomovieit.movieapi.impl.MovieApiOmdbImpl;
import mattpeck.iliketomovieit.movieapi.impl.MovieApiTmdbImpl;

/**
 * Test class for {@link MovieApiTmdbImpl}
 *
 * @author Matt Peck
 */
public class TestMovieApiTmdbImpl {

	@Mock
	private HttpService mockHttpService;

	private MovieApiTmdbImpl toTest;

	@Before
	public void setup() {
		initMocks(this);

		toTest = new MovieApiTmdbImpl(mockHttpService);
	}


	/**
	 * Tests the Movie model is created correctly given correct API input/output
	 */
	@Test
	public void testMoviesReturned() throws Exception {
		// Given
		when(mockHttpService.get("http://api.themoviedb.org/3/search/movie?api_key=6e940a50f408c433af4fea8a64699b5f&query=Empire+of+the+Sun", 20000L))
		.thenReturn("{\"page\":1,\"results\":[{\"poster_path\":\"/mD5BkLNSPXS8GZDB0VRRrI7YV5V.jpg\",\"adult\":false,\"overview\":\"The story of young English boy who lives with his parents in Shanghai during World War II. After the Pearl Harbor attack, the Japanese occupy the Shanghai International Settlement, and in the following chaos Jim becomes separated from his parents.\",\"release_date\":\"1987-12-25\",\"genre_ids\":[18,36,10752],\"id\":10110,\"original_title\":\"Empire of the Sun\",\"original_language\":\"en\",\"title\":\"Empire of the Sun\",\"backdrop_path\":\"/qfBRuSHMNHR19HkCVdwiBeaXXt1.jpg\",\"popularity\":2.03708,\"vote_count\":251,\"video\":false,\"vote_average\":6.92}],\"total_results\":1,\"total_pages\":1}");

		when(mockHttpService.get("http://api.themoviedb.org/3/movie/10110?api_key=6e940a50f408c433af4fea8a64699b5f", 20000L))
		.thenReturn("{\"adult\":false,\"backdrop_path\":\"/qfBRuSHMNHR19HkCVdwiBeaXXt1.jpg\",\"belongs_to_collection\":null,\"budget\":38000000,\"genres\":[{\"id\":18,\"name\":\"Drama\"},{\"id\":36,\"name\":\"History\"},{\"id\":10752,\"name\":\"War\"}],\"homepage\":\"\",\"id\":10110,\"imdb_id\":\"tt0092965\",\"original_language\":\"en\",\"original_title\":\"Empire of the Sun\",\"overview\":\"The story of young English boy who lives with his parents in Shanghai during World War II. After the Pearl Harbor attack, the Japanese occupy the Shanghai International Settlement, and in the following chaos Jim becomes separated from his parents.\",\"popularity\":1.03708,\"poster_path\":\"/mD5BkLNSPXS8GZDB0VRRrI7YV5V.jpg\",\"production_companies\":[{\"name\":\"Amblin Entertainment\",\"id\":56},{\"name\":\"Warner Bros.\",\"id\":6194}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"release_date\":\"1987-12-25\",\"revenue\":0,\"runtime\":153,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"},{\"iso_639_1\":\"ja\",\"name\":\"日本語\"},{\"iso_639_1\":\"zh\",\"name\":\"普通话\"}],\"status\":\"Released\",\"tagline\":\"To survive in a world at war, he must find a strength greater than all the events that surround him.\",\"title\":\"Empire of the Sun\",\"video\":false,\"vote_average\":6.9,\"vote_count\":252}");

		when(mockHttpService.get("http://api.themoviedb.org/3/movie/10110/credits?api_key=6e940a50f408c433af4fea8a64699b5f", 20000L))
		.thenReturn("{\"id\":10110,\"cast\":[{\"cast_id\":1,\"character\":\"Jim 'Jamie' Graham\",\"credit_id\":\"52fe43299251416c75005c7f\",\"id\":3894,\"name\":\"Christian Bale\",\"order\":0,\"profile_path\":\"/pPXnqoGD91znz4FwQ6aKuxi6Pcy.jpg\"},{\"cast_id\":2,\"character\":\"Basie\",\"credit_id\":\"52fe43299251416c75005c83\",\"id\":6949,\"name\":\"John Malkovich\",\"order\":1,\"profile_path\":\"/j3KJURh51bOLb4WNqx4eGAbePV7.jpg\"},{\"cast_id\":3,\"character\":\"Mrs. Victor\",\"credit_id\":\"52fe43299251416c75005c87\",\"id\":8436,\"name\":\"Miranda Richardson\",\"order\":2,\"profile_path\":\"/kTs3t6pnO3zR7WYSVYzQfJ9yKMW.jpg\"},{\"cast_id\":4,\"character\":\"Dr. Rawlins\",\"credit_id\":\"52fe43299251416c75005c8b\",\"id\":53517,\"name\":\"Nigel Havers\",\"order\":3,\"profile_path\":\"/yujuXwxZibgl7NMhk0uZ1FX7Nyf.jpg\"},{\"cast_id\":5,\"character\":\"Dainty\",\"credit_id\":\"52fe43299251416c75005c8f\",\"id\":7399,\"name\":\"Ben Stiller\",\"order\":4,\"profile_path\":\"/umikKeCX3vEZoUcx2klxPG8571s.jpg\"},{\"cast_id\":18,\"character\":\"Frank Demarest\",\"credit_id\":\"53616fb0c3a368395d000420\",\"id\":532,\"name\":\"Joe Pantoliano\",\"order\":5,\"profile_path\":\"/zBvDX2HWepvW9im6ikgoyOL2Xj0.jpg\"},{\"cast_id\":19,\"character\":\"Maxton\",\"credit_id\":\"53616fc1c3a368397600041a\",\"id\":10655,\"name\":\"Leslie Phillips\",\"order\":6,\"profile_path\":\"/A1vO83wMO5eG0AxRvBwwQwu5NCg.jpg\"},{\"cast_id\":20,\"character\":\"Sgt. Nagata\",\"credit_id\":\"53616fcfc3a368394e000425\",\"id\":93892,\"name\":\"Masatō Ibu\",\"order\":7,\"profile_path\":\"/oVy1sMt7DVttkG5Gpxcv6ullVU9.jpg\"},{\"cast_id\":21,\"character\":\"Mary Graham, Jim's mother\",\"credit_id\":\"536170a9c3a368396e000420\",\"id\":1239914,\"name\":\"Emily Richard\",\"order\":8,\"profile_path\":null},{\"cast_id\":22,\"character\":\"John Graham, Jim's father\",\"credit_id\":\"536170d7c3a368394e00043a\",\"id\":62231,\"name\":\"Rupert Frazer\",\"order\":9,\"profile_path\":null},{\"cast_id\":23,\"character\":\"Mr. Victor\",\"credit_id\":\"536170e5c3a368394700043d\",\"id\":1206019,\"name\":\"Peter Galea\",\"order\":10,\"profile_path\":null},{\"cast_id\":24,\"character\":\"Kamikaze Boy Pilot\",\"credit_id\":\"536170f7c3a36839650003ed\",\"id\":1169235,\"name\":\"Takatarô Kataoka\",\"order\":11,\"profile_path\":null},{\"cast_id\":25,\"character\":\"Tiptree\",\"credit_id\":\"53617109c3a3683947000440\",\"id\":4042,\"name\":\"David Neidorf\",\"order\":12,\"profile_path\":null},{\"cast_id\":26,\"character\":\"Cohen\",\"credit_id\":\"53617118c3a3683947000442\",\"id\":5147,\"name\":\"Ralph Seymour\",\"order\":13,\"profile_path\":\"/7lu95loRqfN0TTWZGM1QfI5hX8W.jpg\"},{\"cast_id\":27,\"character\":\"Mr. Lockwood\",\"credit_id\":\"5361712bc3a368395d000433\",\"id\":41957,\"name\":\"Robert Stephens\",\"order\":14,\"profile_path\":\"/q1GXvr08N9MzkTrMQjSXwDPrzRV.jpg\"},{\"cast_id\":28,\"character\":\"Yang\",\"credit_id\":\"53617149c3a368394e000442\",\"id\":1315503,\"name\":\"Zhai Nai She\",\"order\":15,\"profile_path\":null},{\"cast_id\":40,\"character\":\"Sgt. Uchida\",\"credit_id\":\"56a36c2fc3a36838a3002758\",\"id\":553969,\"name\":\"Guts Ishimatsu\",\"order\":17,\"profile_path\":\"/nseDYTb7aL9DbxI4MO6Nu6NjNEW.jpg\"},{\"cast_id\":30,\"character\":\"Amy Matthews\",\"credit_id\":\"5361718bc3a36839650003f5\",\"id\":1315505,\"name\":\"Emma Piper\",\"order\":18,\"profile_path\":null},{\"cast_id\":31,\"character\":\"Mr. Radik\",\"credit_id\":\"5361725e0e0a2649bf0004c9\",\"id\":1315316,\"name\":\"James Walker\",\"order\":19,\"profile_path\":null},{\"cast_id\":33,\"character\":\"Singing Prisoner\",\"credit_id\":\"5697a6dc92514154cc00249b\",\"id\":1563188,\"name\":\"Jack Dearlove\",\"order\":20,\"profile_path\":null},{\"cast_id\":34,\"character\":\"Mrs. Gilmour\",\"credit_id\":\"5697a88892514154db002262\",\"id\":1134138,\"name\":\"Anna Turner\",\"order\":21,\"profile_path\":null},{\"cast_id\":35,\"character\":\"Mrs. Phillips\",\"credit_id\":\"5697a90bc3a3683af50023a9\",\"id\":1520045,\"name\":\"Ann Castle\",\"order\":22,\"profile_path\":null},{\"cast_id\":36,\"character\":\"Mrs. Lockwood\",\"credit_id\":\"5697a92192514154db002274\",\"id\":1402380,\"name\":\"Yvonne Gilan\",\"order\":23,\"profile_path\":null},{\"cast_id\":37,\"character\":\"Mr. Partridge\",\"credit_id\":\"5697a93b92514154d8002492\",\"id\":40160,\"name\":\"Ralph Michael\",\"order\":24,\"profile_path\":null},{\"cast_id\":38,\"character\":\"Mrs. Hug\",\"credit_id\":\"5697a95892514154c9002426\",\"id\":44400,\"name\":\"Sybil Maas\",\"order\":25,\"profile_path\":null},{\"cast_id\":39,\"character\":\"British Prisoner\",\"credit_id\":\"5697a99a92514154d800249d\",\"id\":26687,\"name\":\"Barrie Houghton\",\"order\":26,\"profile_path\":null}],\"crew\":[{\"credit_id\":\"52fe43299251416c75005c95\",\"department\":\"Directing\",\"id\":488,\"job\":\"Director\",\"name\":\"Steven Spielberg\",\"profile_path\":\"/pOK15UNaw75Bzj7BQO1ulehbPPm.jpg\"},{\"credit_id\":\"52fe43299251416c75005c9b\",\"department\":\"Writing\",\"id\":372,\"job\":\"Screenplay\",\"name\":\"Tom Stoppard\",\"profile_path\":\"/x0yktHN4YN5kY6SkoGVkKxmZkhV.jpg\"},{\"credit_id\":\"52fe43299251416c75005ca1\",\"department\":\"Writing\",\"id\":736,\"job\":\"Screenplay\",\"name\":\"Menno Meyjes\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005ca7\",\"department\":\"Production\",\"id\":489,\"job\":\"Producer\",\"name\":\"Kathleen Kennedy\",\"profile_path\":\"/uHEExgTTzJNDX1Gal9CW5n3QdEJ.jpg\"},{\"credit_id\":\"52fe43299251416c75005cad\",\"department\":\"Production\",\"id\":664,\"job\":\"Producer\",\"name\":\"Frank Marshall\",\"profile_path\":\"/iZrDellT1OWf1syKzBgQHjY2hJe.jpg\"},{\"credit_id\":\"52fe43299251416c75005cb3\",\"department\":\"Production\",\"id\":488,\"job\":\"Producer\",\"name\":\"Steven Spielberg\",\"profile_path\":\"/pOK15UNaw75Bzj7BQO1ulehbPPm.jpg\"},{\"credit_id\":\"52fe43299251416c75005cb9\",\"department\":\"Production\",\"id\":63614,\"job\":\"Executive Producer\",\"name\":\"Robert Shapiro\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005cbf\",\"department\":\"Sound\",\"id\":491,\"job\":\"Original Music Composer\",\"name\":\"John Williams\",\"profile_path\":\"/d7NNRZQAIzLBSaoez550QHLpTk.jpg\"},{\"credit_id\":\"52fe43299251416c75005cc5\",\"department\":\"Camera\",\"id\":9965,\"job\":\"Director of Photography\",\"name\":\"Allen Daviau\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005ccb\",\"department\":\"Editing\",\"id\":493,\"job\":\"Editor\",\"name\":\"Michael Kahn\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005cd1\",\"department\":\"Writing\",\"id\":13547,\"job\":\"Novel\",\"name\":\"J.G. Ballard\",\"profile_path\":\"/ot3cvtxOYLJkYHs4NisMPQSrU8w.jpg\"},{\"credit_id\":\"536172b8c3a368395d00044a\",\"department\":\"Crew\",\"id\":9965,\"job\":\"Cinematography\",\"name\":\"Allen Daviau\",\"profile_path\":null}]}");

		// When
		final List<Movie> movies = toTest.getMovies("Empire of the Sun");

		assertEquals(1, movies.size());

		assertEquals("1987", movies.get(0).getYear());
		assertEquals("Steven Spielberg", movies.get(0).getDirector());
		assertEquals("Empire of the Sun", movies.get(0).getTitle());
		assertEquals("tt0092965", movies.get(0).getUniqueId());
	}


	/**
	 * TMDB search results are rather numerous, we cap the search results to 10
	 */
	@Test
	public void testMoviesReturnedCappedAt10() throws Exception {
		// Given
		final String movie = "{\"poster_path\":\"/mD5BkLNSPXS8GZDB0VRRrI7YV5V.jpg\",\"adult\":false,\"overview\":\"The story of young English boy who lives with his parents in Shanghai during World War II. After the Pearl Harbor attack, the Japanese occupy the Shanghai International Settlement, and in the following chaos Jim becomes separated from his parents.\",\"release_date\":\"1987-12-25\",\"genre_ids\":[18,36,10752],\"id\":10110,\"original_title\":\"Empire of the Sun\",\"original_language\":\"en\",\"title\":\"Empire of the Sun\",\"backdrop_path\":\"/qfBRuSHMNHR19HkCVdwiBeaXXt1.jpg\",\"popularity\":2.03708,\"vote_count\":251,\"video\":false,\"vote_average\":6.92}";
		String movieSearchResult = "{\"page\":1,\"results\":[" + movie;

		for (int i = 0; i < 20; i++) {
			movieSearchResult = movieSearchResult + ",";
			movieSearchResult = movieSearchResult + movie;
		}

		movieSearchResult = movieSearchResult + "],\"total_results\":1,\"total_pages\":1}";

		when(mockHttpService.get("http://api.themoviedb.org/3/search/movie?api_key=6e940a50f408c433af4fea8a64699b5f&query=Empire+of+the+Sun", 20000L))
		.thenReturn(movieSearchResult);

		when(mockHttpService.get("http://api.themoviedb.org/3/movie/10110?api_key=6e940a50f408c433af4fea8a64699b5f", 20000L))
		.thenReturn("{\"adult\":false,\"backdrop_path\":\"/qfBRuSHMNHR19HkCVdwiBeaXXt1.jpg\",\"belongs_to_collection\":null,\"budget\":38000000,\"genres\":[{\"id\":18,\"name\":\"Drama\"},{\"id\":36,\"name\":\"History\"},{\"id\":10752,\"name\":\"War\"}],\"homepage\":\"\",\"id\":10110,\"imdb_id\":\"tt0092965\",\"original_language\":\"en\",\"original_title\":\"Empire of the Sun\",\"overview\":\"The story of young English boy who lives with his parents in Shanghai during World War II. After the Pearl Harbor attack, the Japanese occupy the Shanghai International Settlement, and in the following chaos Jim becomes separated from his parents.\",\"popularity\":1.03708,\"poster_path\":\"/mD5BkLNSPXS8GZDB0VRRrI7YV5V.jpg\",\"production_companies\":[{\"name\":\"Amblin Entertainment\",\"id\":56},{\"name\":\"Warner Bros.\",\"id\":6194}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"release_date\":\"1987-12-25\",\"revenue\":0,\"runtime\":153,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"},{\"iso_639_1\":\"ja\",\"name\":\"日本語\"},{\"iso_639_1\":\"zh\",\"name\":\"普通话\"}],\"status\":\"Released\",\"tagline\":\"To survive in a world at war, he must find a strength greater than all the events that surround him.\",\"title\":\"Empire of the Sun\",\"video\":false,\"vote_average\":6.9,\"vote_count\":252}");

		when(mockHttpService.get("http://api.themoviedb.org/3/movie/10110/credits?api_key=6e940a50f408c433af4fea8a64699b5f", 20000L))
		.thenReturn("{\"id\":10110,\"cast\":[{\"cast_id\":1,\"character\":\"Jim 'Jamie' Graham\",\"credit_id\":\"52fe43299251416c75005c7f\",\"id\":3894,\"name\":\"Christian Bale\",\"order\":0,\"profile_path\":\"/pPXnqoGD91znz4FwQ6aKuxi6Pcy.jpg\"},{\"cast_id\":2,\"character\":\"Basie\",\"credit_id\":\"52fe43299251416c75005c83\",\"id\":6949,\"name\":\"John Malkovich\",\"order\":1,\"profile_path\":\"/j3KJURh51bOLb4WNqx4eGAbePV7.jpg\"},{\"cast_id\":3,\"character\":\"Mrs. Victor\",\"credit_id\":\"52fe43299251416c75005c87\",\"id\":8436,\"name\":\"Miranda Richardson\",\"order\":2,\"profile_path\":\"/kTs3t6pnO3zR7WYSVYzQfJ9yKMW.jpg\"},{\"cast_id\":4,\"character\":\"Dr. Rawlins\",\"credit_id\":\"52fe43299251416c75005c8b\",\"id\":53517,\"name\":\"Nigel Havers\",\"order\":3,\"profile_path\":\"/yujuXwxZibgl7NMhk0uZ1FX7Nyf.jpg\"},{\"cast_id\":5,\"character\":\"Dainty\",\"credit_id\":\"52fe43299251416c75005c8f\",\"id\":7399,\"name\":\"Ben Stiller\",\"order\":4,\"profile_path\":\"/umikKeCX3vEZoUcx2klxPG8571s.jpg\"},{\"cast_id\":18,\"character\":\"Frank Demarest\",\"credit_id\":\"53616fb0c3a368395d000420\",\"id\":532,\"name\":\"Joe Pantoliano\",\"order\":5,\"profile_path\":\"/zBvDX2HWepvW9im6ikgoyOL2Xj0.jpg\"},{\"cast_id\":19,\"character\":\"Maxton\",\"credit_id\":\"53616fc1c3a368397600041a\",\"id\":10655,\"name\":\"Leslie Phillips\",\"order\":6,\"profile_path\":\"/A1vO83wMO5eG0AxRvBwwQwu5NCg.jpg\"},{\"cast_id\":20,\"character\":\"Sgt. Nagata\",\"credit_id\":\"53616fcfc3a368394e000425\",\"id\":93892,\"name\":\"Masatō Ibu\",\"order\":7,\"profile_path\":\"/oVy1sMt7DVttkG5Gpxcv6ullVU9.jpg\"},{\"cast_id\":21,\"character\":\"Mary Graham, Jim's mother\",\"credit_id\":\"536170a9c3a368396e000420\",\"id\":1239914,\"name\":\"Emily Richard\",\"order\":8,\"profile_path\":null},{\"cast_id\":22,\"character\":\"John Graham, Jim's father\",\"credit_id\":\"536170d7c3a368394e00043a\",\"id\":62231,\"name\":\"Rupert Frazer\",\"order\":9,\"profile_path\":null},{\"cast_id\":23,\"character\":\"Mr. Victor\",\"credit_id\":\"536170e5c3a368394700043d\",\"id\":1206019,\"name\":\"Peter Galea\",\"order\":10,\"profile_path\":null},{\"cast_id\":24,\"character\":\"Kamikaze Boy Pilot\",\"credit_id\":\"536170f7c3a36839650003ed\",\"id\":1169235,\"name\":\"Takatarô Kataoka\",\"order\":11,\"profile_path\":null},{\"cast_id\":25,\"character\":\"Tiptree\",\"credit_id\":\"53617109c3a3683947000440\",\"id\":4042,\"name\":\"David Neidorf\",\"order\":12,\"profile_path\":null},{\"cast_id\":26,\"character\":\"Cohen\",\"credit_id\":\"53617118c3a3683947000442\",\"id\":5147,\"name\":\"Ralph Seymour\",\"order\":13,\"profile_path\":\"/7lu95loRqfN0TTWZGM1QfI5hX8W.jpg\"},{\"cast_id\":27,\"character\":\"Mr. Lockwood\",\"credit_id\":\"5361712bc3a368395d000433\",\"id\":41957,\"name\":\"Robert Stephens\",\"order\":14,\"profile_path\":\"/q1GXvr08N9MzkTrMQjSXwDPrzRV.jpg\"},{\"cast_id\":28,\"character\":\"Yang\",\"credit_id\":\"53617149c3a368394e000442\",\"id\":1315503,\"name\":\"Zhai Nai She\",\"order\":15,\"profile_path\":null},{\"cast_id\":40,\"character\":\"Sgt. Uchida\",\"credit_id\":\"56a36c2fc3a36838a3002758\",\"id\":553969,\"name\":\"Guts Ishimatsu\",\"order\":17,\"profile_path\":\"/nseDYTb7aL9DbxI4MO6Nu6NjNEW.jpg\"},{\"cast_id\":30,\"character\":\"Amy Matthews\",\"credit_id\":\"5361718bc3a36839650003f5\",\"id\":1315505,\"name\":\"Emma Piper\",\"order\":18,\"profile_path\":null},{\"cast_id\":31,\"character\":\"Mr. Radik\",\"credit_id\":\"5361725e0e0a2649bf0004c9\",\"id\":1315316,\"name\":\"James Walker\",\"order\":19,\"profile_path\":null},{\"cast_id\":33,\"character\":\"Singing Prisoner\",\"credit_id\":\"5697a6dc92514154cc00249b\",\"id\":1563188,\"name\":\"Jack Dearlove\",\"order\":20,\"profile_path\":null},{\"cast_id\":34,\"character\":\"Mrs. Gilmour\",\"credit_id\":\"5697a88892514154db002262\",\"id\":1134138,\"name\":\"Anna Turner\",\"order\":21,\"profile_path\":null},{\"cast_id\":35,\"character\":\"Mrs. Phillips\",\"credit_id\":\"5697a90bc3a3683af50023a9\",\"id\":1520045,\"name\":\"Ann Castle\",\"order\":22,\"profile_path\":null},{\"cast_id\":36,\"character\":\"Mrs. Lockwood\",\"credit_id\":\"5697a92192514154db002274\",\"id\":1402380,\"name\":\"Yvonne Gilan\",\"order\":23,\"profile_path\":null},{\"cast_id\":37,\"character\":\"Mr. Partridge\",\"credit_id\":\"5697a93b92514154d8002492\",\"id\":40160,\"name\":\"Ralph Michael\",\"order\":24,\"profile_path\":null},{\"cast_id\":38,\"character\":\"Mrs. Hug\",\"credit_id\":\"5697a95892514154c9002426\",\"id\":44400,\"name\":\"Sybil Maas\",\"order\":25,\"profile_path\":null},{\"cast_id\":39,\"character\":\"British Prisoner\",\"credit_id\":\"5697a99a92514154d800249d\",\"id\":26687,\"name\":\"Barrie Houghton\",\"order\":26,\"profile_path\":null}],\"crew\":[{\"credit_id\":\"52fe43299251416c75005c95\",\"department\":\"Directing\",\"id\":488,\"job\":\"Director\",\"name\":\"Steven Spielberg\",\"profile_path\":\"/pOK15UNaw75Bzj7BQO1ulehbPPm.jpg\"},{\"credit_id\":\"52fe43299251416c75005c9b\",\"department\":\"Writing\",\"id\":372,\"job\":\"Screenplay\",\"name\":\"Tom Stoppard\",\"profile_path\":\"/x0yktHN4YN5kY6SkoGVkKxmZkhV.jpg\"},{\"credit_id\":\"52fe43299251416c75005ca1\",\"department\":\"Writing\",\"id\":736,\"job\":\"Screenplay\",\"name\":\"Menno Meyjes\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005ca7\",\"department\":\"Production\",\"id\":489,\"job\":\"Producer\",\"name\":\"Kathleen Kennedy\",\"profile_path\":\"/uHEExgTTzJNDX1Gal9CW5n3QdEJ.jpg\"},{\"credit_id\":\"52fe43299251416c75005cad\",\"department\":\"Production\",\"id\":664,\"job\":\"Producer\",\"name\":\"Frank Marshall\",\"profile_path\":\"/iZrDellT1OWf1syKzBgQHjY2hJe.jpg\"},{\"credit_id\":\"52fe43299251416c75005cb3\",\"department\":\"Production\",\"id\":488,\"job\":\"Producer\",\"name\":\"Steven Spielberg\",\"profile_path\":\"/pOK15UNaw75Bzj7BQO1ulehbPPm.jpg\"},{\"credit_id\":\"52fe43299251416c75005cb9\",\"department\":\"Production\",\"id\":63614,\"job\":\"Executive Producer\",\"name\":\"Robert Shapiro\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005cbf\",\"department\":\"Sound\",\"id\":491,\"job\":\"Original Music Composer\",\"name\":\"John Williams\",\"profile_path\":\"/d7NNRZQAIzLBSaoez550QHLpTk.jpg\"},{\"credit_id\":\"52fe43299251416c75005cc5\",\"department\":\"Camera\",\"id\":9965,\"job\":\"Director of Photography\",\"name\":\"Allen Daviau\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005ccb\",\"department\":\"Editing\",\"id\":493,\"job\":\"Editor\",\"name\":\"Michael Kahn\",\"profile_path\":null},{\"credit_id\":\"52fe43299251416c75005cd1\",\"department\":\"Writing\",\"id\":13547,\"job\":\"Novel\",\"name\":\"J.G. Ballard\",\"profile_path\":\"/ot3cvtxOYLJkYHs4NisMPQSrU8w.jpg\"},{\"credit_id\":\"536172b8c3a368395d00044a\",\"department\":\"Crew\",\"id\":9965,\"job\":\"Cinematography\",\"name\":\"Allen Daviau\",\"profile_path\":null}]}");

		// When
		final List<Movie> movies = toTest.getMovies("Empire of the Sun");

		assertEquals(10, movies.size());

		assertEquals("1987", movies.get(0).getYear());
		assertEquals("Steven Spielberg", movies.get(0).getDirector());
		assertEquals("Empire of the Sun", movies.get(0).getTitle());
		assertEquals("tt0092965", movies.get(0).getUniqueId());
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