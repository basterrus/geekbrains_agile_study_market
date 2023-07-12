package com.geekbrains.shop.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
// в новом api io.jsonwebtoken ключ нужно создавать таким образом - один раз и поместить его в secret.properties
// ключ уже создан - НЕ МЕНЯТЬ!!!!!
public class GenerateBASE64EncodedKey {
    public static void main(String[] args) {
        System.out.println(generate());
    }

    public static String generate(){
        SecretKey key=Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Encoders.BASE64.encode(key.getEncoded());
    }
}
