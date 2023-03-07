package com.example.electronicdigitalsignature.data;

import java.math.BigInteger;

public class PublicKey {
    private BigInteger p;
    private BigInteger q;
    private BigInteger g;
    private BigInteger y;

    public PublicKey(BigInteger p, BigInteger q, BigInteger g, BigInteger y) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.y = y;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getG() {
        return g;
    }

    public void setG(BigInteger g) {
        this.g = g;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PublicKey{");
        sb.append("p=").append(p);
        sb.append(", q=").append(q);
        sb.append(", g=").append(g);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
