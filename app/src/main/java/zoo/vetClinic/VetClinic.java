package zoo.vetClinic;

import zoo.ZooEntity;

public interface IVetClinic {
    public boolean checkHealth(ZooEntity zooEntity);
}

public abstract class VetClinic implements IVetClinic {
    @Override
    public abstract boolean checkHealth(ZooEntity zooEntity);
}

