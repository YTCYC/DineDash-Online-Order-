package com.onlineorder.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public record RegisterBody(
        String email,
        String password,
        @JsonProperty("first_name") String firstName,
        // the JsonProperty annotation is to convert front end snake case to camel case used in backend
        @JsonProperty("last_name") String lastName
) {
}
