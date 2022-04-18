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
     * 회원가입
     */
//    public Long join(HotMovie movie) {
//        validateDuplicateMovies(movie); //중복 영화 검증
//        movieRepository.save(movie);
//        return movie.getCode();
//    }
//    private void validateDuplicateMovies(HotMovie movie) {
//        movieRepository.findByName(movie.getTitle())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }
    /**
     * 전체 회원 조회
     */
    public List<HotMovie> findMovies() {
        return movieRepository.findAll();
    }
    public List<HotMovie> findOne(Long movieCode) {
        return movieRepository.findByCode(movieCode);
    }
}


