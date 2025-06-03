# Вопросы и ответы для собеседования Java Middle Developer

## 1. Java Core
1. **В чем отличие `HashMap`, `TreeMap` и `LinkedHashMap`?**  
   - `HashMap` – не гарантирует порядок ключей, использует хеш-таблицу.  
   - `TreeMap` – хранит ключи в отсортированном порядке, реализует `NavigableMap`.  
   - `LinkedHashMap` – сохраняет порядок вставки элементов.

2. **Какие особенности есть у `ConcurrentHashMap`?**  
   - Позволяет безопасно работать с коллекцией в многопоточной среде.  
   - Разделяет данные на сегменты, снижая блокировки.

3. **Что такое `equals` и `hashCode`? Как они связаны?**  
   - `equals()` сравнивает объекты на логическое равенство.  
   - `hashCode()` возвращает числовое представление объекта.  
   - Если `equals()` возвращает `true`, то `hashCode()` должен быть одинаковым.

4. **В чем отличие `String`, `StringBuilder` и `StringBuffer`?**  
   - `String` – неизменяемый.  
   - `StringBuilder` – изменяемый, не потокобезопасный.  
   - `StringBuffer` – изменяемый, потокобезопасный.

5. **В чем отличие checked и unchecked исключений?**  
   - Checked (`IOException`) должны быть обработаны.  
   - Unchecked (`NullPointerException`) могут быть пропущены.

6. **Как работает `try-with-resources`?**  
   - Автоматически закрывает ресурсы, реализующие `AutoCloseable`.

## 2. Многопоточность и конкуренция
1. **В чем отличие `synchronized` и `Lock`?**  
   - `synchronized` блокирует объект, `Lock` более гибкий и дает возможность прерывания.

2. **Что такое `volatile` и зачем он нужен?**  
   - Гарантирует видимость изменений переменной между потоками.

3. **Как работает `ThreadPoolExecutor`?**  
   - Управляет пулом потоков, выполняет задачи асинхронно.

4. **В чем особенности `CompletableFuture`?**  
   - Позволяет писать асинхронный код без блокировок.

5. **Какие методы есть у `CountDownLatch` и `CyclicBarrier`?**  
   - `CountDownLatch.await()` – ждет пока счетчик станет 0.  
   - `CyclicBarrier.await()` – ждет пока все потоки дойдут до барьера.

## 3. Spring Framework
1. **В чем отличие `@Component`, `@Service` и `@Repository`?**  
   - `@Component` – универсальный компонент.  
   - `@Service` – бизнес-логика.  
   - `@Repository` – слой доступа к данным.

2. **Что такое IoC и DI в Spring?**  
   - IoC (Inversion of Control) – управление зависимостями.  
   - DI (Dependency Injection) – внедрение зависимостей.

3. **Как работает `@Transactional`?**  
   - Управляет транзакциями, открывает и закрывает их автоматически.

4. **В чем отличие `@RequestParam` и `@PathVariable`?**  
   - `@RequestParam` – параметры запроса (`?id=1`).  
   - `@PathVariable` – часть URL (`/users/1`).

5. **Как работает `Spring Security`?**  
   - Обеспечивает аутентификацию и авторизацию в приложении.

## 4. PostgreSQL
1. **Какие типы индексов есть в PostgreSQL?**  
   - B-Tree, Hash, GIN, GiST, BRIN.

2. **Что такое `VACUUM` и `ANALYZE`?**  
   - `VACUUM` удаляет неиспользуемые строки.  
   - `ANALYZE` обновляет статистику для оптимизации запросов.

3. **В чем разница `INNER JOIN`, `LEFT JOIN` и `RIGHT JOIN`?**  
   - `INNER JOIN` – только пересечения.  
   - `LEFT JOIN` – все из левой таблицы + совпадающие.  
   - `RIGHT JOIN` – все из правой таблицы + совпадающие.

4. **Что такое `CTE` (общие временные таблицы)?**  
   - Позволяет создавать временные таблицы внутри запроса (`WITH cte AS (...)`).

## 5. Git и GitLab
1. **Как восстановить удаленный коммит?**  
   - Использовать `git reflog` и `git cherry-pick` для восстановления.

2. **Как работает `git bisect`?**  
   - Помогает найти коммит, который сломал код, бинарным поиском.

3. **Как отменить `git rebase`?**  
   - `git reflog` → найти нужный коммит → `git reset --hard <hash>`.

4. **Как использовать GitLab CI/CD?**  
   - Настроить `.gitlab-ci.yml` с этапами сборки и деплоя.

5. **Как работает `git stash` и когда его использовать?**  
   - Сохраняет незакоммиченные изменения, чтобы переключиться на другую ветку.

## 6. Практические задачи
1. **Написать метод, который определяет, является ли строка палиндромом.**
   ```java
   public boolean isPalindrome(String str) {
       String reversed = new StringBuilder(str).reverse().toString();
       return str.equals(reversed);
   }
   ```
2. **Написать SQL-запрос для выборки пользователей, у которых день рождения в текущем месяце.**
   ```sql
   SELECT * FROM users WHERE EXTRACT(MONTH FROM birth_date) = EXTRACT(MONTH FROM CURRENT_DATE);
   ```
3. **Реализовать потокобезопасный Singleton.**  
   ```java
   public class Singleton {
       private static final Singleton INSTANCE = new Singleton();
       private Singleton() {}
       public static Singleton getInstance() { return INSTANCE; }
   }
   ```
4. **Написать метод, который удаляет дубликаты из списка.**  
   ```java
   public List<Integer> removeDuplicates(List<Integer> list) {
       return new ArrayList<>(new HashSet<>(list));
   }
   ```

