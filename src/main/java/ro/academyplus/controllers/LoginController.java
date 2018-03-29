package ro.academyplus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.academyplus.model.dto.LoginDTO;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String initLogin(Model model) {
        model.addAttribute("logindto", new LoginDTO());
        return "login";
    }
}