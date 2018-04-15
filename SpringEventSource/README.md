### Demo app using EventEmitter 

- open in browser: http://localhost:8080/index.html

This will open an event stream and wait for data.

- to send data: `curl -X POST http://localhost:8080/newdata  -d 123`
- to start random data emitter: `curl -X POST http://localhost:8080/emitrandom` 