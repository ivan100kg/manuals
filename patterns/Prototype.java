package patterns;

//класс Animal имеет два поля - species и sound, которые задаются в конструкторе. 
// Также есть метод makeSound(), который выводит сообщение, содержащее вид животного и звук, издаваемый им.
class Animal implements Cloneable {
    private String species;
    private String sound;

    public Animal(String species, String sound) {
        this.species = species;
        this.sound = sound;
    }

    public void makeSound() {
        System.out.println(species + " says " + sound);
    }

    // Метод clone() переопределен для того, чтобы объекты класса Animal можно было клонировать. 
    // Для этого мы вызываем метод clone() суперкласса и приводим результат к типу Animal.
    @Override
    public Animal clone() throws CloneNotSupportedException {
        return (Animal) super.clone();
    }
}

// Теперь можно создать новый объект Animal на основе уже существующего, используя метод clone()
public class Prototype {
    public static void main(String[] args) {
        Animal cat = new Animal("Cat", "Meow");
        try {
            Animal catClone = cat.clone();
            cat.makeSound();        // Выведет "Cat says Meow"
            catClone.makeSound();   // Выведет "Cat says Meow" - звук не изменился!
        } catch (CloneNotSupportedException ex) {}
       
    }

}
