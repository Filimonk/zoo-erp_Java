package zoo.animal.instance;

import zoo.animal.type.Predator;
import zoo.ZooEntity;

public class Tiger extends Predator {
    public Tiger(String name, double food) {
        super("Tiger: " + name, 7.5);
    }
}

