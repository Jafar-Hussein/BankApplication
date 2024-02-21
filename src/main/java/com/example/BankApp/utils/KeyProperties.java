package com.example.BankApp.utils;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class KeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    /**
     * Konstruktor som genererar ett RSA-nyckelpar.
     */
    public KeyProperties() {
        KeyPair keyPair = KeyGenerator.generateRsaKey();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    /**
     * Denna metod används för att hämta den privata nyckeln.
     *
     * @return den privata nyckeln.
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Denna metod används för att hämta den publika nyckeln.
     *
     * @return den publika nyckeln.
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Denna metod används för att sätta den privata nyckeln.
     *
     * @param privateKey är den privata nyckeln.
     */
    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Denna metod används för att sätta den publika nyckeln.
     *
     * @param publicKey är den publika nyckeln.
     */
    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
