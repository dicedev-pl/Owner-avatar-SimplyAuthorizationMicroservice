package pl.dicedev.simplyauth;

import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootTest
class AppRunnerTests {

    @Test
    void getCode() {
        String password = "Ala:AlaMaKota";

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md.update(password.getBytes());
        byte[] digest = md.digest();

        // make MD5 hash
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        System.out.println("MD5: " + myHash);

        // encode to base64
        String myHashToBase64 = Base64.getEncoder().encodeToString(myHash.getBytes());
        System.out.println("Base64: " + myHashToBase64);

        // split into two parts
        String partOne = myHashToBase64.substring(0, 10);
        String partTwo = myHashToBase64.substring(10);
        System.out.println("p1: " + partOne);
        System.out.println("p2: "+ partTwo);
    }

}
