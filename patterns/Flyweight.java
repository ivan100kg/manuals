package patterns;

import java.util.HashMap;
import java.util.Map;

class AnimalFactory {
    private static final Map<String, Animal> animals = new HashMap<>();

    public static Animal getAnimal(String species) {
        Animal animal = animals.get(species);

        if (animal == null) {
            switch (species) {
                case "cat":
                    System.out.println("Creating a new cat.");
                    animal = new Cat();
                    break;
                case "dog":
                    System.out.println("Creating a new dog.");
                    animal = new Dog();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown animal species: " + species);
            }

            animals.put(species, animal);
        } else {
            System.out.println("Returning existing " + species + ".");
        }

        return animal;
    }
}

interface Animal {
    void makeSound();
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow!");
    }
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }
}

// В данном примере мы создали фабрику AnimalFactory, которая создает объекты классов Cat и Dog. 
// При этом мы используем хэш-таблицу для хранения уже созданных объектов. 
// Если объект с нужным ключом найден, то мы его возвращаем, а если нет – создаем новый объект и добавляем его в хэш-таблицу.
// Таким образом, мы избегаем создания дублирующихся объектов классов Cat и Dog, экономя при этом оперативную память и ускоряя работу программы.
public class Flyweight {
    public static void main(String[] args) {
        Animal cat1 = AnimalFactory.getAnimal("cat");   // Creating a new cat.
        cat1.makeSound();

        Animal cat2 = AnimalFactory.getAnimal("cat");   // Returning existing cat.
        cat2.makeSound();

        Animal dog1 = AnimalFactory.getAnimal("dog");
        dog1.makeSound();

        Animal dog2 = AnimalFactory.getAnimal("dog");
        dog2.makeSound();
    }
}
