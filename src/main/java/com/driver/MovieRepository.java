package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        movieMap.put(movie.getName(),movie);
    }

    public void saveDirector(Director director){
        // your code here
        directorMap.put(director.getName(),director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            directorMovieMapping.putIfAbsent(director,new ArrayList<>());
            directorMovieMapping.get(director).add(movie);
        }
    }

    public Movie findMovie(String movie){
        // your code here
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        // your code here
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        return  directorMovieMapping.getOrDefault(director,new ArrayList<>());
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        // your code here
        if(directorMap.containsKey(director)){
            List<String>movies = directorMovieMapping.getOrDefault(director,new ArrayList<>());
            for(String Movie: movies){
                movieMap.remove(director);

            }
            directorMovieMapping.remove(director);
            directorMap.remove(director);

        }
    }

    public void deleteAllDirector(){
        // your code here
        for(String director: new HashSet<>(directorMap.keySet())){
            deleteDirector(director);
        }
    }
}