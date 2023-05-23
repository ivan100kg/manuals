package patterns;

class Animal {
    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }

    public void makeSound() {
        System.out.println("Animal is making a sound");
    }
}

// скрывает детали реализации класса Animal, более удобный интерфейс для работы
// с животным
class AnimalFacade {
    private Animal animal;

    public AnimalFacade() {
        animal = new Animal();
    }

    public void feedAnimal() {
        animal.eat();
    }

    public void letAnimalSleep() {
        animal.sleep();
    }

    public void makeAnimalSound() {
        animal.makeSound();
    }
}

public class Facade {
    public static void main(String[] args) {
        AnimalFacade animalFacade = new AnimalFacade();
        animalFacade.feedAnimal(); // вызываем метод, чтобы животное поело
        animalFacade.letAnimalSleep(); // вызываем метод, чтобы животное спало
        animalFacade.makeAnimalSound(); // вызываем метод, чтобы животное издало звук
    }
}