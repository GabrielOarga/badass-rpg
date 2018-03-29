package ro.academyplus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.model.data.User;
import ro.academyplus.model.data.characters.BaseHero;
import ro.academyplus.model.data.characters.type.AdolfCritler;
import ro.academyplus.model.data.characters.type.ChuckTesta;
import ro.academyplus.model.data.characters.type.NightelfMohawk;
import ro.academyplus.model.data.map.Map;
import ro.academyplus.model.data.map.MapGeneratorDIY;
import ro.academyplus.model.dto.BaseHeroDTO;
import ro.academyplus.repository.BaseHeroRepository;
import ro.academyplus.repository.UserRepository;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

@Service
public class IndexService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BaseHeroRepository heroRepository;
    
    public String returnAuthUserNameByEmail(boolean isAuth, String userEmail) {
        return (isAuth ? userRepository.findOneByEmail(userEmail).getName() : null);
    }

    public void createHeroFromIndex(BaseHeroDTO baseHeroDTO, String userEmail) {
        User user = userRepository.findOneByEmail(userEmail);

        System.out.println("Selected badass name is: " + baseHeroDTO.getHeroName() + " and user is: " + user);
        BaseHero toAdd;
        switch (baseHeroDTO.getHeroType()) {
            case "NightelfMohawk":
                System.out.println("Creating NightelfMohawk");
                toAdd = new NightelfMohawk(baseHeroDTO.getHeroName());
                break;
            case "ChuckTesta":
                System.out.println("Creating ChuckTesta");
                toAdd = new ChuckTesta(baseHeroDTO.getHeroName());
                break;
            case "AdolfCritler":
                System.out.println("Creating AdolfCritler");
                toAdd = new AdolfCritler(baseHeroDTO.getHeroName());
                break;
            default:
                System.out.println("No Badass created");
                toAdd = null;
                break;
        }
        System.out.println("New Badass saved to database");
        if (toAdd != null) {
            heroRepository.saveAndFlush(toAdd);
            user.addHero(toAdd);
            userRepository.saveAndFlush(user);
        }
    }

    public List<BaseHero> retrieveHeroList (boolean isAuth, String userEmail) {

        if (isAuth) {
            User user = userRepository.findOneByEmail(userEmail);
            if (user.getHeroes().isEmpty())
                return null;
            else
                return user.getHeroes();
        }
        else
            return null;
    }

    public boolean deleteHero(String userEmail, String heroName) {
        System.out.println("Email: " + userEmail + " name: " + heroName);
        User usr = userRepository.findOneByEmail(userEmail);
        if (usr == null)
            return false;
        List<BaseHero> userHeroes = usr.getHeroes();
        for (BaseHero hero : userHeroes) {//the remove in foreach may not work
            if (hero.getName().equals(heroName)) {
                boolean test = usr.removeHero(hero);
                heroRepository.delete(hero);
                userRepository.saveAndFlush(usr);
                System.out.println("Delete result is: " + test);
                return test;
            }
        }
        return false;
    }

    public BaseHero retrieveSelectedHero(String heroName) {
        return heroRepository.findOneByName(heroName);
    }

    //TODO Change to an actual map when it's generated, with index selection maybe
    public Map retrieveSelectedMap(BaseHero baseHero) {
        MapGeneratorDIY mapGeneratorDIY = new MapGeneratorDIY();

        return new Map(baseHero, mapGeneratorDIY.createMapStructure());
    }
}