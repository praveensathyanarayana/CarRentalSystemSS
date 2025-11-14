package com.carrental.repository.impl;

import com.carrental.domain.User;
import com.carrental.domain.vo.Email;
import com.carrental.repository.UserRepository;
import com.carrental.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileUserRepository implements UserRepository {
    private final FileStorageService fileStorageService;

    @Override
    public Optional<User> findByEmail(Email email) {
        List<User> users = fileStorageService.loadUsers();
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return fileStorageService.loadUsers();
    }

    @Override
    public void save(User user) {
        List<User> users = fileStorageService.loadUsers();
        users.removeIf(u -> u.getEmail().equals(user.getEmail()));
        users.add(user);
        fileStorageService.saveUsers(users);
    }

    @Override
    public void saveAll(List<User> users) {
        fileStorageService.saveUsers(users);
    }
}
