package patterns;


class Animal {
    private String species;
    private int age;
    private boolean isWild;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.age = builder.age;
        this.isWild = builder.isWild;
    }

    public static class Builder {
        private String species;
        private int age;
        private boolean isWild;

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setIsWild(boolean isWild) {
            this.isWild = isWild;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }

    // геттеры/сеттеры для всех полей
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
        isWild = wild;
    }

}

// Эта реализация позволяет нам использовать необязательные поля в любом порядке при создании объекта,
// а также легко изменять список этих полей в будущем, если потребуется добавить новые свойства.
public class Builder {
    public static void main(String[] args) {
        Animal.Builder builder = new Animal.Builder();
        builder.setAge(20);
        builder.setSpecies("Tiger");
        builder.setIsWild(true);

        Animal animal = builder.build();
    }
    
}
