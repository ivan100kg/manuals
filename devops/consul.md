# 🛠 Шпаргалка: Развёртывание и использование Consul

## 📌 1. Установка и запуск Consul в Linux

### 🔹 **Установка Consul на сервере (Ubuntu/Debian)**
```sh
# Устанавливаем зависимости
sudo apt update && sudo apt install -y unzip curl

# Скачиваем последнюю версию Consul
CONSUL_VERSION="1.15.4"
curl -O https://releases.hashicorp.com/consul/$CONSUL_VERSION/consul_${CONSUL_VERSION}_linux_amd64.zip

# Распаковываем и перемещаем в /usr/local/bin
unzip consul_${CONSUL_VERSION}_linux_amd64.zip
sudo mv consul /usr/local/bin/

# Проверяем установку
consul --version
```

### 🔹 **Запуск Consul в режиме сервера**
```sh
sudo consul agent -server -bootstrap-expect=1 -ui \
  -bind=0.0.0.0 -client=0.0.0.0 \
  -data-dir=/opt/consul \
  -node=consul-server
```

**Параметры:**
- `-server` — режим сервера
- `-bootstrap-expect=1` — количество ожидаемых серверов (1 в нашем случае)
- `-ui` — включаем веб-интерфейс (**http://<IP-сервера>:8500**)
- `-bind=0.0.0.0` — слушать все интерфейсы
- `-client=0.0.0.0` — разрешить доступ из сети

### 🔹 **Остановка и автозапуск Consul как systemd-сервис**
```sh
sudo tee /etc/systemd/system/consul.service > /dev/null <<EOF
[Unit]
Description=Consul Service
After=network.target

[Service]
ExecStart=/usr/local/bin/consul agent -server -bootstrap-expect=1 -ui \
  -bind=0.0.0.0 -client=0.0.0.0 \
  -data-dir=/opt/consul \
  -node=consul-server
Restart=always
User=root
Group=root

[Install]
WantedBy=multi-user.target
EOF

# Перезапускаем systemd и запускаем Consul
sudo systemctl daemon-reload
sudo systemctl enable consul
sudo systemctl start consul
```

---
## 📌 2. Управление конфигурацией (Key-Value Store)

### 🔹 **Добавление конфига**
```sh
consul kv put my-app/config '{"db":"postgres://192.168.1.10:5432","cache":"redis://192.168.1.20:6379"}'
```

### 🔹 **Получение конфига**
```sh
curl http://localhost:8500/v1/kv/my-app/config?raw
```

### 🔹 **Удаление ключа**
```sh
consul kv delete my-app/config
```

---
## 📌 3. Регистрация сервисов (Service Discovery)

### 🔹 **Регистрация сервиса**
Создаем `service.json`:
```json
{
  "service": {
    "name": "auth-service",
    "port": 8080,
    "check": {
      "http": "http://192.168.1.5:8080/health",
      "interval": "10s"
    }
  }
}
```

**Регистрируем:**
```sh
consul services register service.json
```

### 🔹 **Просмотр зарегистрированных сервисов**
```sh
consul catalog services
```

### 🔹 **Запрос информации о сервисе**
```sh
curl http://localhost:8500/v1/catalog/service/auth-service
```

---
## 📌 4. Использование в Java, Python, Bash

### **Java 8 (получение конфига из KV Store)**
```java
import java.net.*;
import java.util.Scanner;

public class ConsulClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8500/v1/kv/my-app/config?raw");
        Scanner scanner = new Scanner(url.openStream()).useDelimiter("\\A");
        System.out.println(scanner.next());
    }
}
```

---
### **Spring Boot 2/3 (подключение к KV Store)**
#### 🔹 **Добавляем зависимость (Maven)**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-config</artifactId>
    <version>3.1.1</version>
</dependency>
```

#### 🔹 **Настройка `application.yml`**
```yaml
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
      config:
        enabled: true
        prefix: config
        defaultContext: application
```

#### 🔹 **Чтение конфига в Spring Boot**
```java
@Value("${db}")
private String databaseUrl;
```

---
### **Python (получение конфига)**
```python
import requests
response = requests.get("http://localhost:8500/v1/kv/my-app/config?raw")
config = response.json()
print(config["db"])
```

---
### **Bash (получение конфига)**
```sh
CONFIG=$(curl -s http://localhost:8500/v1/kv/my-app/config?raw)
echo "Config: $CONFIG"
```

---
## 📌 5. Мониторинг и DNS API

### 🔹 **Запрос состояния сервисов**
```sh
consul operator raft list-peers
```

### 🔹 **Использование DNS API для поиска сервисов**
```sh
dig @localhost -p 8600 auth-service.service.consul
```

---
## ✅ Итог
🔹 **Consul** позволяет централизованно управлять конфигурациями и сервисами.  
🔹 Работает с **Java 8, Spring Boot 2/3, Python, Bash**.  
🔹 Обеспечивает **автоматическое обнаружение сервисов** через **DNS API**.  
🚀 **Используй для микросервисов и распределённых систем!**
