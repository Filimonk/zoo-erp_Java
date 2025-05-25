package zoo.vetClinic.instance;

import java.util.Random;

import zoo.ZooEntity;
import zoo.vetClinic.VetClinic;

public class FiftyFiftyClinic extends VetClinic {
    private final Random random = new Random();

    @Override
    public boolean checkHealth(ZooEntity zooEntity) {
        return random.nextBoolean();
    }
}

