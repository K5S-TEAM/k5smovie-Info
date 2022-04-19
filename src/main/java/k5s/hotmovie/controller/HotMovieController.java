package k5s.hotmovie.controller;

import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HotMovieController {
    private final MovieService movieService;

    @Autowired
    public HotMovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String list(Model model){
        List<HotMovie> movies = movieService.findMovies();
        model.addAttribute("movies", movies);
        return "movies/moviePreviewList";
    }

    @PostMapping("/movieInfo")
    public String findedList(MovieCode movieCode, Model model){
        HotMovie movie = new HotMovie();
        movie.setCode(movieCode.getCode());

        List<HotMovie> findedmovie = movieService.findOne(movie.getCode());
        model.addAttribute("findedmovie",findedmovie);

        return "movies/movieInfo";
    }

}
