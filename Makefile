APP_NAME=e-commerce-chess
.PHONY: all build docker-build run crean stop show-log

all:run

build:
	mvn clean package

docker-build: build
	docker-compose build

run: docker-build	
	docker-compose up -d

stop: 
	docker-compose down

clean:
	mvn clean
	docker system prune -f

logg:
	bash ./src/main/resources/scripts/show-log.sh
