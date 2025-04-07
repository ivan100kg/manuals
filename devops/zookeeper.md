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

### 6️⃣ Открыть порты в брандмауэре
2181 2888 3888

---