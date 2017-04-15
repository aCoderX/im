### 即时通信系统服务端
基于rabbitmq和netty

### 及时通信系统网页端demo
基于websocket

#### 启动
1. 启动jar
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
java -DHOST=172.16.192.128 -jar dealUnit-1.0-SNAPSHOT.jar
```
2. 发布webIM（websocket测试demo）
3. 启动nginx（用作反向代理）
4. 如果需要处理日志，启动日志模块imLoggerServer
只有多台机器上运行时才会将日志发往同一台机器，如果只是单台机器，默认直接log4j记录日志
