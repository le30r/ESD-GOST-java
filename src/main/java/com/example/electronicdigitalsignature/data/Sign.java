package com.example.electronicdigitalsignature.data;

import java.math.BigInteger;

public class Sign {
    private String message;
    private BigInteger s1;
    private BigInteger s2;

    public Sign(String message, BigInteger s1, BigInteger s2) {
        this.message = message;
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigInteger getS1() {
        return s1;
    }

    public void setS1(BigInteger s1) {
        this.s1 = s1;
    }

    public BigInteger getS2() {
        return s2;
    }

    public void setS2(BigInteger s2) {
        this.s2 = s2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sign{");
        sb.append("message='").append(message).append('\'');
        sb.append(", s1=").append(s1);
        sb.append(", s2=").append(s2);
        sb.append('}');
        return sb.toString();
    }
}
