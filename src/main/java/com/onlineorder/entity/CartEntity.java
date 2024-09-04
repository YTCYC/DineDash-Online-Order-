package com.onlineorder.entity;

// entity class needed is for database mapping

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
// the above packages are from this dependency we added in build.gradle
// 'org.springframework.boot:spring-boot-starter-data-jdbc'

@Table("carts")  // spring boot use this annotation to connect to database specifically for table carts
public record CartEntity(
        @Id Long id, // @Id indicates primary id
        Long customerId,
        Double totalPrice
) {
}

