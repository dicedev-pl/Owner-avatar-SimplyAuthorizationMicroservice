package pl.dicedev.simplyauth.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PasswordUtil {

    public static String decodePasswdFromBash64(String pw) {
        byte[] afterDecode = Base64.getDecoder().decode(pw);
        return new String(afterDecode, StandardCharsets.UTF_8);
    }

    public static String encodePasswdToMd5(String name, String password) {
        String toEncode = name + ":" + password;
        return DigestUtils.md5Hex(toEncode);
    }

}
