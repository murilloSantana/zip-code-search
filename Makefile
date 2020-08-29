
docker-run:
	docker-compose up

local-run: install
	./mvnw spring-boot:run -pl application

clean:
	./mvnw clean

install: clean
	./mvnw install

test:
	./mvnw test
