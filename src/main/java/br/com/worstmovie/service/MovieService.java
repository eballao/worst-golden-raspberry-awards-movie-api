package br.com.worstmovie.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.worstmovie.domain.Movie;
import br.com.worstmovie.dto.AwardWinningProducerDto;
import br.com.worstmovie.dto.IntervalWinnersDto;
import br.com.worstmovie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repo;
	
	public IntervalWinnersDto minMaxIntervalBetweenTwoAwards() {
		List<AwardWinningProducerDto> list = createDtoList();
		
		Comparator<AwardWinningProducerDto> comparator = Comparator.comparing(AwardWinningProducerDto::getInterval);
		AwardWinningProducerDto dtoMin = list.stream().min(comparator).get();
		
		AwardWinningProducerDto dtoMax = list.stream().max(comparator).get();
		
		List<AwardWinningProducerDto> listMin = new ArrayList<>();
		List<AwardWinningProducerDto> listMax = new ArrayList<>();
		listMin.add(dtoMin);
		listMax.add(dtoMax);
		
		IntervalWinnersDto interval = new IntervalWinnersDto();
		
		interval.setMax(listMax);
		interval.setMin(listMin);
		
		return interval;
	}
	
	private List<AwardWinningProducerDto> createDtoList() {
		List<Movie> movies = repo.awardWinningProducers();
		Map<String, AwardWinningProducerDto> map = new HashMap<>();
		List<AwardWinningProducerDto> dtos = new ArrayList<>();
		for(Movie movie: movies) {
			AwardWinningProducerDto dto = map.get(movie.getProducer());
			if(dto == null) {
				dto = new AwardWinningProducerDto();
				dto.setProducer(movie.getProducer());
				dto.setPreviousWin(movie.getYear());
				dto.setFollowingWin(movie.getYear());
				dto.setInterval(0);
			} else {
				Integer yearMovie = movie.getYear();
				dto.setPreviousWin(dto.getFollowingWin());
				dto.setFollowingWin(yearMovie);
				dto.setInterval(dto.getFollowingWin() - dto.getPreviousWin());
				dtos.add(dto);
			}
			map.put(movie.getProducer(), dto);
		}
		return dtos;
	}
}
