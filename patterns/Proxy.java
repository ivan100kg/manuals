package patterns;

interface Animal {
    void makeSound();
}

class RealAnimal implements Animal {
    private String sound;

    public RealAnimal(String sound) {
        this.sound = sound;
        System.out.println("Creating a real animal that makes the sound " + sound);
    }

    @Override
    public void makeSound() {
        System.out.println("The animal makes the sound " + sound);
    }
}

class AnimalProxy implements Animal {
    private RealAnimal realAnimal;

    public AnimalProxy(String sound) {
        System.out.println("Creating an animal proxy for sound " + sound);
        realAnimal = new RealAnimal(sound);
    }

    @Override
    public void makeSound() {
        System.out.println("Preparing to make animal sound");
        realAnimal.makeSound();
        System.out.println("Done making animal sound");
    }
}

public class Proxy {
    public static void main(String[] args) {
        Animal realAnimal = new RealAnimal("meow");
        realAnimal.makeSound(); // выводит "The animal makes the sound meow"
        Animal animalProxy = new AnimalProxy("woof");
        animalProxy.makeSound(); // выводит "Preparing to make animal sound", затем "The animal makes the sound woof", затем "Done making animal sound"
    }
}
