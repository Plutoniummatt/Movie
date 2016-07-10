# Movie
A simple tool to search for movies using the OMDB or TMDB APIs

Build using maven
* mvn clean compile assembly:single

To use this tool, specify an API to use, and the name of a movie to search for as JVM system properties 'api' and 'movie' respectively

Available APIs:
* "OMDB" - http://www.omdbapi.com
* "TMDB" - http://www.themoviedb.org

Example usage:
* java -Dapi="OMDB" -Dmovie="Indiana Jones" query.jar
