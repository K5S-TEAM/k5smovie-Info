package k5s.hotmovie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthenticationResponseDto {
    Long id;
    String name;

    public AuthenticationResponseDto(){}

    public AuthenticationResponseDto(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
