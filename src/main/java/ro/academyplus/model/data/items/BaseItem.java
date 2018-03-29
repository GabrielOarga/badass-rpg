package ro.academyplus.model.data.items;

import javax.persistence.*;

@Entity
@Table(name = "spawnedItems")
public abstract class BaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    protected String itemName;
    @Column(name = "dmgModif", nullable = false)
    protected int dmgModif;
    @Column(name = "armorModif", nullable = false)
    protected int armorModfi;
    @Column(name = "critModif", nullable = false)
    protected int critModfi;
    @Column(name = "hitModif", nullable = false)
    protected int hitModif;

    public String getItemType() {
        return itemType;
    }

    public String getUsableBy() {
        return usableBy;
    }

    @Column(name = "itemType", nullable = false)

    protected String itemType;
    @Column(name = "usableBy", nullable = false)
    protected String usableBy;

    public BaseItem(String itemName, int dmgModif, int armorModfi, int critModfi, int hitModif, String itemType, String usableBy) {
        this.itemName = itemName;
        this.dmgModif = dmgModif;
        this.armorModfi = armorModfi;
        this.critModfi = critModfi;
        this.hitModif = hitModif;
        this.itemType = itemType;
        this.usableBy = usableBy;
    }

    public String getItemName() {
        return itemName;
    }

    public int getDmgModif() {
        return dmgModif;
    }

    public int getArmorModfi() {
        return armorModfi;
    }

    public int getCritModfi() {
        return critModfi;
    }

    public int getHitModif() {
        return hitModif;
    }
}
