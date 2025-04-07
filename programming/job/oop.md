# Неделя 4: Проектирование классов и паттерны

## День 1-2: Глубокое изучение ООП

### 1. Инкапсуляция
Инкапсуляция — принцип ООП, при котором данные и методы, работающие с ними, объединяются в одном классе, а доступ к ним ограничивается.

**Пример:**
```java
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
```

### 2. Наследование
Позволяет создавать новые классы на основе существующих.

**Пример:**
```java
class Animal {
    protected String name;
    
    public void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        this.name = name;
    }
    
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}
```

### 3. Полиморфизм
Позволяет одному интерфейсу обрабатывать разные типы данных.

**Пример:**
```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing Square");
    }
}
```

### 4. Принципы SOLID
- **S** — Single Responsibility Principle (Принцип единственной ответственности)
- **O** — Open/Closed Principle (Принцип открытости/закрытости)
- **L** — Liskov Substitution Principle (Принцип подстановки Барбары Лисков)
- **I** — Interface Segregation Principle (Принцип разделения интерфейсов)
- **D** — Dependency Inversion Principle (Принцип инверсии зависимостей)

---

## День 3-4: Популярные паттерны

### 1. Singleton (Одиночка)
Обеспечивает наличие только одного экземпляра класса.
```java
class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### 2. Factory (Фабрика)
Создаёт объекты без указания их точного класса.
```java
interface Product {
    void use();
}

class ConcreteProductA implements Product {
    public void use() {
        System.out.println("Using Product A");
    }
}

class ProductFactory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        }
        return null;
    }
}
```

### 3. Strategy (Стратегия)
Позволяет менять алгоритм в ходе выполнения.
```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
```

### 4. Observer (Наблюдатель)
Позволяет объектам подписываться на изменения состояния другого объекта.
```java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private String name;
    
    public ConcreteObserver(String name) {
        this.name = name;
    }
    
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
```

---

## День 5-7: Практика
1. **Система оплаты** (Strategy)
2. **Логирование событий** (Observer)
3. **Генерация объектов** (Factory + Singleton)
