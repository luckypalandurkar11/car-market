package com.project.controller;

import com.project.entity.User;
import com.project.payload.JWTTokenDto;
import com.project.payload.LoginDto;
import com.project.service.AuthService;
import com.project.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/b2/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private OTPService otpService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
            ){
        String response = authService.saveUser(user, "ROLE_USER");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/getmessage")
//    public String getMessage(){
//        return "Hello";
//    }

    @PostMapping("/signin")
    public ResponseEntity<?> userSignIn(
            @RequestBody LoginDto loginDto
            ){
       String jwtToken = authService.verifyUser(loginDto);
       if(jwtToken != null){
           JWTTokenDto tokenDto = new JWTTokenDto();
           tokenDto.setToken(jwtToken);
           tokenDto.setTokenType("JWT");
           return new ResponseEntity<>(tokenDto, HttpStatus.OK);
       }
       return new ResponseEntity<>("Invalid Token", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/content-manager-signup")
    public ResponseEntity<?> createContentManagerAccount(
            @RequestBody User user
    ){
        String response = authService.saveUser(user, "ROLE_CONTENTMANAGER");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/blog-manager-signup")
    public ResponseEntity<?> createBlogManagerAccount(
            @RequestBody User user
    ){
        String response = authService.saveUser(user, "ROLE_BLOGMANAGER");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/message")
    public String getMassage(){
        return "JAY SHREE RAM";
    }

    @PostMapping("/login-otp")
    public ResponseEntity<?> generateOtp(
            @RequestParam String mobileNumber
    ){
        String otp = otpService.generateOtp(mobileNumber);
        return new ResponseEntity<>(otp +"  " + mobileNumber, HttpStatus.OK);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(
            @RequestParam String mobileNumber,
            @RequestParam String otp
    ){
        String status = otpService.validateOTP(mobileNumber, otp);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
