package ro.academyplus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.academyplus.model.dto.UserDTO;
import ro.academyplus.services.RegisterService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    RegisterService regServ;

    @RequestMapping("/register")
    public String createObject(Model model) {
        model.addAttribute("userdto", new UserDTO());
        return "/register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String fillUserDTO(@ModelAttribute(value = "userdto") @Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Result had errors!");
            return "/register";
        }
        try {
            regServ.registerNewUser(userDTO);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "/register";
        }
        return "redirect:/index";
    }
}
