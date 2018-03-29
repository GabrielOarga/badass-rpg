package ro.academyplus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.model.data.User;
import ro.academyplus.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    CryptService cryptSrv;


    public boolean shouldAuthenticate(String userEmail, String password) {
        //possible security flaw
        User authUser = userRepo.findOneByEmail(userEmail);
        if (authUser == null) {
            System.out.println("Username not found in database!");
            return false;
        }
        if (!cryptSrv.passEqual(password, authUser.getPassword())) {

            System.out.println(authUser.getEmail());
            System.out.println(authUser.getPassword());
            System.out.println("Wrong password hashes");
            return false;
        }
        return true;
    }
}
