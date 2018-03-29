package ro.academyplus.model.data.plebs.types;

import ro.academyplus.model.data.items.BaseItem;
import ro.academyplus.model.data.plebs.BasePleb;

import javax.persistence.Entity;

@Entity
public class GangstaBaws extends BasePleb {//gangsta baws calls 1 puradel for each time it takes damage => damage increases by puradelDamage every time he takes a hit
    private final int puradelDamage = 10;

    public GangstaBaws() {
        this(10, 10, 10, 10, 10);
    }

    public GangstaBaws(int baseDamage, int hitpoints, int damageReduction, int xpMultiplier, int droppedXp) {
        super(baseDamage, hitpoints, damageReduction, xpMultiplier, droppedXp);
        type = "gangstaBaws";
    }

    @Override
    public void takeDamage(int damageTaken) {
        super.takeDamage(damageTaken);
        baseDamage += puradelDamage;
    }
}
