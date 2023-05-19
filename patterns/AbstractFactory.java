package patterns;
/**Можно создавать объекты разных подтипов животных, используя соответствующие фабрики,
 *  при этом не зная о том, как они создаются внутри каждой фабрики. 
 */


// определить интерфейсы для каждого вида животного 
interface Cat {
    void makeSound();
}

interface Dog {
    void makeSound();
}

// Затем мы можем создать несколько конкретных классов, реализующих эти интерфейсы.
// Например, пусть у нас будет класс DomesticCat, WildCat и класс DomesticDog, WildDog:
class DomesticCat implements Cat {
    public void makeSound() {
        System.out.println("Meow!");
    }
}

class WildCat implements Cat {
    public void makeSound() {
        System.out.println("Roar!");
    }
}

class DomesticDog implements Dog {
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class WildDog implements Dog {
    public void makeSound() {
        System.out.println("Howl!");
    }
}

// определить интерфейс или абстрактный класс для абстрактной фабрики:
abstract class AnimalFactory {
    public abstract Cat createCat();

    public abstract Dog createDog();
}

// создать конкр фабрики, для разных подтипов наших животных
class DomesticAnimalFactory extends AnimalFactory {
    public Cat createCat() {
        return new DomesticCat();
    }

    public Dog createDog() {
        return new DomesticDog();
    }
}

class WildAnimalFactory extends AnimalFactory {
    public Cat createCat() {
        return new WildCat();
    }

    public Dog createDog() {
        return new WildDog();
    }
}


public class AbstractFactory {
    public static void main(String[] args) {
        AnimalFactory domesticAnimalFactory = new DomesticAnimalFactory();
        Cat domesticCat = domesticAnimalFactory.createCat();
        Dog domesticDog = domesticAnimalFactory.createDog();

        domesticCat.makeSound(); // Meow!
        domesticDog.makeSound(); // Woof!

        AnimalFactory wildAnimalFactory = new WildAnimalFactory();
        Cat wildCat = wildAnimalFactory.createCat();
        Dog wildDog = wildAnimalFactory.createDog();

        wildCat.makeSound(); // Roar!
        wildDog.makeSound(); // Howl!
    }
}
