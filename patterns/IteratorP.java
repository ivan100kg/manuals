package patterns;

import java.util.Iterator;

class Animal implements Iterable<String> {  // реализуем интерфейс Iterable
    private String[] names;
    private int index = 0;

    public Animal(String... names) {
        this.names = names;
    }

    @Override
    public Iterator<String> iterator() {
        return new AnimalIterator();        // возвращ новый итератор
    }

    private class AnimalIterator implements Iterator<String> {  // реализуем итератор во влож классе
                                                                // в данном случае итерируемся по массиву строк
        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public String next() {
            return names[index++];
        }
    }
}

public class IteratorP {
    public static void main(String[] args) {
        Animal myAnimals = new Animal("lion", "tiger", "bear");

        for (String animal : myAnimals) {   // теперь можно использовать foreach
            System.out.println(animal);     // lion tiger bear
        }
    }
}