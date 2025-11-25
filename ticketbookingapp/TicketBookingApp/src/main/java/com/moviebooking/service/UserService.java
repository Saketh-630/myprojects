package com.moviebooking.service;

import com.moviebooking.entity.User;

public interface UserService {

    User registerUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);
}

