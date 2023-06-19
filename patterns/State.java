package patterns;

// Абстрактный базовый класс состояния животного
abstract class AnimalState {
    public abstract void eat();
    public abstract void move();
}

// Конкретный класс для спящего состояния животного
class SleepingAnimalState extends AnimalState {
    @Override
    public void eat() {
        System.out.println("Я не могу есть, я сплю");
    }

    @Override
    public void move() {
        System.out.println("Я не могу двигаться, я сплю");
    }
}

// Конкретный класс для бодрствующего состояния животного
class AwakeAnimalState extends AnimalState {
    @Override
    public void eat() {
        System.out.println("Я ем");
    }

    @Override
    public void move() {
        System.out.println("Я передвигаюсь");
    }
}

// Класс животное
class Animal {
    private AnimalState state;

    public Animal() {
        // По умолчанию установим животное в состояние "спит"
        setState(new SleepingAnimalState());
    }

    public void setState(AnimalState state) {
        this.state = state;
    }

    public void eat() {
        state.eat();
    }

    public void move() {
        state.move();
    }
}

// Тестирующий класс
public class State {
    public static void main(String[] args) {
        Animal animal = new Animal();

        // Попробуем покормить животное, когда оно спит
        animal.eat(); // Я не могу есть, я сплю

        // Переведем животное в состояние "бодрствует"
        animal.setState(new AwakeAnimalState());

        // Теперь попробуем покормить животное снова
        animal.eat(); // Я ем

        // Также можно изменять другие состояния животного, например:
        //animal.setState(new RunningAnimalState());
        //animal.move(); // Я бегу
    }
}
