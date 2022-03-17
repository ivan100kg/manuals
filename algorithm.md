# Алгоритм - набор инструкций для выполнения задачи.

### Время выполнения алгоритма
`Средний случай` - он же лучший случай выполнения алгоритма.<br>
`Худший случай` - самый долгий вариант выполнения алгоритма.<br>
Для алгоритмов с одинаковой скоростью например O(n) также учитывается
константа c - некоторый фиксированный пром времени для 1 операции. 
O(n*c)
    О - время выполнения алгоритма - описыв-ся ростом количества оперц.
    n - количество элементов.
    c - некоторый фиксированный пром времени для 1 операции. 

### Типичные примеры О большого(есть и другие)
O(log n)     # логарифмическое время(бинарный поиск)
O(n)         # линейное время(простой поиск)
O(n * log n) # эф-е алгоритмы(быстрая сортировка)
O(n**2)      # медленные алг сорт(сортировка выбором)
O(n!)        # факториальное время, оч медленные алг

### Массивы и списки
Массивы идеальны для чтения
Списки идеальны для записи/удаления
    +-------+-------+------+
    |операц |массивы|списки|  O(n) - линейное время
    +-------+-------+------+  O(1) - постоянное время
    |чтение | O(1)  |O(n)  |  вставка/удаление - первый|последний элем
    |вставка| O(n)  |O(1)  |
    |удален | O(n)  |O(1)  |
    +-------+-------+------+

### Алгоритмы поиска
##### Бинарный поиск - поиск в отсортированном массиве, O(log n)
``` python3
def binary_search(items_list, item):
    """на вход подаётся отсортированный список и значение"""
    low = 0
    high = len(items_list) - 1
    while low <= high:
        mid = (low + high) // 2
        guess = items_list[mid]
        if guess == item:
            return mid
        elif item > guess:
            low = mid + 1
        else:
            high = mid - 1
    return None
```

### Рекурсивная функция
Базовый возврат из функции
Рекурсивный случай - вызов самой себя
Все вызовы хранятся в стеке

### Алгоритм быстрой сортировки, разделяй и властвуй.
Время O(n**2), среднее O(n *logn)
``` python3
def quicksort(arr):
    if len(arr) < 2:
        return arr
    else:
        pivot = arr[0]
        less  = [i for i in arr[1:] if i ≤ pivot]
        greater = [i for i in arr[1:] if i > pivot]
        return quicksort(less) + [pivot] + quicksort(greater)
```
