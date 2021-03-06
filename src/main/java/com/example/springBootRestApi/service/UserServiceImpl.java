package com.example.springBootRestApi.service;

import com.example.springBootRestApi.model.User;
import com.example.springBootRestApi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new Error("ユーザーが存在しません。");
        }

        return userRepository.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User requestBody) {
        User user = getUser(id);
        user.setName(requestBody.getName() == null ? user.getName() : requestBody.getName());
        user.setAge(requestBody.getAge() == null ? user.getAge() : requestBody.getAge());
        user.setAddress(requestBody.getAddress() == null ? user.getAddress() : requestBody.getAddress());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        getUser(id);
        userRepository.deleteById(id);
    }
}
