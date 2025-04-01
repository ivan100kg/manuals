# 🚀 Установка Kafka без ZooKeeper (KRaft)

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
# Основные параметры
broker.id=1
log.dirs=/opt/kafka/data
process.roles=broker,controller
node.id=1
controller.quorum.voters=1@192.168.0.101:9093,2@192.168.0.102:9093,3@192.168.0.103:9093
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
### 5️⃣ Открыть порты в брандмауэре
Откройте следующие порты для взаимодействия между брокерами Kafka и клиентами:

9092 — для клиентов, подключающихся к Kafka.

9093 — для внутреннего взаимодействия контроллеров в режиме KRaft.

---

## ✅ Шаг 3: Проверка работы

```bash
/opt/kafka/bin/kafka-topics.sh --bootstrap-server 192.168.0.101:9092 --list
```