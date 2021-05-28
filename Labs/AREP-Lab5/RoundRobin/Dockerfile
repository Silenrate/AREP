FROM openjdk:8

WORKDIR /loadbalancer/bin

ENV PORT 7000

COPY /target/classes /loadbalancer/bin/classes
COPY /target/dependency /loadbalancer/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.App"]