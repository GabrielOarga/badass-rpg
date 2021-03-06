package ro.academyplus.model.data.characters.type;

import ro.academyplus.model.data.characters.BaseHero;

import javax.persistence.Entity;

@Entity
public class AdolfCritler extends BaseHero {

    public AdolfCritler() {
        super("AdolfCritler", "Default", 10, 10, 10, 60);
    }
    public AdolfCritler(String name) {
        super("AdolfCritler", name, 10, 10, 10, 60);
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
