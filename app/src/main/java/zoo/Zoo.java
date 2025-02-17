package zoo;

import java.util.ArrayList;
import java.util.List;

import zoo.ZooEntity;
import zoo.animal.Animal;
import zoo.thing.Thing;
import zoo.inventoryManagement.InventoryManagement;
import zoo.vetClinic.VetClinic;

public class Zoo {
    private int numberOfAnimals = 0;
    private double totalAmountOfFood = 0;
    
    private List<ZooEntity> animals = new ArrayList<>();
    private List<ZooEntity> animalsOfContactSection = new ArrayList<>();
    private List<ZooEntity> thingsList = new ArrayList<>();
    
    private VetClinic vetClinic;
    private final InventoryManagement inventoryManagement;

    
    
    public Zoo(InventoryManagement inventoryManagement) {
        this.inventoryManagement = inventoryManagement;
    }
    public Zoo(InventoryManagement inventoryManagement, VetClinic vetClinic) {
        this.vetClinic = vetClinic;
        this.inventoryManagement = inventoryManagement;
    }
    
    public void setVetClinic(VetClinic vetClinic) {
        this.vetClinic = vetClinic;
    }
    
    
    
    private void printZooEntitiesList(List<ZooEntity> zooEntitiesList) {
        for (ZooEntity entity: zooEntitiesList) {
            System.out.print(inventoryManagement.getName(entity) + " ");
            System.out.println(inventoryManagement.getNumber(entity));
        }
        System.out.println();
    }
    
    
    
    public void addAnimal(Animal newAnimal) {
        if (vetClinic == null) {
            System.out.println("Чтобы добавить нового животного, нужно установить ветклинику.");
            return;
        }
        
        if (vetClinic.checkHealth(newAnimal)) {
            ++numberOfAnimals;
            totalAmountOfFood += newAnimal.getAmountOfFood();
            
            animals.add(newAnimal);
            
            if (newAnimal.isContact()) {
                animalsOfContactSection.add(newAnimal);
            }
            
            inventoryManagement.register(newAnimal);
            System.out.println("Животное здорово и успешно добавлено в зоопарк.");
        }
        else {
            System.out.println("Животное не здорово, поэтому оно не может быть принято.");
        }
    }
    
    public void printAnimals() {
        System.out.println("Вывод всех животных в формате \"Имя\" \"Инвентаризационный номер\":");
        printZooEntitiesList(animals);
    }
    
    public void printContactAnimals() {
        System.out.println("Вывод всех контактных животных в формате \"Имя\" \"Инвентаризационный номер\":");
        printZooEntitiesList(animalsOfContactSection);
    }
    
    public void printNumberOfAnimals() {
        System.out.println("Животных в зоопарке:");
        System.out.println(numberOfAnimals);
    }
    
    
    
    public void addThing(Thing newThing) {
        thingsList.add(newThing);
        inventoryManagement.register(newThing);
        System.out.println("Вещь успешно добавлена в зоопарк.");
    }
    
    public void printThings() {
        System.out.println("Вывод всего инвентаря в формате \"Имя\" \"Инвентаризационный номер\":");
        printZooEntitiesList(thingsList);
    }

    
    
    public void printAllInventoryEntities() {
        System.out.println("Вывод всех инвентаризированных сущностей в формате \"Имя\" \"Инвентаризационный номер\":");
        inventoryManagement.printAllEntities();
    }
    
    public void printTotalAmountOfFood() {
        System.out.println("Общее количество еды (в кг/сутки):");
        System.out.println(totalAmountOfFood);
    }
}

