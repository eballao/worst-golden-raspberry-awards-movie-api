package br.com.worstmovie.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.worstmovie.dto.AwardWinningProducerDto;
import br.com.worstmovie.dto.IntervalWinnersDto;
import br.com.worstmovie.service.MovieService;

@RestController
@RequestMapping(value="/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	
	@RequestMapping(value = "/min-max-interval-between-two-awards", method=RequestMethod.GET)
	public ResponseEntity<IntervalWinnersDto> minMaxIntervalBetweenTwoAwards() {
		return ResponseEntity.ok().body(service.minMaxIntervalBetweenTwoAwards());
	}
}
