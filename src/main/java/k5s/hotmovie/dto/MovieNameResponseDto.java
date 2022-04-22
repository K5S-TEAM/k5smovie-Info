package k5s.hotmovie.dto;

import lombok.Getter;

@Getter
public class MovieNameResponseDto {
    String movieName;

    public MovieNameResponseDto(String movieName) {
        this.movieName = movieName;
    }
}
