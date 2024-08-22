package br.com.ifba.scedu.domain.entities.user.model;

public enum UserRoleEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoleEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
