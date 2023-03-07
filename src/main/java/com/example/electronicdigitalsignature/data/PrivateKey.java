package com.example.electronicdigitalsignature.data;

import java.math.BigInteger;

public class PrivateKey {
    private BigInteger w;

    public PrivateKey(BigInteger w) {
        this.w = w;
    }

    public BigInteger getW() {
        return w;
    }

    public void setW(BigInteger w) {
        this.w = w;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrivateKey{");
        sb.append("w=").append(w);
        sb.append('}');
        return sb.toString();
    }
}
