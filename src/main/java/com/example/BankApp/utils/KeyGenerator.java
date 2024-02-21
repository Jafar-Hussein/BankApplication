package com.example.BankApp.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;


public class KeyGenerator {

    /**
     * Denna metod genererar ett RSA-nyckelpar.
     *
     * @throws IllegalStateException om något går fel.
     * @return ett RSA-nyckelpar.
     */
    public static KeyPair generateRsaKey() {

        KeyPair keyPair;

        try {
            //Skapar en KeyPaiGenerator instans för RSA-algoritmen.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //Initialiserar KeyPairGenerator med en nyckelstorlek på 2048 bitar.
            keyPairGenerator.initialize(2048);
            //Genererar ett nyckelpar.
            keyPair = keyPairGenerator.genKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return keyPair;
    }
}