package com.example.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

     @Transactional
     @Modifying
     @Query(value = "UPDATE member SET name=?1 , age=?2 WHERE id=?3", nativeQuery=true)
     void updateUser(String name, int age , int id);

     @Query(value = "SELECT * FROM member WHERE id=?1", nativeQuery = true)
     public User searchUserById(int id);

}
