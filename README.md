### 即时通信系统服务端
基于rabbitmq和netty

### 及时通信系统网页端demo
基于websocket

#### 启动
* 启动webSocketServer模块
```
java -DHOST=172.16.192.128 -DPORT=9999 -jar webSocketAcceptServer-1.0-SNAPSHOT.jar
```
* 启动exchangeServer模块
```
java -DHOST=172.16.192.128 -jar exchangeServer-1.0-SNAPSHOT.jar
```
* 启动dealUnit模块
```
java -jar dealUnit-1.0-SNAPSHOT.jar
```