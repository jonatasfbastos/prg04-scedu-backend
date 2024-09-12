package br.com.ifba.scedu.user.model;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRoleEnum(String role){
        this.role = role;
    }

}
