package patterns;

interface Animal {
    String getDescription();
}

class BasicAnimal implements Animal {
    @Override
    public String getDescription() {
        return "Basic animal";
    }
}

abstract class AnimalDecorator implements Animal {
    protected Animal decoratedAnimal;

    public AnimalDecorator(Animal decoratedAnimal) {
        this.decoratedAnimal = decoratedAnimal;
    }

    public String getDescription() {
        return decoratedAnimal.getDescription();
    }
}

class CanFlyDecorator extends AnimalDecorator {
    public CanFlyDecorator(Animal decoratedAnimal) {
        super(decoratedAnimal);
    }

    public String getDescription() {
        return decoratedAnimal.getDescription() + ", can fly";
    }
}

class CanSwimDecorator extends AnimalDecorator {
    public CanSwimDecorator(Animal decoratedAnimal) {
        super(decoratedAnimal);
    }

    public String getDescription() {
        return decoratedAnimal.getDescription() + ", can swim";
    }
}

public class Decorator {
    public static void main(String[] args) {
        Animal animal1 = new BasicAnimal();
        System.out.println(animal1.getDescription());   // "Basic animal"

        Animal animal2 = new CanFlyDecorator(new BasicAnimal());
        System.out.println(animal2.getDescription());   // "Basic animal, can fly"

        Animal animal3 = new CanSwimDecorator(new CanFlyDecorator(new BasicAnimal()));
        System.out.println(animal3.getDescription());   // "Basic animal, can swim"
    }

}
