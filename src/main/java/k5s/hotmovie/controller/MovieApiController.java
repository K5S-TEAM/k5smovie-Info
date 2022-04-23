package k5s.hotmovie.controller;

import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.dto.GradeUpdateRequestDto;
import k5s.hotmovie.dto.MovieNameRequestDto;
import k5s.hotmovie.dto.MovieNameResponseDto;
import k5s.hotmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @param movieCode
     * @param dto
     * 특정 영화 평점 update api
     * @return
     */
    @PatchMapping("/movies/{movieCode}")
    public ResponseEntity updateGradeScore(@PathVariable("movieCode") Long movieCode
    , @RequestBody GradeUpdateRequestDto dto) {
        movieService.updateMovieGrade(movieCode, dto.getAverageScore());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
