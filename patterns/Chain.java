package patterns;

abstract class Animal {
    protected Animal successor;
    protected int foodLimit;

    public Animal(int foodLimit) {
        this.foodLimit = foodLimit;
    }

    public void setSuccessor(Animal animal) {
        this.successor = animal;
    }

    public void eat(Food food) {
        if (food.getAmount() <= foodLimit) { // Проверяем, может ли текущий животное съесть данный тип еды
            System.out.println(this.getClass().getSimpleName() + " eats " + food.getName());
        } else if (successor != null) { // Если не может, передаем запрос следующему животному в цепочке
            System.out.println(this.getClass().getSimpleName() + " can't eat " + food.getName() + ". Passing to "
                    + successor.getClass().getSimpleName());
            successor.eat(food);
        } else { // Если нет подходящего животного в цепочке, сообщаем об этом
            System.out.println("No animal in the chain can eat " + food.getName());
        }
    }
}

class Herbivore extends Animal {
    public Herbivore() {
        super(Integer.MAX_VALUE); // Говорим, что травоядным подходит любой вид растительной пищи
    }
}

class Carnivore extends Animal {
    public Carnivore() {
        super(10); // Говорим, что хищникам подходит только мясо
    }
}

class Omnivore extends Animal {
    public Omnivore() {
        super(5); // Говорим, что всеядным подходит любая пища, но в ограниченном количестве
    }
}

class Food {
    private String name;
    private int amount;

    public Food(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}

public class Chain {
    public static void main(String[] args) {
        Herbivore herbivore = new Herbivore();
        Carnivore carnivore = new Carnivore();
        Omnivore omnivore = new Omnivore();

        herbivore.setSuccessor(omnivore);
        omnivore.setSuccessor(carnivore);

        Food grass = new Food("grass", 3);
        herbivore.eat(grass); // Herbivore eats grass

        Food meat = new Food("meat", 5);
        herbivore.eat(meat); // No animal in the chain can eat meat

        Food berries = new Food("berries", 10);
        herbivore.eat(berries); // Omnivore can't eat berries. Passing to Carnivore
    }

}
