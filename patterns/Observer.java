package patterns;

import java.util.ArrayList;
import java.util.List;

// Интерфейс для оповещения наблюдателей об изменении состояния животного
interface AnimalObservable {
    void addObserver(AnimalObserver observer);
    void removeObserver(AnimalObserver observer);
    void notifyObservers();
}

// Интерфейс наблюдателя, который будет получать уведомления об изменении состояния животного
interface AnimalObserver {
    void update(Animal animal);
}

// Класс животного, имплементирующий интерфейс AnimalObservable для оповещения наблюдателей
class Animal implements AnimalObservable {

    private String name;
    private int age;
    private List<AnimalObserver> observers = new ArrayList<>();

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        // Оповещаем наблюдателей об изменении возраста животного
        notifyObservers();
    }

    @Override
    public void addObserver(AnimalObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AnimalObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (AnimalObserver observer : observers) {
            observer.update(this);
        }
    }
}

// Класс наблюдателя, который будет получать уведомления об изменении состояния животного
class AnimalObserverImpl implements AnimalObserver {

    @Override
    public void update(Animal animal) {
        System.out.println("Животное " + animal.getName() + " изменило возраст на " + animal.getAge());
    }
}

// класс Animal реализует интерфейс AnimalObservable для оповещения наблюдателей об изменении возраста животного.
// Класс AnimalObserverImpl является конкретным наблюдателем, который будет получать уведомления об изменении состояния животного.
// В методе main мы создаем объекты классов Animal и AnimalObserverImpl, добавляем наблюдателей к животному,
// меняем возраст животного и удаляем одного из наблюдателей
public class Observer {
    public static void main(String[] args) {
        Animal animal = new Animal("Собака", 3);

        // Создаем несколько наблюдателей
        AnimalObserver observer1 = new AnimalObserverImpl();
        AnimalObserver observer2 = new AnimalObserverImpl();

        // Добавляем наблюдателей к животному
        animal.addObserver(observer1);
        animal.addObserver(observer2);

        // Изменяем возраст животного
        animal.setAge(4);

        // Удаляем одного из наблюдателей
        animal.removeObserver(observer1);

        // Изменяем возраст животного еще раз
        animal.setAge(5);
    }
}
