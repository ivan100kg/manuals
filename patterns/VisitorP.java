package patterns;

import java.util.ArrayList;
import java.util.List;

// Интерфейс "Посетитель"
interface Visitor {
    void visit(Cat cat);
    void visit(Dog dog);
}

// Абстрактный класс Animal, имеющий метод accept,
// который принимает в качестве параметра объект типа Visitor
abstract class Animal {
    public abstract void accept(Visitor visitor);
}

// Класс Cat, наследующийся от класса Animal
class Cat extends Animal {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Класс Dog, наследующийся от класса Animal
class Dog extends Animal {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Реализация интерфейса Visitor
class FeedingVisitor implements Visitor {
    @Override
    public void visit(Cat cat) {
        System.out.println("Даем еду кошке");
    }

    @Override
    public void visit(Dog dog) {
        System.out.println("Даем еду собаке");
    }
}

public class VisitorP {
    public static void main(String[] args) {
        // Создание списка животных
        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat());
        animals.add(new Dog());

        // Создание посетителя
        FeedingVisitor feedingVisitor = new FeedingVisitor();

        // Обход всех животных и вызов метода accept у каждого из них
        for (Animal animal : animals) {
            animal.accept(feedingVisitor);
        }
    }
}
