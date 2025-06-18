package com.todowebsite.sample.demo.Entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (Objects.requireNonNull(role) == Role.USER) {
            return "ROLE_USER";
        }
        throw new IllegalArgumentException("Unknown role: " + role);
    }

    @Override
    public Role convertToEntityAttribute(String roleName) {
        if (roleName.equals("ROLE_USER")) {
            return Role.USER;
        }
        throw new IllegalArgumentException("Unknown role: " + roleName);
    }
}
