package pl.dicedev.simplyauth.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void addUser(String hash, String username) {
        // save to database
        System.out.println("User: " + username + "/" + hash);
    }
}
