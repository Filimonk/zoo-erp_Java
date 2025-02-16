package zoo.InventoryManagement;

import zoo.ZooEntity;

public interface IInventoryManagement {
    public void register(ZooEntity);
    public String getName(ZooEntity);
    public int getNumber(ZooEntity);
    public void printAllEntities();
}

public abstract class InventoryManagement implements IInventoryManagement {
    @Override
    public String getName(ZooEntity zooEntity) {
        return zooEntity.getName();
    }
}

