package com.andersen.chronology.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuth2LoginAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizedClientService clientService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        OAuth2LoginAuthenticationToken oauth2LoginAuthenticationToken = (OAuth2LoginAuthenticationToken) authentication;

        // Получение информации о пользователе из токена аутентификации
        OAuth2User oauth2User = oauth2LoginAuthenticationToken.getPrincipal();

        // Выполнение дополнительной проверки пользовательских данных, если необходимо
        if (!isUserValid(oauth2User)) {
            throw new BadCredentialsException("Invalid user");
        }

        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                oauth2LoginAuthenticationToken.getClientRegistration().getRegistrationId(),
                oauth2LoginAuthenticationToken.getClientRegistration().getClientName()
        );

        // Создание списка ролей для пользователя
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new OAuth2UserAuthority(oauth2User.getAttributes()));

        // Создание аутентификационного токена с данными пользователя и ролями
        Authentication auth = new UsernamePasswordAuthenticationToken(oauth2User, null, authorities);

        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isUserValid(OAuth2User oauth2User) {
        // Проверка информации о пользователе, например, проверка наличия обязательных полей или других правил
        // Верните true, если пользователь является допустимым, иначе верните false
        return true;
    }
}
