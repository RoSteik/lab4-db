/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.controller.impl
 * Class: UserControllerImpl
 */

package com.rostyslav.controller.impl;

import com.rostyslav.controller.UserController;
import com.rostyslav.domain.User;
import com.rostyslav.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Optional<User> findById(String name) {
        return userService.findById(name);
    }

    @Override
    public int create(User user) {
        return userService.create(user);
    }

    @Override
    public int update(String name, User user) {
        return userService.update(name, user);
    }

    @Override
    public int delete(String name) {
        return userService.delete(name);
    }
}
