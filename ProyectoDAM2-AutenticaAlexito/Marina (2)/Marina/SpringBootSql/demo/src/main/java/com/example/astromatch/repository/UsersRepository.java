package com.example.astromatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.astromatch.model.UsersModel;
    


public interface UsersRepository extends JpaRepository<UsersModel, Integer>{

    Optional<UsersModel> findByLoginAndPassword(String login,String password);
}
