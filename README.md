#  Aplicatie pentru spatii de coworking (App for Coworking Spaces)
## Descriere:
Acest proiect permite utilizatorilor sa inchirieze
birouri pe o anumita durata de timp, din spatii special destinate pentru munca pe care o desfasoara.  Utilizatorii pot vedea spatiile de lucru din aplicatie si sa inchirieze un birou pentru o anumita perioada de timp.  

## Aplicatia de Front-End: (Mobile Flutter App)
https://github.com/BogdanNP/ProiectPS_Mobile

## Functionalitati:  
### Utilizator Neingregistrat:
    * vizualizarea spatiilor
    * vizualizarea preturilor  
    * creare cont  
    * logare in cont  
### Utilizator Inregistrat:
    * vizualizarea spatiilor  
    * vizualizarea preturilor  
    * vizualizarea in timp real a birourilor ocupate  
    * posibilitatea de inchirere a unui birou  
    * anularea comenzii plasate
    * plata comenzii 
    * abonare in lista de asteptare
    * dezabonare din lista de asteptare
### Administrator:
    * crearea de conturi  
    * programarea utilizatorilor  
    * anularea comenzilor plasate de catre utilizatori  
    * vizualizare in timp real a birourilor  
    * adaugarea/stergerea de camerea  
    * adaugarea/stergerea de birouri
    * abonare utilizator in lista de asteptare
    * dezabonare utilizator din lista de asteptare

## Diagrama Baza de Date:
![DiagramaBD](diagramaBD.png)

## Endpoint-uri:  
**Adresa: localhost:8080/demo**  
**Adresa pentru MobileApp: 10.0.2.2:8080/demo**  
**Toate requesturile trimit un raspuns de forma:**  
```
{
    "status": "Error" / "Success",
    "message" "Some message" / null,
    "data": JSON Object / null
}
```
### /user:  
    * GET /all -> returneaza o lista cu toti utilizatorii  
    * POST /add -> adauga un user  
                -> body: {"username",    "password", "type"}  
    * PUT /update -> modifica un user
                  -> body: {"id"(REQUIRED), "username", "password", "type"}  
    * DELETE /delete -> sterge un user
                     -> param: $id  
### /desk
    * GET /all -> returneaza o lista cu toate birourile  
    * POST /add -> adauga un birou  
                -> body: {"width", "length", "height", "tariff", "tariff_type"}    
    * PUT /update -> modifica un birou
                  -> body: {"id"(REQUIRED), "width", "length", "height", "tariff", "tariff_type"}  
    * DELETE /delete -> sterge un user
                     -> param: $id  

### /room
    * GET /all -> returneaza o lista cu toate camerele  
    * POST /add -> adauga o camera  
                -> body: {"width", "length", "desk_list", "details"}    
    * PUT /update -> modifica o camera
                  -> body: {"id"(REQUIRED), "width", "length", "desk_list", "details"}  
    * DELETE /delete -> sterge o camera
                     -> param: $id  

### /desk_request
    * GET /all -> returneaza o lista cu toate rezervarile create  
    * POST /add -> adauga o noua rezervare  
                -> body: {"status", "user_id", "desk_id", "start_date", "end_date"}    
    * PUT /update -> modifica o rezervare
                  -> body: {"id"(REQUIRED), "status", "user_id", "desk_id", "start_date", "end_date"}  
    * DELETE /delete -> sterge o rezervare
                     -> param: $id 

### /order
    * GET /all -> returneaza o lista cu toate comenzile  
    * POST /add -> adauga o comanda  
                -> body: {"total", "user_id", "desk_id", "status"}    
    * PUT /update -> modifica o comanda
                  -> body: {"id"(REQUIRED), "total", "user_id", "desk_id", "status"}  
    * DELETE /delete -> sterge o comanda
                     -> param: $id

### /wating_list
    * GET /check_persons -> returneaza o lista cu un utilizatorii care sunt in lista de asteptare  
    * POST /add -> adauga un utilizator in lista de asteptare  
                -> body {"user_id", "desk_id"}  
    * POST /check-status -> returneaza statusul biroului  
                         -> body {"desk_id"}  
    * DELETE /remove -> sterge un utilizator din lista de asteptare
                     -> param: $id  
