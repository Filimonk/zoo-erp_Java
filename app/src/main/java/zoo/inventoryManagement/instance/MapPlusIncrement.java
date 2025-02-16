package zoo.inventoryManagement.instance;

import java.util.Map;
import java.util.TreeMap;

import zoo.inventoryManagement.InventoryManagement;
import zoo.ZooEntity;

public class MapPlusIncrement extends InventoryManagement {
    private Map<ZooEntity, Integer> entityMap = new TreeMap<>();
    private int currentNumber = 1;

    @Override
    public void register(ZooEntity zooEntity) {
        entityMap.put(zooEntity, currentNumber);
        ++currentNumber;
    }

    @Override
    public int getNumber(ZooEntity zooEntity) {
        return entityMap.getOrDefault(zooEntity, -1);
    }

    @Override
    public void printAllEntities() {
        for (Map.Entry<ZooEntity, Integer> entry : entityMap.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
        }
    }
}

