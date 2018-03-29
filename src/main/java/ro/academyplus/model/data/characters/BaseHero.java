package ro.academyplus.model.data.characters;

import org.hibernate.annotations.Fetch;
import ro.academyplus.model.data.items.BaseItem;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "hero")
public abstract class BaseHero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "type", nullable= false)
    protected String type;
    @Column(name = "hitpoints", nullable = false)
    protected int hitpoints;
    @Column(name = "name", unique = true, nullable = false)
    protected String name;
    @Column(name = "baseDamage", nullable = false)
    protected int baseDamage;
    @Column(name = "baseArmor", nullable = false)
    protected int baseArmor;
    @Column(name = "critChance", nullable = false)
    protected int critChance;
    @Column(name = "level", nullable = false)
    protected int level;
    @Column(name = "xp", nullable = false)
    protected int xp;
    @Column(name = "hitChance", nullable = false)
    protected int hitChance;
    @Column(name = "inventorySize", nullable =false)
    protected int inventorySize;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BaseItem> inventory = new ArrayList<>();

    @Override
    public String toString() {
        return "BaseHero{" +
                "inventorySize=" + inventorySize +
                ", id=" + id +
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

    public String getName() {
        return name;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getBaseArmor() {
        return baseArmor;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getHitChance() {
        return hitChance;
    }

    public int getItemCount() {
        return inventory.size();
    }

    public BaseItem getItemByType(String type) {
        for (BaseItem it : inventory)
            if (type.equals(it.getItemType()))
                return it;
        return null;
    }

    public void addInvetoryItem(BaseItem item) {
        BaseItem ret;
        if (item != null && (ret = getItemByType(item.getItemType())) != null) {
            inventory.remove(ret);
            inventory.add(item);
        }
        inventory.add(item);
    }

    public boolean addItem(BaseItem item) {
        //TODO add some constraints regarding item types when they are implemented
        if (getItemCount() < inventorySize) {
            inventory.add(item);
            return true;
        }
        return false;
    }

    public boolean isAlive() {
        if (hitpoints > 0)
            return true;
        return false;
    }

    public boolean removeItem(BaseItem item) {//TODO may want to rewrite the equals and hash functions for items
        return inventory.remove(item);
    }

    public BaseHero(String type, String name, int baseDamage, int baseArmor, int critChance, int hitChance) {
        this.type = type;
        this.hitpoints = 100;//TODO handle the starting hp better
        this.name = name;
        this.baseDamage = baseDamage;
        this.baseArmor = baseArmor;
        this.critChance = critChance;
        this.level = 1;
        this.xp = 0;
        this.hitChance = hitChance;
        this.inventorySize = 5;
    }

    public void addExp(int expValue, int expFactor){
        double levelUp;

        double exp = expValue * (expFactor >= this.getLevel() ? (expFactor == this.getLevel() ? 1 : 2) : 0.5);
        if (exp >= 100) {
            levelUp = exp / 100;
            this.setLevel(this.getLevel() + (int)levelUp);
            this.xp = (int)(exp - 100 * (int)levelUp);
            for (int i = 0; i < (int)levelUp; i++)
                levelUp();
        }
        else
            this.xp = (int)exp;
    }

    private void levelUp() {//here implement the stats change
        hitpoints = hitpoints + hitpoints * 10 / 100;
        baseDamage = baseDamage + baseDamage * 10 / 100;
        baseArmor = baseArmor + baseArmor * 10 / 100;
        critChance = critChance + critChance * 10 / 100;
        hitChance += 1;
        inventorySize += 1;
        System.out.println("This mofo leveled up LEL");
    }

    public int computeItemStat(String itemType) {
        //TODO could implement some sort of enum, meeh too lazy
        int retVal = 0;
        for (BaseItem i : inventory)
            switch (itemType) {
                case "damage": retVal += i.getDmgModif();
                    break;
                case "armor": retVal += i.getArmorModfi();
                    break;
                case "crit": retVal += i.getCritModfi();
                    break;
                case "hit": retVal += i.getHitModif();
                    break;
            }
        return retVal;
    }

    public int calcDamageDone() {
        int baseDamage = this.baseDamage + computeItemStat("damage");
        if (calcHitChance()) {
            if (calcCritChance())
                return baseDamage * 2;
            else
                return baseDamage;
        }
        else
            return 0;
    }

    public void takeDamage(int trueDamage) {
        int baseArmor = this.baseArmor + computeItemStat("armor");
        trueDamage = trueDamage - trueDamage * baseArmor / 100;
        if (trueDamage < 0)
            trueDamage = 1;
        hitpoints -= trueDamage;
        System.out.println("New hp: " + hitpoints);
    }

    private boolean calcCritChance(){
        return Math.random() < (critChance + computeItemStat("crit") / 100);
    }


    private boolean calcHitChance(){
        return Math.random() < (hitChance + computeItemStat("hit") / 100);
    }

}
