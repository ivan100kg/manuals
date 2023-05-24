package patterns;

import java.util.ArrayList;
import java.util.List;

// Общий интерфейс для всех компонентов
interface Animal {
    void run();
    void jump();
}

// Листовой компонент
class Cat implements Animal {
    public void run() {
        // Реализация бега для кошки
    }

    public void jump() {
        // Реализация прыжка для кошки
    }
}

// Листовой компонент
class Dog implements Animal {
    public void run() {
        // Реализация бега для собаки
    }

    public void jump() {
        // Реализация прыжка для собаки
    }
}

// Контейнерный компонент
class Zoo implements Animal {
    private List<Animal> animals = new ArrayList<>();

    public void add(Animal animal) {
        animals.add(animal);
    }

    public void remove(Animal animal) {
        animals.remove(animal);
    }

    public void run() {
        // Реализация бега для зоопарка, который выполняется для всех его дочерних компонентов животных
        for (Animal animal : animals) {
            animal.run();
        }
    }

    public void jump() {
        // Реализация прыжка для зоопарка, который выполняется для всех его дочерних компонентов животных
        for (Animal animal : animals) {
            animal.jump();
        }
    }
}

// В данном примере интерфейс Animal представляет собой общий интерфейс для всех животных в иерархии. 
// Два конкретных класса, Cat и Dog, представляют листовые компоненты, которые реализуют методы run() и jump(). 
// Класс Zoo представляет контейнерный компонент, который может содержать другие животные в виде списка animals. 
// Методы run() и jump() для объекта Zoo вызывают выполнение этих же методов для всех животных в списке animals.
// Таким образом, мы можем работать со всеми типами животных единообразно, независимо от того, 
// являются ли они листовыми или составными компонентами.

public class Composite {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.add(new Dog());
        zoo.add(new Cat());
        zoo.jump();
    }
}