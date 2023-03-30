package com.mantuIT.RestApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mantuIT.RestApplication.entity.Controy;
@Repository
public interface ControyRepository extends JpaRepository<Controy, Integer> {

}
