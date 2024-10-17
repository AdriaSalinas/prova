package com.example.astromatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.astromatch.model.UsersModel;
import com.example.astromatch.repository.UsersRepository;

@Service

public class UsersService {

    @Autowired

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String nombre, String apellido, Integer edad, String login, String password,
            String email) {
        if (login != null && password != null) {
            return null;
        } else {
            UsersModel usersModel = new UsersModel();
            usersModel.setNombre(nombre);
            usersModel.setApellido(apellido);
            usersModel.setEdad(edad);
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);

            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
