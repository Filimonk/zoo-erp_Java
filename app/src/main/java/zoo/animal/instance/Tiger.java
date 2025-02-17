package zoo.animal.instance;

import zoo.animal.type.Predator;
import zoo.ZooEntity;

public class Tiger extends Predator {
    public Tiger(String name) {
        super("Tiger: " + name, 7.5);
    }
}

