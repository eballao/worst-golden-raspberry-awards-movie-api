package br.com.worstmovie.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.worstmovie.domain.Movie;	

@Component
public class Processor implements ItemProcessor<Movie, Movie> {

    @Override
    public Movie process(Movie movie) throws Exception {
    	// To perform some pre-processing on loading csv data
    	return movie;
    }
}
