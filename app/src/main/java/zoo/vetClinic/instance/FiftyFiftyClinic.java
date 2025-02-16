package zoo.vetClinic.instance;

import java.util.Random;

import zoo.ZooEntity;

public class FiftyFiftyClinic extends VetClinic {
    private final Random random = new Random();

    @Override
    public boolean checkHealth(ZooEntity zooEntity) {
        return random.nextBoolean();
    }
}

