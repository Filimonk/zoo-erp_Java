package zoo.animal.type;

import zoo.animal.Animal;

public abstract class Predator extends Animal {
    private Predator(String name, double food) {
        this.name = name;
        
        if (food > 0 && food < 500) {
            this.food = food;
        }
        else {
            System.out.println("Введено не допустимое количество еды! Пожалуйста, введите количество в единицах кг/сутки.");
            return;
        }
        
        /*
        if (kindness > 0 && kindness < 11) {
            this.kindness = kindness;
        }
        else {
            System.out.println("Введено не допустимое количество доброты! Эта величина может быть только от 1 до 10.");
            return;
        }
        
        if (kindness > 5) {
            isContact = true;
        }
        */

        kindness = 0;       // При необходимости расширения контактной секции хищниками
        isContact = false;  // тут можно прописать логику с добротой и характеристикой контактности
    }
}

