
**Add key to Redis:** 

echo '{"key":"key3","value":"value3"}' | curl -H "Content-Type: application/json" -d @- http://localhost:8080/singlekey

**Get key to Redis:**

curl localhost:8080/singlekey/key2

**Send e-Mail:** 

echo '{"to": "christian.egli4@gmail.com","from": "christian.egli@gmx.net","subject": "MailSender Test","body": "This is just a test..."}' | curl -H "Content-Type: application/json" -d @- http://localhost:8080/mail



**Send E-Mail Ressocurces:** 

https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-email.html
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-email.html
https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#mail

