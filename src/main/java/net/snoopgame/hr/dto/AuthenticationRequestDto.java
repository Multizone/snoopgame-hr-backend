package net.snoopgame.hr.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String userName;
    private String password;
}
