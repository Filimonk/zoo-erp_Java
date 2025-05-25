package zoo.thing;

import zoo.ZooEntity;

public abstract class Thing extends ZooEntity {
    @Override
    public String getName() {
        return name;
    }
}

