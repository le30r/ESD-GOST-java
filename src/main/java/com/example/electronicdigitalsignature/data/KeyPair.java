package com.example.electronicdigitalsignature.data;

public class KeyPair {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KeysPair{");
        sb.append("publicKey=").append(publicKey);
        sb.append(", privateKey=").append(privateKey);
        sb.append('}');
        return sb.toString();
    }
}
