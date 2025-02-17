package zoo.animal.instance;

import zoo.animal.type.Herbo;
import zoo.ZooEntity;

public class Rabbit extends Herbo {
    public Rabbit(String name) {
        super("Rabbit: " + name, 0.375, 9);
    }
}

