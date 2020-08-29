FROM openjdk:13-alpine3.9
WORKDIR /usr/app/
COPY . .
RUN ./mvnw clean
RUN ./mvnw install
ENTRYPOINT ["./mvnw","spring-boot:run", "-pl", "application"]