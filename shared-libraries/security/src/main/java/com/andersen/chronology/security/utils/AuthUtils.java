package com.andersen.chronology.security.utils;

import com.andersen.chronology.security.entities.AccountDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@UtilityClass
public class AuthUtils {

    public AccountDetails getAccountDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (Objects.isNull(securityContext)) {
            return null;
        }

        Authentication authentication = securityContext.getAuthentication();
        if (Objects.isNull(authentication)) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        return Objects.nonNull(principal) && principal instanceof AccountDetails accountDetails
                ? accountDetails : null;
    }
}
