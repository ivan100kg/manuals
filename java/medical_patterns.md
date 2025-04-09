# 🧠 Шпаргалка по паттернам проектирования в Java

> Тематика: **Медицинская лаборатория**

---

## 💪 Порождающие паттерны

### 1. **Builder** — Построение сложного объекта шаг за шагом
**Когда использовать:** когда у объекта много параметров (например, отчёт анализа).

```java
LabReport report = new LabReport.Builder()
    .patientName("Иванов И.И.")
    .testType("COVID-19 PCR")
    .result("Отрицательно")
    .doctor("Петров С.С.")
    .date(new Date())
    .build();
```

---

### 2. **Factory Method** — Делегирование создания объектов подклассам
**Когда использовать:** когда логика создания зависит от параметров.

```java
public interface LabTest { void analyze(); }

public class BloodTest implements LabTest { public void analyze() { ... } }
public class CovidTest implements LabTest { public void analyze() { ... } }

public class LabTestFactory {
    public static LabTest create(String type) {
        return switch (type) {
            case "blood" -> new BloodTest();
            case "covid" -> new CovidTest();
            default -> throw new IllegalArgumentException();
        };
    }
}
```

---

### 3. **Singleton** — Один объект на приложение
**Когда использовать:** конфигурация, подключение к БД.

```java
public class Config {
    private static final Config INSTANCE = new Config();
    private Config() {}
    public static Config getInstance() { return INSTANCE; }
}
```

---

## 🛡️ Структурные паттерны

### 4. **Adapter** — Приведение интерфейсов к нужному формату
**Пример:** интеграция старой лаборатории через адаптер:

```java
interface NewLabSystem { void sendData(String data); }
class OldSystem { void transmit(String raw) { ... } }

class Adapter implements NewLabSystem {
    private final OldSystem oldSystem;
    public Adapter(OldSystem oldSystem) { this.oldSystem = oldSystem; }
    public void sendData(String data) { oldSystem.transmit(data); }
}
```

---

### 5. **Decorator** — Расширение функциональности без изменения кода
**Пример:** логирование при выполнении анализа:

```java
class LabTestLogger implements LabTest {
    private final LabTest base;
    public LabTestLogger(LabTest base) { this.base = base; }
    public void analyze() {
        System.out.println("Лог: Анализ начинается");
        base.analyze();
    }
}
```

---

### 6. **Facade** — Упрощённый интерфейс к подсистеме
**Пример:** оформление анализа для пациента.

```java
class LabFacade {
    public void performAnalysis(String patient, String testType) {
        var test = LabTestFactory.create(testType);
        System.out.println("Пациент: " + patient);
        test.analyze();
    }
}
```

---

## ⚙️ Поведенческие паттерны

### 7. **Strategy** — Взаимозаменяемые алгоритмы
**Пример:** расчёт стоимости по разным правилам:

```java
interface PriceStrategy { double calculate(double base); }
class DefaultPrice implements PriceStrategy { public double calculate(double base) { return base; } }
class DiscountPrice implements PriceStrategy { public double calculate(double base) { return base * 0.9; } }

class PriceCalculator {
    private PriceStrategy strategy;
    public void setStrategy(PriceStrategy strategy) { this.strategy = strategy; }
    public double calculate(double base) { return strategy.calculate(base); }
}
```

---

### 8. **Observer** — Подписка на событие
**Пример:** уведомление врача о новых результатах:

```java
interface Observer { void update(String result); }
class Doctor implements Observer { public void update(String result) { System.out.println("Результат: " + result); } }

class LabPublisher {
    private List<Observer> observers = new ArrayList<>();
    public void subscribe(Observer o) { observers.add(o); }
    public void publish(String result) {
        observers.forEach(o -> o.update(result));
    }
}
```

---

### 9. **Command** — Обёртка над действием
**Пример:** отменяемые операции в лаборатории:

```java
interface Command { void execute(); void undo(); }
class PrintReportCommand implements Command {
    public void execute() { System.out.println("Печать отчета"); }
    public void undo() { System.out.println("Отмена печати"); }
}
```

---

### 10. **Chain of Responsibility** — Последовательная обработка
**Пример:** цепочка валидации результатов анализа:

```java
abstract class Validator {
    protected Validator next;
    public Validator setNext(Validator next) { this.next = next; return next; }
    public void validate(String data) {
        if (check(data) && next != null) next.validate(data);
    }
    protected abstract boolean check(String data);
}

class NonEmptyValidator extends Validator {
    protected boolean check(String data) { return !data.isEmpty(); }
}

class RangeValidator extends Validator {
    protected boolean check(String data) { return Integer.parseInt(data) < 100; }
}
```

---

## 🌐 REST Best Practices

- DTO != Entity
- Используй `@RestController` + `@Service`
- Отдавай `ResponseEntity<DTO>`
- Используй `@ExceptionHandler` или `@ControllerAdvice`
- Поддерживай пагинацию, фильтрацию, сортировку

```java
@GetMapping("/patients")
public ResponseEntity<List<PatientDto>> getAll(@RequestParam int page) {
    return ResponseEntity.ok(service.getPatients(page));
}
```

---

## 📨 Kafka (Spring Kafka)

```java
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
```

```java
@KafkaListener(topics = "lab-results")
public void listen(String message) {
    log.info("Новый результат: {}", message);
}
```

---

## 🧵 WebSocket (Spring)

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
}
```

```java
@Controller
public class LabSocketController {
    @MessageMapping("/results")
    @SendTo("/topic/updates")
    public LabResult sendResult(LabResult result) {
        return result;
    }
}
```

---

## 🧩 CQRS (Command Query Responsibility Segregation)

**Command:**
```java
public record CreateTestCommand(String patientId, String testType) {}
```
**Handler:**
```java
public class CreateTestHandler {
    public void handle(CreateTestCommand command) {
        var test = new LabTest(command.testType());
        repository.save(test);
    }
}
```
**Query:**
```java
public record GetPatientResultsQuery(String patientId) {}
```
**Handler:**
```java
public class GetPatientResultsHandler {
    public List<ResultDto> handle(GetPatientResultsQuery query) {
        return resultRepository.findByPatient(query.patientId());
    }
}
```

---

## 🧠 DDD (Domain-Driven Design)

- Разделяй Core, Application, Infrastructure
- Используй Value Objects, Entities, Aggregates
- Контекст лаборатории = отдельная модель

```java
public record PatientId(UUID value) {}

public class LabTestAggregate {
    private PatientId patient;
    private List<Result> results;

    public void addResult(Result r) {
        results.add(r);
    }
}
```

---

## 📚 Рекомендованные книги:

- "Чистый код" — Роберт Мартин
- "Рефакторинг" — Мартин Фаулер
- "Паттерны проектирования" — GoF
- "Эффективная Java" — Джошуа Блох
- "DDD: Tackling Complexity" — Эрик Эванс

---

🎉 **Готово для печати и повседневной разработки!**
