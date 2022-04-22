package k5s.hotmovie.controller;

import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.dto.MovieNameRequestDto;
import k5s.hotmovie.dto.MovieNameResponseDto;
import k5s.hotmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity getMovieName(@RequestBody MovieNameRequestDto dto) {
        List<HotMovie> result = movieService.findOne(dto.getId());

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new MovieNameResponseDto(result.get(0).getTitle()));
        }
    }
}
