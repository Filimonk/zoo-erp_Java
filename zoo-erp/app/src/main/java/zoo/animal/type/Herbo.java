package zoo.animal.type;

import zoo.animal.Animal;

public abstract class Herbo extends Animal {
    public Herbo(String name, double food, int kindness) {
        this.name = name;
        
        if (food > 0 && food < 500) {
            this.food = food;
        }
        else {
            System.out.println("Введено не допустимое количество еды! Пожалуйста, введите количество в единицах кг/сутки.");
            return;
        }
        
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
    }
}

