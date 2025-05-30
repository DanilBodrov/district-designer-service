package org.example.districtdesignerservice.service;

import lombok.RequiredArgsConstructor;
import org.example.districtdesignerservice.model.User;
import org.example.districtdesignerservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean registerUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            return false;
        }
        return true;
    }

    public boolean loginUser(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password).isPresent();
    }
}
