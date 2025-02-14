package zoo;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private int numberOfAnimals;
    private int totalAmountOfFood;
    
    private List<Animal> animals = new ArrayList<>();
    private List<Animal> animalsOfContactSection = new ArrayList<>();
    
    private VetClinic vetClinic;
    private final InventoryBase inventoryBase;

    public Zoo(InventoryBase inventoryBase) {
        this.inventoryBase = inventoryBase;
    }
    public Zoo(InventoryBase inventoryBase, VetClinic vetClinic) {
        this.vetClinic = vetClinic;
        this.inventoryBase = inventoryBase;
    }
    
    public void setVetClinic(VetClinic vetClinic) {
        this.vetClinic = vetClinic;
    }
    
    public void addAnimal(Animal newAnimal) {
        if (vetClinic.checkHealth(newAnimal) == true) {
            ++numberOfAnimals;
            totalAmountOfFood += newAnimal.getAmountOfFood();
            
            animals.add(newAnimal);
            
            if (newAnimal.isHerbo() == true && newAnimal.getAmountOfKindness() > 5) {
                animalsOfContactSection.add(newAnimal);
            }
            
            inventoryBase.register(newAnimal);
        }
        else {
            System.out.println("This animal is not healthy, so it is not accepted into the zoo.");
        }
    }
    
    public void printAnimals() {
        System.out.println("Вывод всех животных в формате \"Имя\" \"Инвентаризационный номер\":");
        for (int i = 0; i < animals.size(); ++i) {
            System.out.print(inventoryBase.getName(animals.get(i)) + " ");
            System.out.println(inventoryBase.getNumber(animals.get(i)));
        }
        System.out.println();
    }
    
    public void printContactAnimals() {
        System.out.println("Вывод всех контактных животных в формате \"Имя\" \"Инвентаризационный номер\":");
        for (int i = 0; i < animalsOfContactSection.size(); ++i) {
            System.out.print(inventoryBase.getName(animalsOfContactSection.get(i)) + " ");
            System.out.println(inventoryBase.getNumber(animalsOfContactSection.get(i)));
        }
        System.out.println();
    }
}

