package ro.academyplus.services;

import org.springframework.stereotype.Service;
import ro.academyplus.model.data.characters.BaseHero;
import ro.academyplus.model.data.items.BaseItem;
import ro.academyplus.model.data.plebs.BasePleb;

@Service
public class BattleService {
    private BaseItem getDrop() {
        //handle item drops here
        return null;
    }

    public boolean fight(BaseHero hero, BasePleb pleb) {//returns true if hero won and false if hero lost
        System.out.println("ENTERED");
        if (pleb == null)
            return true;
        if (hero == null)
            return false;
        while (hero.isAlive() && pleb.isAlive()) {
            System.out.println("OKOKOKOKOK");
            hero.takeDamage(pleb.dealDamage());
            pleb.takeDamage(hero.calcDamageDone());
        }
        System.out.println("Out of service " + hero);
        return hero.isAlive();
    }
}
