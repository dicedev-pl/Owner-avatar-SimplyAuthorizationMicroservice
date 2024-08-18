# SimplyAuthorizationMicroservice

simply authorization microservice, only for testing purposes
after running go to http://localhost:8081/swagger-ui/index.html#/

#### in GET /v1/authentication/{credentials}
credentials are simply strings user and password separated by colon, ex.: user:password

# Obraz Docker:
## Tworzenie obrazu
aby utworzyć obraz dokera, który będzie można wykorzystać bez potrzeby uruchamiania IntelliJ, lub innego IDE,
w głównym katalogu, z plikiem `dockerfile`, należy wykonać

```
docker build -t simply-auth-ms .
```

## Uruchomienie obrazu
HINT: obraz potrzebuje bazy MongoDB, uruchomienie poniżej
wydanie polecenia:
```
docker run -p 8081:8080 --link mongoAli:mongo simply-auth-ms  
```
- port `8081` możesz dowolnie zmieniać, jednak pamiętaj, że wszystkie odwołania do niego wymagają aktualizacji podczas kopiowania

## Uruchomienie MongoDB
HINT: komendę można wpisać w jednej linii pomijając znak `\` na końcu
HINT: znak `\` oznacza pominięcie `Enter`, na końcu linii
```
docker run -p 27017:27017 -d --name mongoAli \
	-e MONGO_INITDB_ROOT_USERNAME=ala \
	-e MONGO_INITDB_ROOT_PASSWORD=alamakota \
	mongo:7.0.12
```
 - `ala` to nazwa użytkownika
 - `alamakota` to hasło
 - `mongoAli` to nazwa pod jaką zostaje uruchomiony kontener 
wszystkie wartości są wykorzystywane w pliku `application.properties` - linijka `mongodb://ala:alamakota@mongoAli:27017/aladb?authSource=admin`

### Błędy
- `docker: Error response from daemon: Conflict. The container name "/mongoAli" is already in use by container "925afb8f82354133dfe2a6cfb38800075d82e9947d799fdb98bdcb0f8fd50ebd". You have to remove (or rename) that container to be able to reuse that name.`
    - rozwiązanie, to wydanie komendy, z numerem, który widnienie w komunikacie:
    ```
    docker rm 925afb8f82354133dfe2a6cfb38800075d82e9947d799fdb98bdcb0f8fd50ebd
    ```
### Sprawdzenie czy Docker działa:
- wpisane w przeglądarkę adresu: `localhost:8081/ok`