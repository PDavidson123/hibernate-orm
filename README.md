# Projekt előkészítése

A projekthez szükséges egy adatbázis.
A Docker telepítése után terminálban a következő parancs létrehozza az adatbázisunkat:
```shell script
docker run --name quarkus-db -e MYSQL_ROOT_PASSWORD=root -p 3310:3306 mysql
```

Majd a terminállal navigáljunk az import.sql-t tartalmazó mappába. Ez a projekten belül található: /hibernate-orm/src/main/resources/import.sql
Ez után futtassuk a következő parancsot:
```shell script
docker exec -i quarkus-db mysql -uroot -proot < import.sql
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
1. Felhasználó registrációs: POST-JSON: /user/register
```
{
    "name": "",
    "password": ""
}
```
2. Felhasználó címeinek hozzáadása: POST-JSON /user/register_addresses (Kapott token szükséges)
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
POST-JSON /product
```
{
    "name": "",
    "description": "",
    "price": ""
}
```
### Termék szerkesztése
PUT-JSON /product/{termék-ID}
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
1. /user/register - POST
- Nem létező user névvel
  
```
pm.test("Success with Body", function () {
  pm.response.to.have.status(201);
  pm.response.to.have.body();
});

pm.test("Body contains string",() => {
  pm.expect(pm.response.text()).to.include("User added successfully. You need register at least 1 address. /user/register_addresses");
  });
```

- Létező user névvel

```
pm.test("Success with Body", function () {
  pm.response.to.have.status(400);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("User name reserved.");
  });
```

2. /user/register_addresses - POST
- Érvényes címekkel
```
pm.test("Success with Body and header", function () {
  pm.request.headers.find("Authorization")
  pm.response.to.have.status(200);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("All addresses successfully added.");
  });
```

3. /user/login GET
- Regisztráció után
```
pm.test("Success with Body", function () {
  pm.response.to.have.status(200);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("has a valid token.");
  });
```

- Ha már egyszer bejelentkeztünk
```
pm.test("Success with Body", function () {
  pm.response.to.have.status(200);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("has a valid token.");
  });
```

- Ha még nem jelentkeztünk be
```
pm.test("Success with Body", function () {
  pm.response.to.have.status(200);
  pm.response.to.have.body();
});
```

4. /product POST
- Érvényes termék megadásakor
```
pm.test("Success with Body and header", function () {
    pm.request.headers.find("Authorization")
  pm.response.to.have.status(201);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("Product added successfully.");
  });
```
- Név nélküli termék megadásakor
```
pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("Product don't have name.");
  });
```
- A user-hez már tartozik egy ugyanilyen nevű termék
```
pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("No permission.");
  });
```

5. /product/{id} PUT
- Saját termék átnevezése
```
pm.test("Success with Body and header", function () {
    pm.request.headers.find("Authorization")
  pm.response.to.have.status(200);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("Product updated successfully.");
  });
```
- Nem saját termék változtatása
```
pm.test("Success with Body and header", function () {
    pm.request.headers.find("Authorization")
  pm.response.to.have.status(400);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("No permission.");
  });
```
6. /product/{id} DELETE
- Saját termékt szeretnénk törölni
```
pm.test("Success with Body and header", function () {
    pm.request.headers.find("Authorization")
  pm.response.to.have.status(200);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("Product deleted successfully.");
  });
```
- Más termékét szeretnénk törölni
```
pm.test("Success with Body and header", function () {
    pm.request.headers.find("Authorization")
  pm.response.to.have.status(400);
  pm.response.to.have.body();
});

pm.test("Body contains a string",() => {
  pm.expect(pm.response.text()).to.include("No permission.");
  });
```

7. /product/filter GET: ?filterOpt=(all/my/more/less)&price=5000
- Ha legalább 1 termékkel jön vissza a response
```
pm.test("Success with JsonBody and header", function () {
    pm.request.headers.find("Authorization")
  pm.response.to.have.status(200);
  pm.response.to.have.jsonBody;
});

const jsonData = pm.response.json();
pm.test("Test JsonArray data type of the response", () => {
  pm.expect(jsonData).to.be.an("array");
  pm.expect(jsonData[0].name).to.be.a("string");
  pm.expect(jsonData[0].description).to.be.a("string");
  pm.expect(jsonData[0].price).to.be.a("number");
  pm.expect(jsonData[0].productID).to.be.a("number");
});
```













