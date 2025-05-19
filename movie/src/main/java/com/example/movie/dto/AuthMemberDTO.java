package com.example.movie.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AuthMemberDTO extends User {
    // 인증된 정보만 담는 용도

    private MemberDTO memberDTO;

    // username : id 개념
    public AuthMemberDTO(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthMemberDTO(MemberDTO memberDTO) {
        super(memberDTO.getEmail(), memberDTO.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE " + memberDTO.getMemberRole())));

        this.memberDTO = memberDTO;
    }

}
