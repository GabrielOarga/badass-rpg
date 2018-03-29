package ro.academyplus.model.data.plebs.types;
import ro.academyplus.model.data.items.BaseItem;
import ro.academyplus.model.data.plebs.BasePleb;

import javax.persistence.Entity;

@Entity
public class Fenton extends BasePleb {//Fenton hase a 30% chance to chase and deal fucking 300% damage FENTOOOOON!!!!!!!!! (prolly 300% is too much naaaah) also fenton takes 50% more dmg cuz he is a motherfucker

    private final double damageTakenMultiplier = 0.5;
    private final int fentonChaseMultiplier = 3;
    public Fenton() {
        this(10, 10, 10, 10, 10);
    }
    public Fenton(int baseDamage, int hitpoints, int damageReduction, int xpMultiplier, int droppedXp) {
        super(baseDamage, hitpoints, damageReduction, xpMultiplier, droppedXp);
        type = "fenton";
    }

    @Override
    public int dealDamage() {
        if (Math.random() < 0.3)
            return super.dealDamage() * fentonChaseMultiplier;
        return super.dealDamage();
    }

    @Override
    public void takeDamage(int damageTaken) {//FUCK you fenton....
        damageTaken = damageTaken + (int)(damageTaken * damageTakenMultiplier);
        super.takeDamage(damageTaken);
    }
}
