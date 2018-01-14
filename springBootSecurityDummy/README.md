## App implementing Custom Spring Security Features

More info on Custom Security Filters: http://leaks.wanari.com/2017/11/28/how-to-make-custom-usernamepasswordauthenticationfilter-with-spring-security/


#### Generate TLS Public Key
```
 keytool -genkey -alias tomcat -keypass password -keystore keystore.jks -storepass password -keyalg RSA -validity 360 -keysize 2048
```

#### Start app

`https://localhost:8080/`

#### Accounts

`user@example.com    uuu    20`

`admin@example.com   aaa    30`

