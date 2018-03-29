package ro.academyplus.model.data.map;
import ro.academyplus.model.data.characters.BaseHero;
import ro.academyplus.model.data.map.storeable.MapStructure;
import ro.academyplus.model.data.map.storeable.StructureElement;
import java.util.List;
import java.util.Random;

public class Map {

    private BaseHero playerHero;
    private int size;
    private int playerX, playerY;
    private List<StructureElement> enemies;

    public Map() {

    }

    public String assemblePlebDisplay() {
        String retVal = "";
        for(StructureElement el : enemies) {
            if (el.getContent() != null)
                retVal += el.getX() + "_" + el.getY() + " ";
        }
        return retVal;
    }

    public boolean removeElement(StructureElement el) {
        return enemies.remove(el);
    }

    public Map(BaseHero player, MapStructure mapData) {
        size = mapData.getSize();
        playerX = size / 2 + 1;
        playerY = size / 2 + 1;
        this.playerHero = player;
        enemies = mapData.getMapElements();
    }

    public List<StructureElement> getEnemies() {
        return enemies;
    }

    public BaseHero getPlayerHero() {
        return playerHero;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }


    @Override
    public String toString() {
        return "Map{" +
                "player=" + playerHero +
                ", size=" + size +
                ", playerX=" + playerX +
                ", playerY=" + playerY +
                ", enemies=" + enemies +
                '}';
    }

    private StructureElement getContentRaw(int x, int y) {
        for (StructureElement el : enemies)
            if (el.getX() == x && el.getY() == y)
                return el;
        return null;
    }

    public Object getContent(int x, int y) {
        StructureElement el;

        el = getContentRaw(x, y);
        if (el != null)
            return el.getContent();
        else
            return null;
    }

    private String randomDir() {
        switch (new Random().nextInt(4)) {
            case 0:
                return "w";
            case 1:
                return "a";
            case 2:
                return "s";
            case 3:
                return "d";
        }
        System.out.println("Random dir o crapat");
        return null;
    }

    public boolean flee(double fleeChance) {//Todo: it's a chance between 0-1
        if (Math.random() < fleeChance) {
            Random rand = new Random();
            move(randomDir());
            return true;
        }
        return false;
    }

    public boolean removeMob(int x, int y) {
        return enemies.remove(getContentRaw(x, y));
    }

    public boolean move(String c) {
        int newX = playerX;
        int newY = playerY;
        switch (c) {
            case "d":
                newY--;
                break;
            case "a":
                newY++;
                break;
            case "w":
                newX--;
                break;
            case "s":
                newX++;
                break;
        }
        if (newX > 0 && newX <= size && newY > 0 && newY <= size) {
            playerY = newY;
            playerX = newX;
            return false;
        }
        return true;
    }
}
