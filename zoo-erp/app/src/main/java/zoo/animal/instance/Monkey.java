package zoo.animal.instance;

import zoo.animal.type.Herbo;
import zoo.ZooEntity;

public class Monkey extends Herbo {
    public Monkey(String name) {
        super("Monkey: " + name, 24.5, 4); // пускай эта обезьяна - горилла
    }
}

