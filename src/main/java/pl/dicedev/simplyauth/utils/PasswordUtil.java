package pl.dicedev.simplyauth.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PasswordUtil {

    public static String preparePasswdHash(String pw) {
        byte[] afterDecode = Base64.getDecoder().decode(pw);
        return new String(afterDecode, StandardCharsets.UTF_8);
    }
}
