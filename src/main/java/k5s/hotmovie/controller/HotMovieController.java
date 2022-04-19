package k5s.hotmovie.controller;

import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/movies/{movieCode}")
    public String getMovie(@PathVariable("movieCode") Long movieCode, Model model) {
        List<HotMovie> result = movieService.findOne(movieCode);
        model.addAttribute("movies", result);

        return "movies/movieInfo";
    }
}
