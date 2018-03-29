package ro.academyplus.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.academyplus.Application;
import ro.academyplus.model.data.User;
import ro.academyplus.model.data.plebs.types.Fenton;
import ro.academyplus.model.data.map.storeable.MapStructure;
import ro.academyplus.model.data.map.storeable.StructureElement;
import ro.academyplus.repository.BaseHeroRepository;
import ro.academyplus.repository.MapRepository;
import ro.academyplus.repository.UserRepository;
import ro.academyplus.services.CryptService;
import ro.academyplus.services.IndexService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    @Autowired
    IndexService delService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CryptService cryptService;

    @Autowired
    BaseHeroRepository heroRepo;

    @Autowired
    MapRepository mapRepo;

    @RequestMapping("/test")
    @ResponseBody
    public String returnShit(HttpServletRequest request) {
        if (request.getSession().getAttribute("id") != null)
            return request.getSession().getAttribute("id").toString();
        else
        {
            request.getSession().setAttribute("id", Application.counter++);
            return request.getSession().getAttribute("id").toString();
        }
    }

    @RequestMapping("/createusr")
    @ResponseBody
    public String returnShit2() {

        /*User usr = new User();
        usr.setEmail("test_email");
        usr.setName("test_name");
        usr.setPassword(cryptService.hashPassword("test"));
        userRepo.saveAndFlush(usr);
        BaseHero test = new AdolfCritler("Kappa");
        test.addItem(new TestItem());
        usr.addHero(test);
        heroRepo.saveAndFlush(test);
        userRepo.saveAndFlush(usr);*/
        /*
        MapStructure struct = new MapStructure();
        struct.add(new StructureElement(10, 10, new Fenton(10, 11, 12, 13, 14)));
        mapRepo.saveAndFlush(struct);*/
        delService.deleteHero("rares.preda98@gmail.com", "Kaka");
        return "test running";

    }

    @RequestMapping("/shit")
    public String retStuff(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("authorized", false);
        else
            model.addAttribute("authorized", true);
        return "test";
    }

    @RequestMapping("/retrieveusr")
    @ResponseBody
    public String returnShit3() {
        MapStructure mapStructure = mapRepo.findOneById(1);
        if (mapStructure != null)
            mapRepo.delete(mapStructure);
        /*
        User usr = userRepo.findOneByEmail("test_email");
        if (usr != null) {
            return usr.toString();
        }
        else return "BOOM";*/
        return "Delete success";
    }
}
