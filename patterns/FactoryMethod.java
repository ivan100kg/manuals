abstract class Product {
    String name;
}

class ProductA extends Product {
    
}

class ProductB extends Product {

}

abstract class Creator {
    abstract public Product FactoryMethod();
}

class CreatorA extends Creator {
    @Override
    public Product FactoryMethod() {
        return new ProductA();
    }
}

class CreatorB extends Creator {
    @Override
    public Product FactoryMethod() {
        return new ProductB();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        Creator creator = new CreatorA();
        Product productA = creator.FactoryMethod();
        creator = new CreatorB();
        Product productB = creator.FactoryMethod();
    }
}