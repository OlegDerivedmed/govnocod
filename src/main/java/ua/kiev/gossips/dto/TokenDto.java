package ua.kiev.gossips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.kiev.gossips.entities.Token;

@AllArgsConstructor
@Data
public class TokenDto {

    private String token;

    public static TokenDto from(Token token){
        return new TokenDto(token.getValue());
    }
}
