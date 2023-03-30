package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.beans.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
