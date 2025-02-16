package zoo.animal;

import zoo.ZooEntity;

public interface IAnimal {
    public String getName();
    public double getAmountOfFood();
    public boolean isContact();
}

public abstract class Animal extends ZooEntity implements IAnimal {
    protected String name;
    protected double food;
    protected int kindness;
    protected boolean isContact;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getAmountOfFood() {
        return food;
    }

    @Override
    public boolean isContact() {
        return isContact;
    }
}

