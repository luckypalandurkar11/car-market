package com.project.service;

import com.project.entity.User;
import com.project.payload.LoginDto;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

 // @Autowired
 //   private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public String saveUser(User user, String role) {

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return "Username already exists";
        }

        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()){
            return "EmailId already exists";
        }
                 // 1st method to encrypt the password for security perpose
//      String encodedPassword = passwordEncoder.encode(user.getPassword());
//      user.setPassword(encodedPassword);

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(hashpw);

        user.setRole(role);
        userRepository.save(user);
        return "User created successfully";
    }

    public String verifyUser(LoginDto loginDto) {
       Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());

        if (opUser.isPresent()) {
            User user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                return jwtService.generateToken(user.getUsername());
            }
        }
        return null;
    }
}
