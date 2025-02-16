package zoo.animal.instance;

import zoo.animal.type.Predator;
import zoo.ZooEntity;

public class Wolf extends Predator {
    public Wolf(String name, double food) {
        super("Wolf: " + name, 3);
    }
}

