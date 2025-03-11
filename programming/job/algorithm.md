# 📌 Разбор сортировок и оценка сложности алгоритмов

## 🔹 Пузырьковая сортировка (Bubble Sort)
**Описание:** Сравниваем попарно элементы и "всплываем" наибольший в конец.

**Временная сложность:** 
- Средняя и худшая: **O(n²)**
- Лучшая (если массив уже отсортирован): **O(n)**

**Пример (Java):**
```java
void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```

---

## 🔹 Сортировка выбором (Selection Sort)
**Описание:** Находим минимальный элемент и ставим его на первую позицию.

**Временная сложность:** 
- Всегда: **O(n²)**

**Пример (Java):**
```java
void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        int temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;
    }
}
```

---

## 🔹 Сортировка слиянием (Merge Sort)
**Описание:** Рекурсивно делим массив пополам и объединяем отсортированные части.

**Временная сложность:** 
- Всегда: **O(n log n)**

**Пример (Java):**
```java
void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    int[] L = new int[n1];
    int[] R = new int[n2];
    for (int i = 0; i < n1; i++) L[i] = arr[left + i];
    for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) arr[k++] = L[i++];
        else arr[k++] = R[j++];
    }
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}
```

---

## 🔹 Быстрая сортировка (Quick Sort)
**Описание:** Выбираем опорный элемент (pivot), делим массив и сортируем рекурсивно.

**Временная сложность:** 
- Средняя: **O(n log n)**
- Худшая (если массив уже отсортирован и pivot выбирается плохо): **O(n²)**

**Пример (Java):**
```java
void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
}
```

---

## 🔹 Итоговая таблица сложности алгоритмов
| Алгоритм         | Лучший случай | Средний случай | Худший случай |
|------------------|--------------|--------------|--------------|
| **Bubble Sort**  | O(n)         | O(n²)       | O(n²)       |
| **Selection Sort** | O(n²)      | O(n²)       | O(n²)       |
| **Merge Sort**   | O(n log n)  | O(n log n)  | O(n log n)  |
| **Quick Sort**   | O(n log n)  | O(n log n)  | O(n²)       |
