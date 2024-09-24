package com.gmart.gmart_api.dto;

public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder {
        private String id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;

        private String token;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UserDto build() {
            UserDto user = new UserDto();
            user.setId(this.id);
            user.setFirstName(this.firstName);
            user.setLastName(this.lastName);
            user.setUsername(this.username);
            user.setEmail(this.email);
            user.setToken(this.token);
            return user;
        }
    }
}
