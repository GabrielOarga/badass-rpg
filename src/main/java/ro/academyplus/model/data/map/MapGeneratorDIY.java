package ro.academyplus.model.data.map;

import ro.academyplus.model.data.map.storeable.MapStructure;
import ro.academyplus.model.data.map.storeable.StructureElement;
import ro.academyplus.model.data.plebs.BasePleb;
import ro.academyplus.model.data.plebs.types.Fenton;
import ro.academyplus.model.data.plebs.types.GangstaBaws;

import java.util.Random;

public class MapGeneratorDIY {
    private MapStructure mapStructure;
    private StructureElement structureElement;
    private int posX;
    private int posY;
    private BasePleb basePleb;

    public MapGeneratorDIY() {

    }

    public MapStructure createMapStructure () {
        mapStructure = new MapStructure();

        for(posX = 1; posX <= 21; posX++) {
            for(posY = 1; posY <= 21; posY++) {
                mapStructure.add(createStructureElement(posX, posY));
            }
        }
        mapStructure.setIntendedLevel(1);
        return mapStructure;
    }

    private  StructureElement createStructureElement(int x, int y) {

        if(Math.random() < 0.1) {
            this.structureElement = new StructureElement(x, y, createRandomPleb());
            return this.structureElement;
        }
        this.structureElement = new StructureElement(x, y, null);
        return this.structureElement;
    }

    private  BasePleb createRandomPleb() {
        Random rand = new Random();
        if (Math.random() < 0.5) {
            return new Fenton(rand.nextInt(10), rand.nextInt(30), rand.nextInt(5), rand.nextInt(5), rand.nextInt(100));
        }
        return new GangstaBaws(rand.nextInt(5), rand.nextInt(50), rand.nextInt(4), rand.nextInt(10), rand.nextInt(100));
    }
}