package com.goit.restapiexample.service;

import com.goit.restapiexample.entity.Users;
import com.goit.restapiexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Users findByUsername(String username) {
        Optional<Users> user = repository.findById(username);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    public void saveUser(Users user) {
        Users findUser = findByUsername(user.getName());
        if (findUser != null) {
            findUser.setAge(user.getAge());
            findUser.setName(user.getName());
            findUser.setPasswordHash(user.getPasswordHash());
        }else {
            findUser = user;
        }
        repository.save(findUser);
    }

}
