package zoo;

import java.util.Scanner;

import zoo.Zoo;
import zoo.vetClinic.VetClinic;
import zoo.vetClinic.instance.FiftyFiftyClinic;
import zoo.inventoryManagement.InventoryManagement;
import zoo.inventoryManagement.instance.MapPlusIncrement;

import zoo.animal.Animal;
import zoo.animal.instance.Monkey;
import zoo.animal.instance.Rabbit;
import zoo.animal.instance.Tiger;
import zoo.animal.instance.Wolf;

import zoo.thing.Thing;
import zoo.thing.instance.Table;
import zoo.thing.instance.Computer;

public class App {
    public static void main(String[] args) {
        InventoryManagement mapPlusIncrement = new MapPlusIncrement();
        Zoo moscowZoo = new Zoo(mapPlusIncrement);
        
        VetClinic fiftyFiftyClinic = new FiftyFiftyClinic();
        moscowZoo.setVetClinic(fiftyFiftyClinic);
        
        Scanner scanner = new Scanner(System.in);
        String word;
        
        System.out.println("Программа начала свое выполнение.");
        
        while (true) {
            word = scanner.next();
            word = word.toLowerCase();

            if (word.equals("add")) {
                word = scanner.next();
                word = word.toLowerCase();
                
                if (word.equals("monkey")) {
                    word = scanner.next();
                    moscowZoo.addAnimal(new Monkey(word));
                }
                else if (word.equals("rabbit")) {
                    word = scanner.next();
                    moscowZoo.addAnimal(new Rabbit(word));
                }
                else if (word.equals("tiger")) {
                    word = scanner.next();
                    moscowZoo.addAnimal(new Tiger(word));
                }
                else if (word.equals("wolf")) {
                    word = scanner.next();
                    moscowZoo.addAnimal(new Wolf(word));
                }
                else if (word.equals("table")) {
                    moscowZoo.addThing(new Table());
                }
                else if (word.equals("computer")) {
                    moscowZoo.addThing(new Computer());
                }
                else {
                    System.out.print("Введена не верная сущность " + word);
                    System.out.println(", переходим к следующему запросу.");
                }
            }
            else if (word.equals("printanimals")) {
                moscowZoo.printAnimals();
            }
            else if (word.equals("printcontactanimals")) {
                moscowZoo.printContactAnimals();
            }
            else if (word.equals("printnumberofanimals")) {
                moscowZoo.printNumberOfAnimals();
            }
            else if (word.equals("printallinventoryentities")) {
                moscowZoo.printAllInventoryEntities();
            }
            else if (word.equals("printtotalamountoffood")) {
                moscowZoo.printTotalAmountOfFood();
            }
            else if (word.equals("exit")) {
                return;
            }
            else if (word.equals("help")) {
                
            }
            else {
                System.out.print("Введена не верная команда " + word);
                System.out.println(", переходим к следующему запросу.");
            }
        }
    }
}

