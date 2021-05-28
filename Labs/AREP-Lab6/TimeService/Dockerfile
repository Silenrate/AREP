FROM openjdk:8

WORKDIR /logic/bin

ENV PORT 8000
ENV TZ America/Bogota

COPY /target/classes /logic/bin/classes
COPY /target/dependency /logic/bin/dependency
RUN mkdir keystore
COPY keystore/* /logic/bin/keystore/

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.App"]