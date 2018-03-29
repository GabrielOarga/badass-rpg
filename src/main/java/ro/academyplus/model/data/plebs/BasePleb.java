package ro.academyplus.model.data.plebs;

import ro.academyplus.model.data.items.BaseItem;

import javax.persistence.*;

@Entity
@Table(name = "mapPlebs")
public abstract class BasePleb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column
    protected String type;
    @Column
    protected int baseDamage;
    @Column
    protected int hitpoints;
    @Column
    protected int damageReduction;//Flat damage reduction
    @Column
    protected int xpMultiplier;
    @Column
    protected int droppedXp;

    private int hitsTaken = 0;//Will be used for battle recap at the end (got some sort of an idea about how battle should work)

    public String getType() {
        return type;
    }

    public BasePleb(int baseDamage, int hitpoints, int damageReduction, int xpMultiplier, int droppedXp) {
        this.baseDamage = baseDamage;
        this.hitpoints = hitpoints;
        this.damageReduction = damageReduction;
        this.xpMultiplier = xpMultiplier;
        this.droppedXp = droppedXp;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public int getXpMultiplier() {
        return xpMultiplier;
    }

    public int getDroppedXp() {
        return droppedXp;
    }

    public int getHitsTaken() {
        return hitsTaken;
    }


    public boolean isAlive() {
        if (hitpoints > 0)
            return true;
        return false;
    }

    public void takeDamage(int damageTaken) {
        hitsTaken++;
        damageTaken = damageTaken - damageReduction;
        if (damageTaken < 0)
            damageTaken = 5;
        hitpoints -= damageTaken;
    }

    public int dealDamage() {
        return baseDamage;
    }

    public String toString() {
        return "Hi I am a pleb and I am called " + type + " " +
                "my stats are-> Damage: " + baseDamage +
                " DamageReduction: " + damageReduction +
                " Hitpoints: " + hitpoints +
                " and some more stuff oh well...";
    }
}
