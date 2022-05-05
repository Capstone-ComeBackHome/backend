package com.comebackhome.user.domain.repository;

import com.comebackhome.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long userId);
}
