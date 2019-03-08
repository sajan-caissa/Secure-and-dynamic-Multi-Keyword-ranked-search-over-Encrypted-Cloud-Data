/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

/**
 *
 * @author SAJAN
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public final class RSAKeyPair {

    private int keyLength;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    public RSAKeyPair(int keyLength)
            throws GeneralSecurityException {

        this.keyLength = keyLength;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(this.keyLength);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public final PrivateKey getPrivateKey() {
        return privateKey;
    }

    public final PublicKey getPublicKey() {
        return publicKey;
    }

    public final void toFileSystem(String privateKeyPathName, String publicKeyPathName)
            throws IOException {

        FileOutputStream privateKeyOutputStream = null;
        FileOutputStream publicKeyOutputStream = null;

        try {

            File privateKeyFile = new File(privateKeyPathName);
            File publicKeyFile = new File(publicKeyPathName);

            privateKeyOutputStream = new FileOutputStream(privateKeyFile);
            privateKeyOutputStream.write(privateKey.getEncoded());

            publicKeyOutputStream = new FileOutputStream(publicKeyFile);
            publicKeyOutputStream.write(publicKey.getEncoded());

        } catch (IOException ioException) {
            throw ioException;
        } finally {

            try {

                if (privateKeyOutputStream != null) {
                    privateKeyOutputStream.close();
                }
                if (publicKeyOutputStream != null) {
                    publicKeyOutputStream.close();
                }

            } catch (IOException ioException) {
                throw ioException;
            }
        }
    }
}
