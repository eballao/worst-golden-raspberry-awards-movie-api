package br.com.worstmovie.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.worstmovie.domain.Movie;
import br.com.worstmovie.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBWriter implements ItemWriter<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void write(List<? extends Movie> movies) throws Exception {
    	System.out.println("Processing Movies...");
    	List<Movie> moviesProcessed = processMovies(",",movies);
    	moviesProcessed = processMovies(" and ",moviesProcessed);
        System.out.println("Data Saved for Movies: " + moviesProcessed);
        movieRepository.saveAll(moviesProcessed);
    }
    
    public List<Movie> processMovies(String splitRegex, List<? extends Movie> movies) {
    	List<Movie> moviesProcessed = new ArrayList<>();
    	for(Movie movie: movies) {
    		List<Movie> newMovies = splitProducersAndCreateNewMovies(splitRegex,movie);
    		if(!newMovies.isEmpty()) {
    			moviesProcessed.addAll(newMovies);
    		} else {
    			moviesProcessed.add(movie);
    		}
    	}
    	return moviesProcessed;
    }
    
    public List<Movie> splitProducersAndCreateNewMovies(String splitRegex, Movie movie) {
    	String producersComma[] = movie.getProducer().split(splitRegex);
    	int producersCommaLength = producersComma.length;
    	List<Movie> newMovies = new ArrayList<>();
    	if(producersCommaLength > 1) {
    		for(int i = 0; i < producersCommaLength; i++) {
    			String producerComma = producersComma[i].trim();
    			Movie newMovie = new Movie(movie.getId(),movie.getYear(),movie.getTitle(),movie.getStudio(),producerComma,movie.getWinner());
    			newMovies.add(newMovie);
    		}
    	}
    	return newMovies;
    }
}
