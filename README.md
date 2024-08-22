# English below

## SimplyAuthorizationMicroservice

simply authorization microservice, only for testing purposes
after running go to http://localhost:8081/swagger-ui/index.html#/


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

## Dodatkowe opcje
- własny użytkownik, ze wszystkimi uprawnieniami:
```
docker run -p 8081:8080 \
 -e "DICEDEV_INIT_USER_NAME=qaz" \
 -e "DICEDEV_INIT_USER_PASSWORD=123qwe" \
 --link mongoAli:mongo \
 simply-auth-ms 
```
- własny użytkownik, z ograniczonymi uprawnieniami:
```
docker run -p 8081:8080 \
 -e "DICEDEV_INIT_USER_NAME=qaz" \
 -e "DICEDEV_INIT_USER_PASSWORD=123qwe" \
 -e "DICEDEV_INIT_USER_RIGHTS=USER, TRAINEE, ADD_USERS" \
 --link mongoAli:mongo \
 simply-auth-ms 
```
uprawnienia, które można przypisać:
- USER
- TRAINEE
- ADD_USERS
- REMOVE_USERS
- ORDER_FOOD
- ADD_RESERVATIONS
- REMOVE_RESERVATIONS

## Gdy nie zostaną podane dane użytkownika, zostaną stworzone dwa domyślne konta:
user: Admin; password: AdminMaKota -> o prawami: ADD_USERS
user: Root; password: RootMaKota -> ze wszystkimi uprawnieniami

# ENGLISH VERSION

## SimplyAuthorizationMicroservice

### This is a simple authorization microservice intended for testing purposes. 
After starting the service, visit http://localhost:8081/swagger-ui/index.html#/.

In the GET /v1/authentication/{credentials} endpoint, the credentials are formatted as a string containing the username and password separated by a colon, for example, user:password.

### Docker Image:
## Creating the Image

To create a Docker image that can be used without needing to run IntelliJ or another IDE, navigate to the main directory containing the `dockerfile` and execute the appropriate command to build the image with the tag simply-auth-ms.
```
docker build -t simply-auth-ms .
```
### Running the Image

The Docker image requires a MongoDB database. To start the image, run it with the necessary port configurations: 
```
docker run -p 8081:8080 --link mongoAli:mongo simply-auth-ms
```
You can change port 8081 to any desired port, but remember to update all references accordingly.

### Starting MongoDB

To start MongoDB, use the command that initializes a MongoDB container named mongoAli: 
```
docker run -p 27017:27017 -d --name mongoAli \
-e MONGO_INITDB_ROOT_USERNAME=ala \
-e MONGO_INITDB_ROOT_PASSWORD=alamakota \
mongo:7.0.12
```
This container will use the:
  - username `ala` 
  - password `alamakota`
  - container name `mongoAli`

These values are also referenced in the `application.properties` file under the line containing `mongodb://ala:alamakota@mongoAli:27017/aladb?authSource=admin`.


#### Error Handling

If you encounter an error stating that the container name /mongoAli is already in use, you will need to remove or rename the existing container before proceeding. The error message will provide a container ID that can be used to remove the conflicting container.
```
 docker rm 925afb8f82354133dfe2a6cfb38800075d82e9947d799fdb98bdcb0f8fd50ebd
```

### Checking Docker Status

To verify that Docker is working correctly, enter `localhost:8081/ok` into your browser's address bar.

### Additional Options

You can create a custom user with full permissions by running the image with specific environment variables for the username and password:
```
docker run -p 8081:8080 \
 -e "DICEDEV_INIT_USER_NAME=qaz" \
 -e "DICEDEV_INIT_USER_PASSWORD=123qwe" \
 --link mongoAli:mongo \
 simply-auth-ms
```
Alternatively, you can create a custom user with limited permissions by specifying which permissions to assign:
```
docker run -p 8081:8080 \
 -e "DICEDEV_INIT_USER_NAME=qaz" \
 -e "DICEDEV_INIT_USER_PASSWORD=123qwe" \
 -e "DICEDEV_INIT_USER_RIGHTS=USER, TRAINEE, ADD_USERS" \
 --link mongoAli:mongo \
 simply-auth-ms
```
Available permissions include:
  - USER
  - TRAINEE
  - ADD_USERS
  - REMOVE_USERS
  - ORDER_FOOD
  - ADD_RESERVATIONS
  - REMOVE_RESERVATIONS

## When no user details are provided, two default accounts will be created:
user: Admin; password: AdminMaKota -> with Rights: ADD_USERS
user: Root; password: RootMaKota -> with all Rights