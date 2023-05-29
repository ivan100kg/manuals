package patterns;

abstract class Animal {

    // Шаблонный метод, определяющий алгоритм выполнения действий
    public final void performAction() {
        chooseAnimal();
        move();
        makeSound();
    }

    // Абстрактные методы, которые должны быть реализованы в подклассах
    protected abstract void chooseAnimal();
    protected abstract void move();
    protected abstract void makeSound();

}

// Подклассы класса Animal должны переопределять абстрактные методы, чтобы реализовать свою собственную логику.
// Например, рассмотрим класс Dog, который наследуется от класса Animal:
class Dog extends Animal {

    @Override
    protected void chooseAnimal() {
        System.out.println("Выбрана собака");
    }

    @Override
    protected void move() {
        System.out.println("Собака бегает по парку");
    }

    @Override
    protected void makeSound() {
        System.out.println("Гав-гав!");
    }

}

// шаблонный метод позволяет определить каркас выполнения определенной последовательности действий, которые должны выполняться в подклассах. 
// Этот шаблон полезен в ситуациях, когда у нас есть несколько объектов, выполняющих сходные действия, но имеющих свою уникальную реализацию.
public class TemplateMethod {
    public static void main(String[] args) {
        Dog dog = new Dog();
        // можем вызвать его метод performAction(), чтобы увидеть, как он реализует алгоритм из класса Animal
        dog.performAction();
    }
}
