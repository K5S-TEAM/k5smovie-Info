package k5s.hotmovie.service;
import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.repository.MovieRepository;

import java.util.List;

//@Service
//@Transactional

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
    /**
     * 평점 수정 쿼리
     */
    public void updateMovieScore(Long movieCode, Double score) {
        movieRepository.updateMovieScore(movieCode, score);
    }

}


