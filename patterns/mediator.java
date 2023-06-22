package patterns;

import java.util.ArrayList;
import java.util.List;


interface Mediator {
    void addAnimal(Animal animal);
    void animalChanged(Animal animal);
}

// реализует интерфейс Mediator. В этом классе есть список всех животных и метод animalChanged, который вызывается, 
// когда изменяется состояние одного из животных. Внутри этого метода происходит обработка всех изменений состояний животных.
class AnimalMediator implements Mediator {
    private List<Animal> animals = new ArrayList<>();

    @Override
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public void animalChanged(Animal animal) {
        // Обработка изменений состояния животного
        for(Animal a : animals) {
            if(a != animal) {
                a.reactOnChanges();
            }
        }
    }
}

// абстрактный класс Animal, который имеет ссылку на посредника (Mediator) 
// У него есть методы makeSound и reactOnChanges, которые будут использоваться для взаимодействия между животными
abstract class Animal {
    protected Mediator mediator;

    public Animal(Mediator mediator) {
        this.mediator = mediator;
    }

    public void reactOnChanges() {
        System.out.println("Я - " + getClass().getSimpleName() + " и я что-то услышал!");
    }

    public void makeSound() {
        System.out.println(getClass().getSimpleName() + " издаёт звук!");
        mediator.animalChanged(this);
    }
}

// Существуют два типа животных: собаки и кошки. Они оба наследуются от класса Animal
class Dog extends Animal {
    public Dog(Mediator mediator) {
        super(mediator);
    }
}

class Cat extends Animal {
    public Cat(Mediator mediator) {
        super(mediator);
    }
}

public class mediator {
    public static void main(String[] args) {
        AnimalMediator mediator = new AnimalMediator();

        // экземпляры собаки и кошки, добавляю их в посредника и вызываю метод makeSound у собаки. 
        // Это приводит к изменению состояния животных и вызову метода reactOnChanges, который выводит сообщение о том, что животное услышало что-то
        
        Animal dog = new Dog(mediator);
        Animal cat = new Cat(mediator);

        mediator.addAnimal(dog);
        mediator.addAnimal(cat);

        dog.makeSound();
    }
}
