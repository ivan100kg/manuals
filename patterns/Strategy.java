package patterns;

// Интерфейс стратегии
interface MoveStrategy {
    void move();
}

// Классы конкретных стратегий
class WalkStrategy implements MoveStrategy {
    public void move() {
        System.out.println("Идти");
    }
}

class RunStrategy implements MoveStrategy {
    public void move() {
        System.out.println("Бежать");
    }
}

// Контекст
// Класс Animal является контекстом, который содержит ссылку на текущую стратегию moveStrategy. 
// Метод move() вызывает метод move() текущей стратегии.
class Animal {
    private MoveStrategy moveStrategy;
    
    public Animal(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }
    
    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }
    
    public void move() {
        moveStrategy.move();
    }
}

// Пример использования
public class Strategy {
    public static void main(String[] args) {
        Animal animal1 = new Animal(new WalkStrategy());
        animal1.move(); // Идти
        
        // Смена стратегии на "бежать"
        animal1.setMoveStrategy(new RunStrategy());
        animal1.move(); // Бежать
    }
}
