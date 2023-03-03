package demo.vlasabo.springsecuritydemo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    USER_READ("users:read"),
    USER_WRITE("users:write")
    ;
    @Getter
    private final String permission;

}
