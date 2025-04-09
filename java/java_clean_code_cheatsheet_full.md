# ☕ Java Clean Code Шпаргалка (2025 Edition)

## 🧠 Общие принципы

| Принцип | Описание |
|--------|----------|
| 🧼 KISS | Keep It Simple, Stupid — не усложняй |
| 📐 SRP | Один класс = одна ответственность |
| 🧱 DRY | Не повторяйся |
| 🔁 YAGNI | Не пиши, пока не нужно |
| 🧹 Чистый код | Названия, читаемость, минимализм |
| 🔄 Refactoring | Маленькие изменения — большая разница |
| ✅ TDD/BDD | Пиши тесты! Unit / Integration / Contract |

---

## 🧾 Классы и архитектура

### ✅ Структура классов

```java
public class OrderService {
    public OrderDto createOrder(CreateOrderCommand command) { ... }
}
```

- Классы: **Service**, **Controller**, **Repository**, **Facade**, **Helper**
- Избегай утилитарных god-классов

---

## ✍️ Именование

| Что         | Пример               | Комментарий                        |
|-------------|----------------------|------------------------------------|
| Класс       | `UserService`        | Существительное                    |
| Метод       | `createOrder()`      | Глагол + объект                    |
| Переменная  | `createdAt`          | Ясно отражает смысл                |
| Константа   | `MAX_RETRIES`        | `UPPER_SNAKE_CASE`                |

---

## 💡 Современные фичи Java

| Версия | Возможности |
|--------|-------------|
| Java 8  | Stream API, Optional, Lambda, Method ref |
| Java 11 | HttpClient, `var`, Files API             |
| Java 14+| `record`, `switch` как выражение, Text Blocks |
| Java 17 | Sealed classes, Pattern Matching         |
| Java 21 | Virtual Threads (Project Loom!)          |

---

## 🧱 DTO / Entity / Builder

### DTO (Java 8 compatible)

```java
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderDto {
    private Long id;
    private String status;
    private BigDecimal amount;
}
```

### DTO (Java 16+)

```java
public record OrderDto(Long id, String status, BigDecimal amount) {}
```

---

## 📦 Repository (Spring Data JPA)

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
}
```

---

## ⚙️ Service Layer

```java
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;

    public OrderDto create(OrderDto dto) {
        Order order = map(dto);
        repo.save(order);
        return map(order);
    }
}
```

---

## 🌐 Controller (REST)

```java
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto dto) {
        return ResponseEntity.status(201).body(service.create(dto));
    }
}
```

---

## 📨 Kafka Producer

```java
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String, OrderEvent> kafka;

    public void send(OrderEvent event) {
        kafka.send("orders.topic", event.id().toString(), event);
    }
}
```

---

## 🧼 Чистый код (по Uncle Bob)

### ✅ Правильно

- 🔹 Маленькие методы (до 10–15 строк)
- 🔹 Один уровень абстракции на метод
- 🔹 Названия → смысл, не технические детали
- 🔹 Комментарии — только если **нужны**, не вместо кода
- 🔹 Код самодокументируемый
- 🔹 Исключения, а не коды ошибок
- 🔹 Dependency Injection

### ❌ Избегай

- ❌ boolean-флагов для управления логикой
- ❌ `instanceof` и `if`-деревьев (используй полиморфизм)
- ❌ длинных `switch-case`
- ❌ God-объектов, менеджеров всего
- ❌ утечек абстракций

---

## 📐 Паттерны проектирования

| Категория | Паттерны |
|-----------|----------|
| Создание  | `Builder`, `Factory`, `Singleton` |
| Структура | `Adapter`, `Decorator`, `Composite`, `Facade` |
| Поведение | `Strategy`, `Observer`, `Command`, `Chain of Responsibility` |

---

## 🛠 Рефакторинг

| Вариант          | Улучшение                        |
|------------------|----------------------------------|
| Extract Method   | Декомпозиция кода                |
| Replace Temp     | Вынеси переменные в методы       |
| Inline Variable  | Убери лишние переменные          |
| Replace If w/ Polymorphism | Избавься от `if`         |
| Split Loop       | Делай одно — в одном проходе     |

---

## 🔥 Современные фреймворки

| Назначение  | Инструмент                     |
|-------------|-------------------------------|
| Web         | Spring Boot, Micronaut, Helidon |
| Kafka       | Spring Kafka, Apache Kafka client |
| Database    | Spring Data JPA, Flyway        |
| Mapping     | MapStruct, ModelMapper         |
| Документация| Swagger (OpenAPI)              |
| Тесты       | JUnit 5, Mockito, Testcontainers |

---

## ✅ Тесты (JUnit 5)

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock OrderRepository repository;
    @InjectMocks OrderService service;

    @Test
    void createOrder_success() {
        OrderDto dto = new OrderDto(...);
        when(repository.save(any())).thenReturn(new Order(...));
        OrderDto result = service.create(dto);
        assertEquals("NEW", result.getStatus());
    }
}
```

---

## 🧪 Статическая проверка

- 🔍 **Checkstyle** / **SpotBugs**
- 📦 **SonarQube**
- ⚡ **Lombok**
- 🔬 **ArchUnit**

---

## 📌 Maven базовый `pom.xml`

```xml
<properties>
  <java.version>17</java.version>
</properties>

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
</dependencies>
```

---

## 💬 Заключение

- 📚 Пиши код так, будто читать его будет психопат с паяльником
- ✂️ Минимум зависимостей
- 🧩 Минимум логики в контроллерах
- ♻️ Повторно используй бизнес-логику
- ✅ Покрытие тестами — must have
- 🚀 Стартуй просто — улучшай по мере роста

> "First make it work, then make it right, then make it fast." — Kent Beck