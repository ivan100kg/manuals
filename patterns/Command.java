package patterns;


// Интерфейс команды
interface AnimalCommand {
    void execute();
}

// Класс животного
class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Различные действия, выполняемые животным
    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

// Конкретная команда - кормление животного
class FeedAnimalCommand implements AnimalCommand {
    private Animal animal;

    public FeedAnimalCommand(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void execute() {
        animal.eat();
    }
}

// Конкретная команда - усыпление животного
class PutAnimalToSleepCommand implements AnimalCommand {
    private Animal animal;

    public PutAnimalToSleepCommand(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void execute() {
        animal.sleep();
    }
}

// Класс-отправитель, который инициирует выполнение команд
class AnimalInvoker {
    private AnimalCommand command;

    public void setCommand(AnimalCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

// Пример использования
// Здесь мы создаем интерфейс команды AnimalCommand, который имеет метод execute. 
// Затем мы создаем конкретные команды FeedAnimalCommand и PutAnimalToSleepCommand, 
// которые реализуют этот интерфейс. Обе команды принимают экземпляр класса Animal в своем конструкторе и выполняют соответствующие действия животного.
// Далее мы создаем класс-отправитель AnimalInvoker, который хранит текущую команду и вызывает ее метод execute по запросу.
// В примере мы создаем новый экземпляр класса AnimalInvoker и устанавливаем в него две различные команды: 
// кормление животного и усыпление животного. Затем для каждой команды мы вызываем метод executeCommand, который запускает соответствующую команду.
public class Command {
    public static void main(String[] args) {
        Animal cat = new Animal("Murzik", 3);
        AnimalCommand feedCommand = new FeedAnimalCommand(cat);
        AnimalCommand sleepCommand = new PutAnimalToSleepCommand(cat);

        AnimalInvoker invoker = new AnimalInvoker();

        // Установка и выполнение команды кормления животного
        invoker.setCommand(feedCommand);
        invoker.executeCommand();

        // Установка и выполнение команды усыпления животного
        invoker.setCommand(sleepCommand);
        invoker.executeCommand();
    }
}
