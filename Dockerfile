FROM maven:3.6.1-jdk-8-alpine as builder

WORKDIR /app

COPY pom.xml /app

RUN mvn clean install

COPY . /app

RUN mvn clean install

FROM openjdk:8-jdk-alpine

ENV TIMEZONE "Asia/Ho_Chi_Minh"

RUN apk add --no-cache tzdata bash \
&& cat /usr/share/zoneinfo/${TIMEZONE} > /etc/localtime \
&& echo "${TIMEZONE}" > /etc/timezone

WORKDIR /work

COPY --from=builder /app /work/

EXPOSE 8099
# replace port in yaml
ENV SERVER_PORT 8099

CMD java -jar target/ecommerce-backend-1.0-SNAPSHOT.jar