package br.com.worstmovie.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public AwardWinningProducerDto longestIntervalBetweenTwoAwards() {
		List<AwardWinningProducerDto> list = createDtoList();
		Comparator<AwardWinningProducerDto> comparator = Comparator.comparing(AwardWinningProducerDto::getInterval);
		AwardWinningProducerDto dto = list.stream().max(comparator).get();
		return dto;
	}
	
	public AwardWinningProducerDto twoFastestAwards() {
		List<AwardWinningProducerDto> list = createDtoList();
		Comparator<AwardWinningProducerDto> comparator = Comparator.comparing(AwardWinningProducerDto::getInterval);
		AwardWinningProducerDto dto = list.stream().min(comparator).get();
		return dto;
	}
	
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
				Integer prevYear = dto.getPreviousWin();
				Integer follYear = dto.getFollowingWin();
				if(yearMovie <= prevYear) {
					dto.setPreviousWin(yearMovie);
				}
				if(yearMovie >= follYear) {
					dto.setFollowingWin(yearMovie);
				}
				dto.setInterval(dto.getFollowingWin() - dto.getPreviousWin());
			}
			map.put(movie.getProducer(), dto);
		}
		List<AwardWinningProducerDto> list = new ArrayList<>();
		Iterator<Entry<String, AwardWinningProducerDto>> iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<String, AwardWinningProducerDto> entry = iterator.next();
	        AwardWinningProducerDto dto = entry.getValue();
	        if(dto.getInterval() > 0) list.add(dto);
	    }
		return list;
	}
}
