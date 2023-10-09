# Lists-Service

## Założenia i opis

Serwis pozwalający definiować listingi danych, czyli kompletne lub okrojone zbiory pewnych konkretnych danych. Przykładowo może
to być listing danych na temat wszystkich sprintów lub zawężone dane do sprintów z ostatniego roku. Listing danych poza 
wyświetlaniem wybranych danych, umożliwia również ich filtrowanie. Dane zawsze są stronnicowane to znaczy, że serwis 
odpowiada zbiorem danych w porcjach po X rekordów, gdzie X jest konfigurowany podczas definiowania listingu.

Lista tworzona jest na podstawie formularza udostępnionego przez [Forms-Service](https://gitlab.bpower2.com/bpower3/micro-services/forms-service)
Ten formularz określa jakie dane i jakie informacje o tych danych (kolumny) będą się znajdować na listingu.

Przykładowy listing danych (skrócony)

```json
{
  "id": "651d70477624eb47cbc0049b",
  "name": "Kanban lista",
  "columns": [
    {
      "techName": "64ff1717f76c5822c3bb5a63",
      "label": "ID",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a64",
      "label": "Name",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a65",
      "label": "Description",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    }
  ]
}
```
Powyższy listing ma nazwę "`Kanban Lista`" i zawiera kolumny:
 - `ID`
 - `Name`
 - `Description`

Każda z kolumn ma nazwę techniczną, która będzie wykorzystywana do filtrowania danych oraz opcje:
 - `visible` - widoczność kolumny na listingu
 - `sumPageValues` - czy wartości danej kolumny są sumowane na aktywnej stronie
 - `sumAllValues` - czy wartości danej kolumny z całego listingu są sumowane

## Komunikacja z innymi mikroserwisami

Serwis listowy komunikuje się poprzez RestAPI z serwisem Formularzy oraz serwisem Danowym. Komunikacja z serwisem
Formularzy zapewnia możlwiość definiowania widoku listingu (jakie kolumny są prezentowane na listingu oraz ich ustawienia, 
filtry domyślne). Z zasobu struktury formularza pobierane są dane o kolumnach widocznych na listingu oraz
budowany jest domyślny `ProQLQuery` do pobierania danych z listingu.

Serwis Danowy serwuje dane do listingu na podstawie podstawowego `ProQLQuery` danej listy, lub `ProQLQuery`
z dodatkowymi warunkami (filtrowanie danych).

## Opis Rest API

<details>
<summary>
<code>GET</code> <code>/v1/queries/struct/{id}</code> <code>Pobranie struktury listy</code>
</summary>

#### Struktura listy
Zasób pozwala pobrać strukturę listy, tj.: metadane `id`, `nazwa` oraz informacje na temat kolumn dostępnych na listingu

#### Parametry
- `id` - id listy, np.: 651d70477624eb47cbc0049b 

#### Content-Type
```application/json```

#### Przykładowe wywołanie
```http request
GET http://{{host}}/v1/queries/struct/651d70477624eb47cbc0049b
Content-Type: application/json
```


<details>
<summary><b>Przykładowa odpowiedź</b></summary>

```json
{
  "id": "651d70477624eb47cbc0049b",
  "name": "Kanban lista",
  "columns": [
    {
      "techName": "64ff1717f76c5822c3bb5a63",
      "label": "ID",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a64",
      "label": "Name",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a65",
      "label": "Description",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a66",
      "label": "Answer",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a67",
      "label": "Created at",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a68",
      "label": "Due Date",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a69",
      "label": "Deadline",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6a",
      "label": "Sprint",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6b",
      "label": "Status",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6c",
      "label": "Type",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6d",
      "label": "Urgency",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6e",
      "label": "Priority",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6f",
      "label": "Extended Description",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a70",
      "label": "Orderer",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a71",
      "label": "Performer",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a72",
      "label": "Project",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a73",
      "label": "Metrics",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a74",
      "label": "Evaluation",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    }
  ]
}
```
</details>
</details>

<details>
<summary>
<code>GET</code> <code>/v1/queries/data/{id}/page/{pageNo}</code> <code>Pobranie danych listy ze strony</code>
</summary>

#### Listing danych
Zasób pozwala dane listy z wybranej strony

#### Parametry
- `id` - id listy, np.: 651d70477624eb47cbc0049b
- `pageNo` - numer strony do pobrania, np.: 1

#### Content-Type
```application/json```

#### Przykładowe wywołanie
```http request
GET http://{{host}}/v1/queries/data/651d70477624eb47cbc0049b/page/1
Content-Type: application/json
```


<details>
<summary><b>Przykładowa odpowiedź</b></summary>

Prezentuję skróconą odpowiedź, ponieważ `columns` zawiera dokładnie te same informacje co zwraca zasób `struct`.
Prezentuje też jedynie jeden rekord z całej odpowiedzi w `data` ponieważ każdy rekord składniowo wygląda tak samo,
różnią się jedynie wartości poszczególnych kolumn.
```json
{
  "meta": {
    "id": "651d70477624eb47cbc0049b",
    "totalElements": 386,
    "totalPages": 20,
    "numberOfElements": 20,
    "pageSize": 20,
    "pageNumber": 1
  },
  "columns": [
    {},
    {},
    {}
  ],
  "data": [
    {
      "ID": 161,
      "Name": "Realizacje dokumentacyjnych rysunków struktury aplikacji AZ",
      "Description": null,
      "Answer": null,
      "Created at": "2023-08-28",
      "Due Date": "2023-08-28",
      "Deadline": "2023-09-22",
      "Sprint": "B2-2023-09-02",
      "Status": "Done (canceled)",
      "Type": "Frontend Junior",
      "Urgency": "4. Usprawnienie (could have)",
      "Priority": "99",
      "Extended Description": null,
      "Orderer": {
        "id": 382,
        "login": null,
        "password": null,
        "email": null,
        "firstName": "Konarska Antonina",
        "lastName": null,
        "birthDate": null
      },
      "Performer": {
        "id": 383,
        "login": null,
        "password": null,
        "email": null,
        "firstName": "Skrypnyk Liliia",
        "lastName": null,
        "birthDate": null
      },
      "Project": {
        "id": 204,
        "name": "Helpdesk - Kanban",
        "status": null,
        "startDate": null,
        "endDate": null,
        "client": null,
        "orderingClient": null
      },
      "Metrics": {
        "id": 162,
        "budget": 1.0,
        "estimation": 1.0,
        "timeLeft": 0.0,
        "timeConsumed": 1.0
      },
      "Evaluation": null
    }
  ]
}
```
</details>
</details>

<details>
<summary>
<code>POST</code> <code>/v1/commands</code> <code>Utworzenie listy danych</code>
</summary>

#### Utworzenie listy danych
Zasób pozwala utworzyć listę danych na podstawie istniejącego formularza. Pozwala nadać liście nazwę
oraz sprecyzować domyślny rozmiar strony

#### Parametry
- brak

#### Body
```json
{
  "name": "Testowa Lista",
  "formId": "650d967c303b0a0bbdc0cb29",
  "pageSize": 20
}
```
- `name` - Nazwa listy, dowolna wartość tekstowa
- `formId` - id formularza z serwisu formularzy
- `pageSize` - domyślny rozmiar strony

#### Content-Type
```application/json```

#### Przykładowe wywołanie
```http request
POST http://{{host}}/v1/commands
Content-Type: application/json

{
  "name": "Testowa Lista",
  "formId": "650d967c303b0a0bbdc0cb29",
  "pageSize": 20
}
```

<details>
<summary><b>Przykładowa odpowiedź</b></summary>

```json
{
  "id": "651d70477624eb47cbc0049b",
  "name": "Testowa Lista",
  "columns": [
    {
      "techName": "64ff1717f76c5822c3bb5a63",
      "label": "ID",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a64",
      "label": "Name",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a65",
      "label": "Description",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a66",
      "label": "Answer",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a67",
      "label": "Created at",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a68",
      "label": "Due Date",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a69",
      "label": "Deadline",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6a",
      "label": "Sprint",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6b",
      "label": "Status",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6c",
      "label": "Type",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6d",
      "label": "Urgency",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6e",
      "label": "Priority",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a6f",
      "label": "Extended Description",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a70",
      "label": "Orderer",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a71",
      "label": "Performer",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a72",
      "label": "Project",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a73",
      "label": "Metrics",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    },
    {
      "techName": "64ff1718f76c5822c3bb5a74",
      "label": "Evaluation",
      "options": {
        "visible": true,
        "sumPageValues": false,
        "sumAllValues": false
      }
    }
  ]
}
```
</details>
</details>