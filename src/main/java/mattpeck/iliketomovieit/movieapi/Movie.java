package mattpeck.iliketomovieit.movieapi;

/**
 * Model class holding relevant information of a movie
 *
 * @author Matt Peck
 */
public class Movie {

	/**
	 * Title of the movie
	 */
	private final String title;

	/**
	 * Year the movie was released
	 */
	private final String year;

	/**
	 * Director of the movie
	 */
	private String director;

	/**
	 * Unique ID of the movie provided by the external API
	 */
	private final String uniqueId;


	public Movie(final String uniqueId, final String title, final String year, final String director) {
		this.uniqueId = uniqueId;
		this.title = title;
		this.year = year;
		this.director = director;
	}


	/**
	 * @see #director
	 */
	public String getDirector() {
		return director;
	}


	/**
	 * @see #year
	 */
	public String getYear() {
		return year;
	}


	/**
	 * @see #title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @see #uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}


	@Override
	public String toString() {
		return String.format("Title: %s, Year: %s, Director: %s", title, year, director);
	}
}