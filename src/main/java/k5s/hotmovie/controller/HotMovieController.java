package k5s.hotmovie.controller;

import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.dto.AuthenticationResponseDto;
import k5s.hotmovie.service.AuthService;
import k5s.hotmovie.service.MovieService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class HotMovieController {
    private final MovieService movieService;
    private final AuthService authService;

    @Autowired
    public HotMovieController(MovieService movieService, AuthService authService){
        this.movieService = movieService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String home(Model model, @CookieValue(value = "accessToken", required = false) String accessToken) {
        if (accessToken==null) {

            List<HotMovie> movies = movieService.findRecentUpdate();
            model.addAttribute("movies", movies);
            model.addAttribute("memberName", null);
        }
        else {
            AuthenticationResponseDto result = authService.requestAuthentication(accessToken);
            Long memberId = result.getId();
            String memberName = result.getName();

            if (memberName==null){
                List<HotMovie> movies = movieService.findRecentUpdate();
                model.addAttribute("movies", movies);
                model.addAttribute("memberName", null);
            }
            else{
                List<HotMovie> movies = movieService.findRecentUpdate();
                model.addAttribute("movies", movies);
                model.addAttribute("memberName", memberName);
            }
        }
        return "movies/movieToday";
    }

    @GetMapping("/movies")
    public String list(@RequestParam(required = false, defaultValue = "1") int page, Model model
            , @CookieValue(value = "accessToken", required = false) String accessToken) {

        if (page < 1) {
            page = 1;
        }

//        System.out.println("?????????????????? ????????? : " + accessToken);

        if (accessToken==null) {
//            System.out.println("????????????~!~!~!~!~!~!");
            List<HotMovie> movies = movieService.findWithPage(page);
            model.addAttribute("movies", movies);
            model.addAttribute("page", page);
            model.addAttribute("memberName", null);
        }
        else {
//            System.out.println("????????????~!~!~!~!~!~!");
            AuthenticationResponseDto result = authService.requestAuthentication(accessToken);

            Long memberId = result.getId();
            String memberName = result.getName();
//            System.out.println("?????????"+memberId);
//            System.out.println("??????"+memberName);

            if (memberName==null){
//                System.out.println("????????? ?????? ?????? ??????");
                List<HotMovie> movies = movieService.findWithPage(page);
                model.addAttribute("movies", movies);
                model.addAttribute("page", page);
                model.addAttribute("memberName", null);
            }
            else{
//                System.out.println("????????? ??? ??????");
                List<HotMovie> movies = movieService.findWithPage(page);
                model.addAttribute("movies", movies);
                model.addAttribute("page", page);
                model.addAttribute("memberName", memberName);
            }
        }
        return "movies/moviePreviewList";
    }

    @GetMapping("/movies/{movieCode}")
    public String getMovie(@PathVariable("movieCode") Long movieCode, Model model
            , @CookieValue(value = "accessToken", required = false) String accessToken) {

//        System.out.println("?????????????????? ????????? : " + accessToken);

        if (accessToken==null) {
//            System.out.println("????????????~!~!~!~!~!~!");
            List<HotMovie> movie = movieService.findOne(movieCode);
            model.addAttribute("movies", movie);
            model.addAttribute("movieCode", movieCode);
            model.addAttribute("memberName", null);
        }
        else {
//            System.out.println("????????????~!~!~!~!~!~!");
            AuthenticationResponseDto result = authService.requestAuthentication(accessToken);
            String memberName = result.getName();

            if (memberName==null){
//                System.out.println("????????? ?????? ?????? ??????");
                List<HotMovie> movie = movieService.findOne(movieCode);
                model.addAttribute("movies", movie);
                model.addAttribute("movieCode", movieCode);
                model.addAttribute("memberName", null);
            }
            else{
//                System.out.println("????????? ??? ??????");
                List<HotMovie> movie = movieService.findOne(movieCode);
                model.addAttribute("movies", movie);
                model.addAttribute("movieCode", movieCode);
                model.addAttribute("memberName", memberName);
            }
        }
        return "movies/movieInfo";
    }

    @GetMapping("/movies/request-logout")
    @ResponseBody
    public void logout(@CookieValue(value = "accessToken", required = false) String accessToken) {
//        System.out.println("???????????? ???????????? ????????? : " + accessToken);
        authService.requestLogout(accessToken);
    }
}
