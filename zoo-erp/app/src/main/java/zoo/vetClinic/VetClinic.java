package zoo.vetClinic;

import zoo.ZooEntity;

interface IVetClinic {
    public boolean checkHealth(ZooEntity zooEntity);
}

public abstract class VetClinic implements IVetClinic {
    @Override
    public abstract boolean checkHealth(ZooEntity zooEntity);
}

