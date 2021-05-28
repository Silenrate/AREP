FROM openjdk:8

WORKDIR /login/bin

ENV PORT 7000
ENV TZ America/Bogota

COPY /target/classes /login/bin/classes
COPY /target/dependency /login/bin/dependency
RUN mkdir keystore
COPY keystore/* /login/bin/keystore/

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.App"]