package com.mah.springbootjwteg.repository;

import com.mah.springbootjwteg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}