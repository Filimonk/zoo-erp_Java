package zoo.inventoryManagement;

import zoo.ZooEntity;

interface IInventoryManagement {
    public void register(ZooEntity zooEntity);
    public String getName(ZooEntity zooEntity);
    public int getNumber(ZooEntity zooEntity);
    public void printAllEntities();
}

public abstract class InventoryManagement implements IInventoryManagement {
    @Override
    public String getName(ZooEntity zooEntity) {
        return zooEntity.getName();
    }
}

