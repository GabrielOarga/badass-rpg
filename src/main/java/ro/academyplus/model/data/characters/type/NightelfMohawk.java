package ro.academyplus.model.data.characters.type;

import ro.academyplus.model.data.characters.BaseHero;

import javax.persistence.Entity;

@Entity
public class NightelfMohawk extends BaseHero {

    public NightelfMohawk() {
        super("NightelfMohawk", "Default", 30, 30, 30, 60);
    }

    public NightelfMohawk(String name) {
        super("NightelfMohawk", name, 30, 30, 30, 60);
    }

    public String toString() {
        return "BaseHero{" +
                "inventorySize=" + inventorySize +
                ", id=" + super.getId() +
                ", type='" + type + '\'' +
                ", hitpoints=" + hitpoints +
                ", name='" + name + '\'' +
                ", baseDamage=" + baseDamage +
                ", baseArmor=" + baseArmor +
                ", critChance=" + critChance +
                ", level=" + level +
                ", xp=" + xp +
                ", hitChance=" + hitChance +
                '}';
    }
}
