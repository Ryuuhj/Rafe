package com.project.rafe.repository;

import com.project.rafe.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUserByUserId(Long id);
    Optional<Users> findUserByUserEmail(String email);


}