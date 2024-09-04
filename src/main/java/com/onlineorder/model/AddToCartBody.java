package com.onlineorder.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public record AddToCartBody(

        @JsonProperty("menu_id") Long menuId
        // the JsonProperty annotation is to convert front end snake case to camel case used in backend
) {
}
