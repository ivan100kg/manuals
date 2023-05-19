package patterns;

/**В этом примере у класса Animal есть приватный конструктор и статический 
 * метод getInstance, который возвращает единственный экземпляр класса Animal.
 * Если экземпляр еще не был создан, то метод getInstance создает новый экземпляр.
 * Если же экземпляр уже существует, то метод getInstance просто возвращает его. 
 */

class Animal {
    private static Animal instance;
    private String name;

    private Animal(String name) {
        this.name = name;
    }

    public static synchronized Animal getInstance(String name) {
        if (instance == null) {
            instance = new Animal(name);
        }
        return instance;
    }

    public void makeSound() {
        System.out.println("The " + name + " makes a sound");
    }
}


public class Singleton {
    Animal animal = Animal.getInstance("Kitty");
}
