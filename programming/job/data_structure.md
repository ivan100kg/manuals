# 📌 Основные структуры данных

## 📌 1. Массив (Array)
**Описание:** Упорядоченная структура данных, элементы хранятся в непрерывной области памяти.

**Временная сложность:**
- Доступ по индексу: **O(1)**
- Поиск: **O(n)** (линейный) или **O(log n)** (бинарный, если отсортирован)
- Вставка/Удаление (в середине): **O(n)**

**Пример (Java):**
```java
int[] arr = {1, 2, 3, 4, 5};
System.out.println(arr[2]); // Выведет 3
```

---

## 📌 2. Связанный список (Linked List)
**Описание:** Элементы (узлы) связаны между собой ссылками.

### 🔹 Односвязный список
```java
class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; this.next = null; }
}
```

**Временная сложность:**
- Доступ по индексу: **O(n)**
- Вставка/Удаление в начале: **O(1)**
- Вставка/Удаление в середине: **O(n)**

### 🔹 Двусвязный список (имеет ссылки на предыдущий и следующий узлы)
```java
class Node {
    int data;
    Node next, prev;
    Node(int data) { this.data = data; this.next = this.prev = null; }
}
```

---

## 📌 3. Стек (Stack)
**Описание:** LIFO (Last In, First Out) — последний добавленный элемент извлекается первым.

**Основные операции:**
- `push(x)`: добавить элемент (O(1))
- `pop()`: удалить верхний элемент (O(1))
- `peek()`: посмотреть верхний элемент (O(1))

**Пример (Java - Stack)**
```java
import java.util.Stack;
Stack<Integer> stack = new Stack<>();
stack.push(10);
stack.push(20);
System.out.println(stack.pop()); // Выведет 20
```

**Реализация через LinkedList**
```java
import java.util.LinkedList;
LinkedList<Integer> stack = new LinkedList<>();
stack.push(10);
stack.push(20);
System.out.println(stack.pop()); // Выведет 20
```

---

## 📌 4. Очередь (Queue)
**Описание:** FIFO (First In, First Out) — первый добавленный элемент извлекается первым.

**Основные операции:**
- `enqueue(x)`: добавить элемент (O(1))
- `dequeue()`: удалить первый элемент (O(1))
- `peek()`: посмотреть первый элемент (O(1))

**Пример (Java - Queue)**
```java
import java.util.LinkedList;
import java.util.Queue;
Queue<Integer> queue = new LinkedList<>();
queue.add(1);
queue.add(2);
System.out.println(queue.poll()); // Выведет 1
```

---

## 📌 5. Двусторонняя очередь (Deque - Double Ended Queue)
**Описание:** Можно добавлять/удалять элементы как с начала, так и с конца.

**Пример (Java - Deque)**
```java
import java.util.Deque;
import java.util.LinkedList;
Deque<Integer> deque = new LinkedList<>();
deque.addFirst(1);
deque.addLast(2);
System.out.println(deque.pollFirst()); // Выведет 1
```

---

## 📌 6. Деревья и графы

### 📌 Деревья (Trees)
**Описание:** Иерархическая структура, в которой каждый узел связан с дочерними узлами.

**Основные виды деревьев:**
- **Бинарное дерево** – каждый узел имеет не более двух потомков.
- **Бинарное дерево поиска (BST)** – левый потомок меньше родителя, правый – больше.
- **Сбалансированные деревья** – AVL, Red-Black Tree (самобалансирующиеся BST).

**Обходы дерева:**
- **DFS (глубина - Depth-First Search)**
  - Прямой (Preorder) (Root -> Left -> Right)
  - Центрированный (Inorder) (Left -> Root -> Right)
  - Обратный (Postorder) (Left -> Right -> Root)

```java
void inorderTraversal(Node root) {
    if (root != null) {
        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }
}
```

- **BFS (ширина - Breadth-First Search)**
```java
void bfs(Node root) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        Node node = queue.poll();
        System.out.print(node.data + " ");
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

### 📌 Графы (Graphs)
**Описание:** Структура, состоящая из узлов (вершин) и рёбер.

**Основные способы представления графов:**
- Матрица смежности (`int[][] matrix`)
- Список смежности (`Map<Vertex, List<Vertex>>`)

**Алгоритмы:**
- **DFS (поиск в глубину)** – используется для проверки связности графа, поиска циклов.
- **BFS (поиск в ширину)** – используется для поиска кратчайшего пути в невзвешенном графе.

**Поиск кратчайшего пути:**
- **Алгоритм Дейкстры** (для графов без отрицательных рёбер)
- **Алгоритм Беллмана-Форда** (работает с отрицательными весами)
- **Алгоритм Флойда-Уоршелла** (поиск кратчайших путей между всеми вершинами)

```java
void dijkstra(int[][] graph, int start) {
    // Реализация алгоритма Дейкстры
}
```

---

# 🎯 Итог
| Структура | Доступ | Вставка | Удаление |
|-----------|--------|---------|---------|
| **Массив** | O(1) | O(n) | O(n) |
| **Связанный список** | O(n) | O(1) | O(1) |
| **Стек** | O(n) | O(1) | O(1) |
| **Очередь** | O(n) | O(1) | O(1) |
| **Deque** | O(n) | O(1) | O(1) |
| **Дерево (BST)** | O(log n) | O(log n) | O(log n) |
| **Граф** | O(V + E) | O(1) | O(1) |

