# 📌 Установка Apache Kafka + Zookeeper на 3 сервера

## 🏗 Шаг 1: Установка Zookeeper

### 1️⃣ Установить Zookeeper на все 3 сервера (исправить на свежую версию)
```bash
cd /opt
wget https://downloads.apache.org/zookeeper/stable/apache-zookeeper-3.8.4-bin.tar.gz
tar -xzf apache-zookeeper-3.8.4-bin.tar.gz
mv apache-zookeeper-3.8.4-bin zookeeper
```

### 2️⃣ Настроить конфигурацию Zookeeper (`/opt/zookeeper/conf/zoo.cfg`)
```ini
tickTime=2000
dataDir=/opt/zookeeper/data
admin.serverPort=8180
clientPort=2181
initLimit=5
syncLimit=2
server.1=192.168.0.101:2888:3888
server.2=192.168.0.102:2888:3888
server.3=192.168.0.103:2888:3888
```

### 3️⃣ Создать директорию и задать ID сервера
```bash
mkdir -p /opt/zookeeper/data
echo "1" > /opt/zookeeper/data/myid  # На первом сервере
echo "2" > /opt/zookeeper/data/myid  # На втором сервере
echo "3" > /opt/zookeeper/data/myid  # На третьем сервере
```

### 4️⃣ Запустить Zookeeper
```bash
/opt/zookeeper/bin/zkServer.sh start
```

### 5️⃣ Проверить работу
```bash
/opt/zookeeper/bin/zkCli.sh -server 192.168.0.101:2181 ls /
```

### 6. Открыть порты в брандмауэре
2181 2888 3888

---

## 🚀 Шаг 2: Установка Kafka

### 1️⃣ Установить Kafka на все 3 сервера (исправить на свежую версию)
```bash
cd /opt
wget https://downloads.apache.org/kafka/4.0.0/kafka_2.13-4.0.0.tgz
tar -xzf kafka_2.13-4.0.0.tgz
mv kafka_2.13-4.0.0 kafka
```

### 2️⃣ Настроить Kafka (`/opt/kafka/config/server.properties`)
#### 📌 Конфигурация для `192.168.0.101`
```ini
broker.id=1
log.dirs=/opt/kafka/data
zookeeper.connect=192.168.0.101:2181,192.168.0.102:2181,192.168.0.103:2181
listeners=PLAINTEXT://192.168.0.101:9092
advertised.listeners=PLAINTEXT://192.168.0.101:9092
default.replication.factor=2
offsets.topic.replication.factor=2
```
#### 📌 Для `192.168.0.102`
- `broker.id=2`
- `listeners=PLAINTEXT://192.168.0.102:9092`
- `advertised.listeners=PLAINTEXT://192.168.0.102:9092`

#### 📌 Для `192.168.0.103`
- `broker.id=3`
- `listeners=PLAINTEXT://192.168.0.103:9092`
- `advertised.listeners=PLAINTEXT://192.168.0.103:9092`

### 3️⃣ Создать директорию логов Kafka
```bash
mkdir -p /opt/kafka/data
```

### 4️⃣ Запустить Kafka
```bash
/opt/kafka/bin/kafka-server-start.sh -daemon /opt/kafka/config/server.properties
```
### 6. Открыть порты в брандмауэре
9092 9093

---

## ✅ Шаг 3: Проверка работы

### 🔍 Проверить список брокеров
```bash
/opt/kafka/bin/zookeeper-shell.sh 192.168.0.101:2181 ls /brokers/ids
```
✅ Должно вывести `[1,2,3]`

### 🎯 Создать тестовый топик
```bash
/opt/kafka/bin/kafka-topics.sh --create --topic test-topic --bootstrap-server 192.168.0.101:9092 --replication-factor 2 --partitions 3
```

### 🔄 Проверить список топиков
```bash
/opt/kafka/bin/kafka-topics.sh --list --bootstrap-server 192.168.0.101:9092
```

### 📝 Отправить сообщение
```bash
/opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.0.101:9092 --topic test-topic
```
**Введите текст и нажмите Enter.**

### 📥 Получить сообщение
```bash
/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server 192.168.0.101:9092 --topic test-topic --from-beginning
```

---

## 🎉 Готово!
Теперь у тебя **Kafka-кластер (3 брокера) + Zookeeper** на 3 серверах. 🚀

