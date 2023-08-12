tomcat-up:
	docker-compose -f docker-compose-tomcat.yaml up -d

tomcat-down:
	docker-compose -f docker-compose-tomcat.yaml down

db-up:
	docker-compose -f docker-compose-postgres.yaml up -d

db-down:
	docker-compose -f docker-compose-postgres.yaml down