package patterns;

// паттерн Bridge на примере класса Animal, который будет иметь два свойства - тип животного и его звук

// интерфейс AnimalSound с единственным методом makeSound()
interface AnimalSound {
    void makeSound();
}

// два класса, которые будут реализовывать этот интерфейс
class MeowSound implements AnimalSound {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

class WoofSound implements AnimalSound {
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }
}

// абстрактный класс Animal, содерж ссылку на реализацию интерфейса AnimalSound.
// Он также имеет методы для установки и получения типа животного
abstract class Animal {
    protected AnimalSound animalSound;
    private String type;

    public Animal(String type, AnimalSound animalSound) {
        this.type = type;
        this.animalSound = animalSound;
    }

    public abstract void display();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AnimalSound getAnimalSound() {
        return animalSound;
    }

    public void setAnimalSound(AnimalSound animalSound) {
        this.animalSound = animalSound;
    }

    public void makeSound() {
        animalSound.makeSound();
    }
}

// два класса-наследника класса Animal - Cat и Dog.
// Они реализов метод display() для отображения информации о животном
class Cat extends Animal {
    public Cat(AnimalSound animalSound) {
        super("Cat", animalSound);
    }

    @Override
    public void display() {
        System.out.println("I am a " + getType());
    }
}

class Dog extends Animal {
    public Dog(AnimalSound animalSound) {
        super("Dog", animalSound);
    }

    @Override
    public void display() {
        System.out.println("I am a " + getType());
    }
}

// В этом примере мы использовали паттерн Bridge для разделения абстракции Animal и реализации AnimalSound. 
// Это позволяет нам легко изменять или расширять звуки животных, не затрагивая основную абстракцию Animal
public class Bridge {
    public static void main(String[] args) {
        Animal cat = new Cat(new MeowSound());
        cat.display();
        cat.makeSound();

        Animal dog = new Dog(new WoofSound());
        dog.display();
        dog.makeSound();
    }
}
