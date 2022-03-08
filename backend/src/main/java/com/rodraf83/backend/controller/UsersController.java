package com.rodraf83.backend.controller;

import javax.validation.Valid;

import com.rodraf83.backend.model.entity.UsersEntity;
import com.rodraf83.backend.model.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200/")
public class UsersController {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersEntity save(@RequestBody @Valid UsersEntity usersEntity) {
        return usersRepository.save(usersEntity);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid UsersEntity updatedUser) {
        usersRepository.findById(id)
        .map(UsersEntity -> {
            updatedUser.setUsername(updatedUser.getUsername());
            updatedUser.setEmail(updatedUser.getEmail());
            updatedUser.setPassword(updatedUser.getPassword());
            return usersRepository.save(updatedUser);
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usersRepository.findById(id)
        .map(usersEntity -> {
            usersRepository.delete(usersEntity);
            return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado!"));
    }

    @GetMapping("{id}")
    public UsersEntity findById(@PathVariable Long id) {
        return usersRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado!"));
    }

}
