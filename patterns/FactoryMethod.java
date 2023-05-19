package patterns;

/**В этом примере определены интерфейсы Animal и AnimalFactory.
 * Классы Cat и Dog реализуют интерфейс Animal,
 * а классы CatFactory и DogFactory реализуют интерфейс AnimalFactory.
 * Таким образом, CatFactory и DogFactory могут создавать объекты Cat и Dog соответственно.
 * В методе main мы создаем экземпляры DogFactory и CatFactory, 
 * затем используя эти фабрики создаем объекты dog и cat. 
 * Наконец, мы вызываем метод makeSound() для каждого из них, 
 * чтобы произвести звук, свойственный этому животному. 
 */

interface Animal {
    void makeSound();
}

class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

interface AnimalFactory {
    Animal createAnimal();
}

class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}

class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        AnimalFactory dogFactory = new DogFactory();
        AnimalFactory catFactory = new CatFactory();

        Animal dog = dogFactory.createAnimal();
        Animal cat = catFactory.createAnimal();

        dog.makeSound(); // Output: Woof!
        cat.makeSound(); // Output: Meow!
    }
}