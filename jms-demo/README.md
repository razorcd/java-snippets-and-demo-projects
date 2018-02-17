To start ActiveMQ with docker:

```
docker run --name='activemq' -it --rm -P -p 61616:61616 -p 8161:8161 webcenter/activemq:latest
```

then open admin (admin:admin): `http://localhost:8161/admin/index.jsp`




