package br.com.worstmovie.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.worstmovie.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query(
		"SELECT m "
	  + "FROM Movie m "
	  + "WHERE m.winner = true "
	  + "ORDER BY m.producer, m.year ASC")
	List<Movie> awardWinningProducers();
}
