package com.maniek.software.workoutnotepad.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository{


    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByEmail(String email);


}
