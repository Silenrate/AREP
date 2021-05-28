FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000
ENV TZ America/Bogota

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.App"]