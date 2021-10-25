# Projekt előkészítése

A projekthez szükséges egy adatbázis.
A Docker telepítése után terminálban a következő parancs létrehozza az adatbázisunkat:
```shell script
docker run --name quarkus-db -e MYSQL_ROOT_PASSWORD=root -p 3310:3306 mysql
```

Majd a terminállal navigáljunk az import.sql-t tartalmazó mappába. Ez a projekten belül található: /hibernate-orm/src/main/resources/import.sql
Ez után futtassuk a következő parancsot:
```shell script
--docker exec -i quarkus-db mysql -uroot -proot < import.sql
```
Ezzel felépítjük az adatbázis sémát, amelyet a projekt használni fog.

## Alkalmazás futtatása

Miután terminállal a projekt mappájába navigáltunk, a következő paranccsal futtatjuk a projektet:
```shell script
mvn quarkus:dev
```
vagy
```shell script
./mvnw quarkus:dev
```

## Alkalmazás használata
### Regisztráció

2 lépéses regisztráció:
1. Felhasználó registrációs: PUT-JSON: /user/register
```shell script
{
    "name": "",
    "password": ""
}
```
2. Felhasználó címeinek hozzáadása: PUT-JSON /user/register_addresses
```shell script
[
    {
        "city": "",
        "roadName": "",
        "houseNumber": ""
    },
    {
        "city": "",
        "roadName": "",
        "houseNumber": ""
    }
]
```












