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
```
mvn quarkus:dev
```
vagy
```
./mvnw quarkus:dev
```
## Alkalmazással kapcsolatos információk

- Localhost alap elérése: http://localhost:8080 <-- A readme-ben látott linkek előtagja
- Egy felhasználóhoz ugyanabból a címből csak 1 adható hozzá (tehát ugyanolyan város, utca, házszám kétszer nem szerepelhet)
- Egy felhasználóhoz 2 ugyanolyan nevű product (termék) nem tartozhat
- A termékekkel kapcsolatos endpointokhoz csak bejelentkezés után, valid tokennel férhetünk hozzá.

## Alkalmazás használata
### Regisztráció

2 lépéses regisztráció:
1. Felhasználó registrációs: PUT-JSON: /user/register
```
{
    "name": "",
    "password": ""
}
```
2. Felhasználó címeinek hozzáadása: PUT-JSON /user/register_addresses
```
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
A teljes regisztrációhoz legalább 1 address-t meg kell adni.

### Bejelentkezés
GET-JSON: /user/login - String-el tér vissza, amely vagy a tokenünk, vagy a belépés során adódott hibák szövege.
```
{
    "name": "",
    "password": ""
}
```
Ha sikeres a belépés a kéréseink Headerjébe helyezzük bele:
Key: Authorization
Value: Bearer + kapott token
### Kijelentkezés
GET /user/logout - A kérésünkben tárolt token alapján kijelentkezteti a felhasználót.
Ezzel a tokennel többet nem tud hozzáférni a szerver token-szükséges endpointjaihoz.

### Termék hozzáadása
PUT-JSON /product
```
{
    "name": "",
    "description": "",
    "price": ""
}
```
### Termék szerkesztése
POST-JSON /product/{termék-ID}
```
{
    "name": "",
    "description": "",
    "price": ""
}
```
### Termék törlése
DELETE /product/{termék-ID}

### Filterek - termékek szűrése
GET /product/filter?filterOpt=xxx&price=yyy

- xxx - szűrési paraméter, amely a következő lehet:
1. all - minden termék listázása (&price nem szükséges)
2. my - saját termékek listázása (&price nem szükséges)
3. more - price-nál nagyobb értékű termékek listázása
4. less - price-nál kisebb értékű termékek listázása

- yyy - a szűrni kívánt paraméterekhez tartozó ár

## Endpoint tesztek

































