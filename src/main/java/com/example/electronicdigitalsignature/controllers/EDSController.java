package com.example.electronicdigitalsignature.controllers;

import com.example.electronicdigitalsignature.data.KeyPair;
import com.example.electronicdigitalsignature.data.PublicKey;
import com.example.electronicdigitalsignature.data.Sign;
import com.example.electronicdigitalsignature.utils.KeyGenerator;
import com.example.electronicdigitalsignature.utils.SignChecker;
import com.example.electronicdigitalsignature.utils.SignCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.math.BigInteger;

public class EDSController {
    @FXML
    private TextField signLeftS2;

    @FXML
    private TextField signRightS2;

    @FXML
    private TextArea messageAreaLeft;

    @FXML
    private TextArea messageAreaRight;

    @FXML
    private TextField privateKeyW;

    @FXML
    private TextField publicKeyLeftG;

    @FXML
    private TextField publicKeyLeftP;

    @FXML
    private TextField publicKeyLeftQ;

    @FXML
    private TextField publicKeyLeftY;

    @FXML
    private TextField publicKeyRightG;

    @FXML
    private TextField publicKeyRightP;

    @FXML
    private TextField publicKeyRightQ;

    @FXML
    private TextField publicKeyRightY;

    @FXML
    private TextField signLeftS1;

    @FXML
    private TextField signRightS1;

    @FXML
    private Button signStatus;

    private KeyPair keyPair;

    @FXML
    void onCheckSignAction() {

        BigInteger g = new BigInteger(publicKeyRightG.getText());
        BigInteger p = new BigInteger(publicKeyRightP.getText());
        BigInteger q = new BigInteger(publicKeyLeftQ.getText());
        BigInteger y = new BigInteger(publicKeyRightY.getText());
        PublicKey publicKey = new PublicKey(p, q, g, y);

        BigInteger s1 = new BigInteger(signRightS1.getText());
        BigInteger s2 = new BigInteger(signRightS2.getText());
        Sign sign = new Sign(messageAreaRight.getText(), s1, s2);

        boolean status = SignChecker.checkSign(sign, publicKey);
        if (status) {
            signStatus.setBackground(Background.fill(Paint.valueOf("green")));
        } else {
            signStatus.setBackground(Background.fill(Paint.valueOf("red")));
        }
    }

    @FXML
    void onCopyAction() {
        publicKeyRightG.setText(publicKeyLeftG.getText());
        publicKeyRightP.setText(publicKeyLeftP.getText());
        publicKeyRightQ.setText(publicKeyLeftQ.getText());
        publicKeyRightY.setText(publicKeyLeftY.getText());
        messageAreaRight.setText(messageAreaLeft.getText());
        signRightS1.setText(signLeftS1.getText());
        signRightS2.setText(signLeftS2.getText());
    }

    @FXML
    void onGenerateButtonClick() {
        keyPair = KeyGenerator.generateKeyPair();

        privateKeyW.setText(keyPair.getPrivateKey().getW().toString());

        publicKeyLeftG.setText(keyPair.getPublicKey().getG().toString());
        publicKeyLeftP.setText(keyPair.getPublicKey().getP().toString());
        publicKeyLeftQ.setText(keyPair.getPublicKey().getQ().toString());
        publicKeyLeftY.setText(keyPair.getPublicKey().getY().toString());
    }

    @FXML
    void onSignButtonClick() {
        Sign sign = SignCreator.createSign(messageAreaLeft.getText(), keyPair);
        signLeftS1.setText(sign.getS1().toString());
        signLeftS2.setText(sign.getS2().toString());
    }

}