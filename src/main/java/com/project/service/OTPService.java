package com.project.service;

import com.project.entity.User;
import com.project.payload.OTPDetails;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    private final Map<String, OTPDetails> otpStorage = new HashMap<>();
    private static final int OTP_EXPIRY_TIME = 5;

    public String generateOtp(String mobileNumber) {

        Optional<User> opUser = userRepository.findByMobile(mobileNumber);
        if(opUser.isPresent()){
            String otp = String.format("%06d", new Random().nextInt(999999));
            OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());

            otpStorage.put(mobileNumber, otpDetails);
            return otp;
        }
        return "Mobile number not registered!";
    }

    public String validateOTP(String mobileNumber, String otp) {
        OTPDetails otpDetails = otpStorage.get(mobileNumber);

        if(otpDetails == null){
            return "OTP not generated";  //OTP not generated
        }

        //check if otp is expired
        long currentTime = System.currentTimeMillis();
        long otpTime = otpDetails.getTimestamp();
        long timeDifference = TimeUnit.MILLISECONDS.toMinutes(currentTime - otpTime);

        if(timeDifference > OTP_EXPIRY_TIME){
            otpStorage.remove(otp);    //Remove expired otp
            return "OTP expired";    // OTP expired
        }

        //Validate OTP
        if (otpDetails.getOtp().equals(otp)) {
            Optional<User> opUser = userRepository.findByMobile(mobileNumber);
            if (opUser.isPresent()) {
                String jwtToken = jwtService.generateToken(opUser.get().getUsername());
                return "OTP validated successfully! Token: " + jwtToken;
            }
            return "User not found!";
        }

        return "Invalid OTP!";
    }
}
