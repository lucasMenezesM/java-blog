package com.iff.NexusBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iff.NexusBlog.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByEmailContainingIgnoreCase(String email);
  List<User> findByFirstNameContainingIgnoreCase(String firstName);
  List<User> findByLastNameContainingIgnoreCase(String lastName);
  List<User> findByActive(boolean active);

  @Query("SELECT u FROM User u WHERE u.email = :email")
  User findByEmail(@Param("email") String email);

  @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
  List<User> findByNameContaining(@Param("name") String name);

}
