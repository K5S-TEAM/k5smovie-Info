package k5s.hotmovie.service;
import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.dto.AuthenticationRequestDto;
import k5s.hotmovie.dto.AuthenticationResponseDto;
import k5s.hotmovie.error.InvalidAuthenticationException;
import k5s.hotmovie.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class MovieService {
    private final MovieRepository movieRepository;

    //@Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    /**
     * 영화 조회
     */
    public List<HotMovie> findMovies() {
        return movieRepository.findAll();
    }
    public List<HotMovie> findOne(Long movieCode) {
        return movieRepository.findByCode(movieCode);
    }

    /**
     * 영화 페이징 쿼리
     */
    public List<HotMovie> findWithPage(int page){
        return movieRepository.findWithPage(page);
    }
}


