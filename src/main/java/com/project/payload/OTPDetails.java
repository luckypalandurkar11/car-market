package com.project.payload;

public class OTPDetails {

    private final String otp;
    private final long timestamp;


    public OTPDetails(String otp, long timestsmp) {
        this.otp = otp;
        this.timestamp = timestsmp;
    }

    public String getOtp() {
        return otp;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
