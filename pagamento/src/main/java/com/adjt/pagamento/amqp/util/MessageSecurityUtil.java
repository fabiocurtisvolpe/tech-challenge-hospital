package com.adjt.pagamento.amqp.util;

import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class MessageSecurityUtil {

    private static final String ALGORITHM = "SHA256withRSA";

    // Assina um texto usando a Chave Privada
    public static String sign(String data, RSAPrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // Verifica a assinatura usando a Chave Pública
    public static boolean verify(String data, String signatureStr, RSAPublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(signatureBytes);
    }
}