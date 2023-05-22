package patterns;


// Интерфейс USB
interface USB {
    void connectWithUsbCable();
}


// адаптируемый класс, реализующий карту памяти
class MemoryCard {
    public void insert() {
        System.out.println("Карта памяти успешно вставлена!");
    }

    public void copyData() {
        System.out.println("Данные скопированы на компьютер!");
    }
}


// адаптер, у адаптера есть общий интерфейс с компьютером
class CardReader implements USB {
    private MemoryCard memoryCard;              // наш конкретный класс

    public CardReader(MemoryCard memoryCard) {  // принимаем конкретный класс в кострукторе
        this.memoryCard = memoryCard;
    }

    @Override
    public void connectWithUsbCable() {         // имплементируем метод
        this.memoryCard.insert();
        this.memoryCard.copyData();
    }
}

// Адаптируемый класс (карта памяти) становится одним из полей адаптера.
// Далее в методе имплементируемого интерфейса вертим методами карты
public class Adapter {
    public static void main(String[] args) {
        USB cardReader = new CardReader(new MemoryCard());
        cardReader.connectWithUsbCable();       // используем адаптер
    }
}
