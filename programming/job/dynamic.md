## День 1-2: Основы DP

# 📌 Основы DP

## Разбиение задачи на подпроблемы
Динамическое программирование (DP) — это метод решения задач, которые можно разбить на более мелкие, перекрывающиеся подзадачи.

Принцип:
1. **Разбиение задачи**: Делим большую задачу на более мелкие.
2. **Оптимальное подрешение**: Используем решения подзадач для вычисления общего результата.
3. **Кэширование (мемоизация)**: Храним результаты уже вычисленных подзадач.
4. **Определение состояния**: Определяем параметры, характеризующие состояние задачи.

### Подходы к решению
1. **Рекурсия с мемоизацией** (сверху вниз, top-down).
2. **Итеративный подход с таблицей (табуляция)** (снизу вверх, bottom-up).

---

## Примеры на Java

### 1. Числа Фибоначчи (Рекурсия + Мемоизация)
```java
import java.util.HashMap;

public class FibonacciMemoization {
    private static HashMap<Integer, Long> memo = new HashMap<>();

    public static long fib(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        
        long result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(50)); // Оптимизированный вызов
    }
}
```

### 2. Задача о рюкзаке (0/1 Knapsack, Табуляция)
```java
public class Knapsack {
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;
        System.out.println(knapsack(W, weights, values, values.length)); // 220
    }
}
```

### Заключение
- DP помогает избегать повторных вычислений.
- Два основных метода: мемоизация (рекурсия с кэшированием) и табуляция (заполнение таблицы).
- Используется в задачах на оптимизацию (рюкзак, редактирование строк и др.).
