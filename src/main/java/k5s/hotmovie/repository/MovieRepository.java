package k5s.hotmovie.repository;

import k5s.hotmovie.domain.HotMovie;

import java.util.List;

public interface MovieRepository {
    //Movie save(HotMovie movie);
    List<HotMovie> findByCode(Long code);
    //Optional<Movie> findByName(String name);
    List<HotMovie> findMovieList();
    List<HotMovie> findAll();
    List<HotMovie> findRecentUpdate();
    List<HotMovie> findWithPage(int page);
    void updateMovieScore(Long code, Double score);
}
