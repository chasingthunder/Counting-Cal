package br.com.fiap.epiccountingcal.user;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "githubuser")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    Long id;
    String nome;
    String avatarUrl;
    @Builder.Default
    Integer calorias = 1;

    public static User convert(OAuth2User user){
        return new UserBuilder()
                .id(Long.valueOf(user.getName()))
                .nome(user.getAttribute("name"))
                .avatarUrl(user.getAttribute("avatar_url"))
                .build();
    }
    
}