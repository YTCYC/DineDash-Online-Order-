package com.onlineorder.repository;
// org.springframework.data 是在build gradle里面加进去的dependency

import com.onlineorder.entity.CustomerEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {

    // SELECT * FROM customers WHERE first_name = :firstName
    List<CustomerEntity> findByFirstName(String firstName);


    // SELECT * FROM customers WHERE last_name = :lastName
    List<CustomerEntity> findByLastName(String lastName);


    // SELECT * FROM customers WHERE email = :email
    CustomerEntity findByEmail(String email);

    @Modifying // 表示要做的操作是写 这样spring boot会加锁之类 做一些优化
    @Query("UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE id = :id")
    void updateNameById(long id, String firstName, String lastName);

    @Modifying // 表示要做的操作是写 这样spring boot会加锁之类 做一些优化
    @Query("UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE email = :email")
    void updateNameByEmail(String email, String firstName, String lastName);
}

