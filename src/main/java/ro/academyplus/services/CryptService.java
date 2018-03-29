package ro.academyplus.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CryptService {

    public String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public boolean passEqual(String password, String hash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         if (encoder.matches(password, hash))
             return true;
        else return false;

    }
}
