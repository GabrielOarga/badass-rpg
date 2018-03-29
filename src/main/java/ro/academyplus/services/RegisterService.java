package ro.academyplus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.academyplus.model.data.User;
import ro.academyplus.model.dto.UserDTO;
import ro.academyplus.repository.UserRepository;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CryptService cryptSrv;

    private boolean validateRegistration(UserDTO userDTO) {
        if (userRepository.findOneByEmail(userDTO.getEmail()) != null)
            return false;
        return true;
    }

    public void registerNewUser(UserDTO userDTO) throws Exception {
        if (!validateRegistration(userDTO)) {
            System.out.println("email already in db");
            throw new Exception("Invalid form data!");
        }

        //Changed and moved the crypt logic to a separate service hopefully didn't break anything
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(cryptSrv.hashPassword(userDTO.getPassword()));
        userRepository.saveAndFlush(user);
    }
}
