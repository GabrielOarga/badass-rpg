package ro.academyplus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.academyplus.model.data.map.Map;
import ro.academyplus.model.data.plebs.BasePleb;
import ro.academyplus.model.dto.MapDTO;
import ro.academyplus.services.ArenaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ArenaController {

    @Autowired
    ArenaService arenaService;

    @RequestMapping(value = "/arena", method = RequestMethod.GET)
    public String sendArenaData(Model model, HttpServletRequest request) {
        Map map = ((Map) request.getSession().getAttribute("selectedMap"));
        MapDTO mapDTO = new MapDTO();


        model.addAttribute("hero", map.getPlayerHero());
        model.addAttribute("map", map);
        model.addAttribute("mapDTO", mapDTO);
        model.addAttribute("pleb", map.getContent(map.getPlayerX(), map.getPlayerY()));
        return "/arena";
    }

    @RequestMapping(value = "/arena", method = RequestMethod.POST)
    public String retrieveActions(@ModelAttribute(value = "mapDTO") @Valid MapDTO mapDTO, HttpServletRequest request, BindingResult result) {
        if(result.hasErrors()) {
            System.out.println("Arena result has errors");
            return "/error";
        }
        Map map = ((Map) request.getSession().getAttribute("selectedMap"));

        switch(mapDTO.getActionType()) {
            case "movement":
                if (arenaService.moveHero(mapDTO.getActionValue(), map))
                    return "redirect:/index";
                request.getSession().setAttribute("selectedMap", map);
                return "redirect:/arena";
            case "combat":
                arenaService.resolveCombat(mapDTO.getActionValue(), map, map.getPlayerHero());
        }

        System.out.println(mapDTO.getActionType());
        System.out.println(mapDTO.getActionValue());
        return "redirect:/arena";
    }
}
