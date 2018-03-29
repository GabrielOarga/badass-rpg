package ro.academyplus.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.academyplus.model.dto.ActionDTO;
import ro.academyplus.model.dto.BaseHeroDTO;
import ro.academyplus.services.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @RequestMapping("/index")
    public String returnIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuth = !auth.getName().equalsIgnoreCase("anonymousUser");

        model.addAttribute("actionDTO", new ActionDTO());
        model.addAttribute("retrievedHeroList", indexService.retrieveHeroList(isAuth, auth.getName()));
        model.addAttribute("baseHeroDTO", new BaseHeroDTO());
        model.addAttribute("authorized", isAuth);
        model.addAttribute("userName", indexService.returnAuthUserNameByEmail(isAuth, auth.getName()));
        return "index";
    }

    @RequestMapping("/")
    public String redirectSlashToIndex() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String baseHeroCreation(@ModelAttribute(value = "baseHeroDTO") @Valid BaseHeroDTO baseHeroDTO, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();//checking if the user is logged in, more secure this way
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            if (result.hasErrors()) {
                System.out.println("Result has errors!");
                return "/index";
            }
            indexService.createHeroFromIndex(baseHeroDTO, auth.getName());
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/selection_action", method = RequestMethod.POST)
    public String selectAction(@ModelAttribute(value = "actionDTO") @Valid ActionDTO actionDTO, HttpServletRequest request, BindingResult result) {

        System.out.println(actionDTO.getSelectedHeroName());
        System.out.println(actionDTO.getSelectedAction());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        switch (actionDTO.getSelectedAction()) {
            case "SelectForPlay" :
                System.out.println("Hero ready for play");
                request.getSession().setAttribute("selectedMap", indexService.retrieveSelectedMap(indexService.retrieveSelectedHero(actionDTO.getSelectedHeroName())));
                return "redirect:/arena";
            case "SelectForDeletion" :
                System.out.println("Hero deleted, bye bye");
                indexService.deleteHero(auth.getName(), actionDTO.getSelectedHeroName());
                return "redirect:/index";
            default :
                System.out.println("This is the default case, something went wrong");
                //TODO build error page :))
                return "/error";
        }
    }
}