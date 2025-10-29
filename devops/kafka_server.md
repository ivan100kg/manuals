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
process.roles=broker,controller
node.id=1
controller.quorum.bootstrap.servers=192.168.0.101:9093,192.168.0.102:9093,192.168.0.103:9093
controller.quorum.voters=1@192.168.0.101:9093,2@192.168.0.102:9093,3@192.168.0.103:9093
listeners=PLAINTEXT://192.168.0.101:9092,CONTROLLER://192.168.0.101:9093
advertised.listeners=PLAINTEXT://192.168.0.101:9092,CONTROLLER://192.168.0.101:9093
controller.listener.names=CONTROLLER
inter.broker.listener.name=PLAINTEXT
log.dirs=/var/log/kafka
offsets.topic.replication.factor=2
share.coordinator.state.topic.replication.factor=2
transaction.state.log.replication.factor=2
request.timeout.ms=30000
```
#### 📌 Для `192.168.0.102` заменить строки
- `node.id=2`
- `listeners=PLAINTEXT://192.168.0.102:9092,CONTROLLER://192.168.0.102:9093`
- `advertised.listeners=PLAINTEXT://192.168.0.102:9092,CONTROLLER://192.168.0.102:9093`

#### 📌 Для `192.168.0.103` заменить строки
- `node.id=3`
- `listeners=PLAINTEXT://192.168.0.103:9092,CONTROLLER://192.168.0.103:9093`
- `advertised.listeners=PLAINTEXT://192.168.0.103:9092,CONTROLLER://192.168.0.103:9093`

### 2️⃣a Настроить папку логов на каждом сервере, на первом генерируем uuid и используем на всех
- /opt/kafka/bin/kafka-storage.sh random-uuid
- /opt/kafka/bin/kafka-storage.sh format -t <uuid> -c /opt/kafka/config/server.properties

### 3️⃣ Запустить Kafka
```bash
/opt/kafka/bin/kafka-server-start.sh -daemon /opt/kafka/config/server.properties
# или так с выбором нужной Java
JAVA_HOME=/usr/lib/jvm/jdk-21.0.6-oracle-x64 /opt/kafka/bin/kafka-server-start.sh -daemon /opt/kafka/config/server.properties
```
### 4️⃣ Открыть порты в брандмауэре
#### Откройте следующие порты для взаимодействия между брокерами Kafka и клиентами:

- 9092 — для клиентов, подключающихся к Kafka.
- 9093 — для внутреннего взаимодействия контроллеров в режиме KRaft.

---

## ✅ Шаг 3: Проверка работы

```bash
/opt/kafka/bin/kafka-topics.sh --bootstrap-server 192.168.0.101:9092 --list
```