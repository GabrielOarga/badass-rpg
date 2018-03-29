package ro.academyplus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.model.data.characters.BaseHero;
import ro.academyplus.model.data.map.Map;
import ro.academyplus.model.data.plebs.BasePleb;
import ro.academyplus.repository.BaseHeroRepository;
import ro.academyplus.repository.UserRepository;

@Service
public class ArenaService {

    @Autowired
    BattleService battleService;
    @Autowired
    BaseHeroRepository heroRepo;

    public boolean moveHero(String action, Map map) {
        boolean test = map.move(action);
        if (test) {
            map.getPlayerHero().addExp(100, 10);
            heroRepo.saveAndFlush(map.getPlayerHero());
            return true;
        }
        return false;
    }

    public void resolveCombat(String action, Map map, BaseHero hero){
        System.out.println("Start: " + map.getPlayerHero().toString());
        switch (action){
            case "flight":
                map.flee(0.9);
                break;
            case "fight":
                System.out.println((BasePleb)map.getContent(map.getPlayerX(), map.getPlayerY()));
                if (battleService.fight(hero, (BasePleb)map.getContent(map.getPlayerX(), map.getPlayerY())))
                    map.removeMob(map.getPlayerX(), map.getPlayerY());
                break;
        }
        System.out.println("End: " + map.getPlayerHero().toString());
    }
}
