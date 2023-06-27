package patterns;

import java.util.ArrayList;
import java.util.List;

// класс Анимал
class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Memento1 createMemento() {
        return new Memento1(name, age);
    }

    public void restore(Memento1 memento) {
        this.name = memento.getName();
        this.age = memento.getAge();
    }
}

// класс Memento1, который будет хранить состояние животного в определенный
// момент времени:
class Memento1 {
    private String name;
    private int age;

    public Memento1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// класс Caretaker, который будет управлять списком состояний Animal
class Caretaker {
    private List<Memento1> mementos = new ArrayList<>();

    public void addMemento(Memento1 memento) {
        mementos.add(memento);
    }

    public Memento1 getMemento(int index) {
        return mementos.get(index);
    }
}


// Теперь мы можем использовать паттерн Хранитель для сохранения и восстановления состояний объекта Animal. Например, 
// создадим объект Animal и несколько объектов Memento, которые будут хранить его состояния на разных этапах работы программы
public class Memento {
    public static void main(String[] args) {
        Animal animal = new Animal("Cat", 3);

        Caretaker caretaker = new Caretaker();
        caretaker.addMemento(animal.createMemento()); // сохраняем первое состояние животного

        animal.setAge(5);
        caretaker.addMemento(animal.createMemento()); // сохраняем второе состояние животного

        animal.setName("Dog");
        caretaker.addMemento(animal.createMemento()); // сохраняем третье состояние животного

        animal.restore(caretaker.getMemento(1)); // восстанавливаем второе состояние животного
        System.out.println(animal.getName() + " " + animal.getAge()); // вывод: Cat 5
    }
}
