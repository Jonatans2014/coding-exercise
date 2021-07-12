# Accela Coding Exercise

## Running this app locally
```
git clone https://github.com/Jonatans2014/coding-exercise.git
cd coding-exercise&target folders
./mvnw package
java -jar target/coding-exercise-0.0.1-SNAPSHOT.jar
```


## Running the test 
```
./mvnw test
```
Technologies used on this app are:
* Spring Boot
* Thymeleaf
* Mockito
* jUnit Jupiter  
* H2 in-memory DB

You can then access the app here: http://localhost:8080/

<img width="1042" alt="home-page" src="https://user-images.githubusercontent.com/15609932/125278331-7f4f1780-e30a-11eb-86c2-10ec6089d196.PNG">

Based on input from the user, the app can:
1. Add Person (id, firstName, lastName)
2. Edit Person (firstName, lastName)
3. Delete Person (id)
4. Add Address to person [multiple required] (id, street, city, state, postalCode)
5. Edit Address (street, city, state, postalCode)
6. Delete Address (id)
7. Count Number of Persons
8. List Persons

Add Person: click  on the `Add Person` button on the main page and add  new person:

<img width="1042" alt="add-person-page" src="https://user-images.githubusercontent.com/15609932/125279147-80cd0f80-e30b-11eb-8127-169e04107777.PNG">

Edit Person: click  on the name of the person(Hyperlink) on the main page and then click on `Edit Person` button:
<img width="1042" alt="edit-person-page" src="https://user-images.githubusercontent.com/15609932/125280723-4e241680-e30d-11eb-8f8d-a2105d6c532a.PNG">

Delete Person: click on the `Delete Person` button on the page above

Add Address to person: click on `Add Address` button
<img width="1042" alt="edit-person-page" src="https://user-images.githubusercontent.com/15609932/125281382-11a4ea80-e30e-11eb-989d-0c9dd4be7bc2.PNG">

Edit Address: click on the address `id`(Hyperlink) then edit the address
<img width="1042" alt="edit-adress-page" src="https://user-images.githubusercontent.com/15609932/125282070-e242ad80-e30e-11eb-8f5d-8020be349b7f.PNG">
<img width="1042" alt="edit-adress-page" src="https://user-images.githubusercontent.com/15609932/125282604-7ad92d80-e30f-11eb-910a-8b0d15722c01.PNG">

Delete Address: click on the `Delete Address` button on the page above

Count Number of Persons: is on top left of the main page.

List Persons: All persons are listed on the main page.