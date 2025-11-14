package com.carrental.repository;

import com.carrental.domain.User;
import com.carrental.domain.vo.Email;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(Email email);

    List<User> findAll();

    void save(User user);

    void saveAll(List<User> users);
}
