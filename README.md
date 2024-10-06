# Order System in Java and Angular

This program has two parts, the backend in Java 17 using Spring Boot 3.3.4 and PostgreSQL for database, and the frontend in Angular 17.3.10.

## How execute the program?

### Database Configuration

First create an empty database in Postgres, in this case I use the default user and port of Postgres. If you need to update the settings of database, you can update the file application.properties. However if you want to use the same default 
settings, just set the same variables you can see in this image:

![image](https://github.com/user-attachments/assets/a87ccf32-088e-475f-9013-8a099cf038ae)

### Backend

To run the program, open the backend part first. The java project was made using Maven, so check it first in your IDE. Execute The program in your IDE and you will something like this:

![image](https://github.com/user-attachments/assets/476c4df5-910b-467f-9100-32ad5ba5afcf)

If you only want to prove the backend functionality, you can try the examples on JSON file in "postman" folder:

![image](https://github.com/user-attachments/assets/59c7f119-47de-41c4-b451-04413b4c6ce0) ![image](https://github.com/user-attachments/assets/cd0361d6-8ef3-4edf-83b6-b6766e862772) ![image](https://github.com/user-attachments/assets/482badcc-1b5f-49cf-bffb-3166b27a35f7)

Just to create, update or delete an order, you can try only with postman, bacause that part wasn't developed in the frontend part. However you can create it using the examples of JSON file:

![image](https://github.com/user-attachments/assets/b2b29153-fa78-4756-bacc-2eaecc13ba68)
![image](https://github.com/user-attachments/assets/ada42889-34ea-48b4-b1be-16dc65d219b6)

### Frontend

To try the frontend you will need Node.js V20.18.0, npm 10.8.2 and Angular 17.3.10. After that open the frontend part in your IDE and execute the command: 

```
ng serve --o
```

After that you will see this in console:

![image](https://github.com/user-attachments/assets/71d9a764-7480-4b16-a2ea-051a4b8b6564)

And the graphic interface is showed opening a new tab in your default browser:

![image](https://github.com/user-attachments/assets/239614a1-4358-438a-86be-df24e98f3d8d)

#### Client

So, the first part is going to Clients part, where you will see the list of clients created previously on postman, else you will see a message 
of "not results found" in case the table is empty:

![image](https://github.com/user-attachments/assets/11fd80e0-fab3-4591-8cba-6884995d8e0e)

If you want to create a new client, just go to "Crear Nuevo Cliente":

![image](https://github.com/user-attachments/assets/40820775-3ba1-4f99-8098-d8fa21c7e877)

And you will see it in the list page:

![image](https://github.com/user-attachments/assets/bc2145e9-53d3-4578-a7dd-9f585c82fdbd)

If you want to edit, press the button "Editar":

![image](https://github.com/user-attachments/assets/7d4b9a89-cf4a-4e78-9454-43c9402021cd)

![image](https://github.com/user-attachments/assets/f6b917d5-9705-4456-aed5-485fcc18c8c4)

If you want to delete, press the button "Borrar". After delete you will see a message indicating the correct functionality:

![image](https://github.com/user-attachments/assets/561aff8e-5acb-4dcb-b118-fce183acaff5)

![image](https://github.com/user-attachments/assets/45157fb4-881d-4592-aab2-c0db2e4f18fe)


#### Article

So, the second part is going to Articles part, where you will see the list of articles created previously on postman, else you will see a message 
of "not results found" in case the table is empty:

![image](https://github.com/user-attachments/assets/b5301f7a-b75c-4377-bc3b-85481041f0fb)

If you want to create a new article, just go to "Crear Nuevo Articulo":

![image](https://github.com/user-attachments/assets/2f74966f-57c0-4208-87b5-44386af8d58e)

And you will see it in the list page:

![image](https://github.com/user-attachments/assets/698038e3-b70f-4d64-aad6-8fd3c246d6df)

If you want to edit, press the button "Editar":

![image](https://github.com/user-attachments/assets/91f3fb42-83d3-4a27-a6a3-3a83ae0a6f71)

![image](https://github.com/user-attachments/assets/f873b32b-0de3-4790-a423-7f7bc9fada58)

If you want to delete, press the button "Borrar". After delete you will see a message indicating the correct functionality:

![image](https://github.com/user-attachments/assets/9b0ad0e6-c30e-43e5-b998-3c7c375b66ac)

![image](https://github.com/user-attachments/assets/278e09f7-9537-401d-bbc6-1ea8e495ce3a)


#### Order

So, the second part is going to Articles part, where you will see the list of articles created previously on postman, else you will see a message 
of "not results found" in case the table is empty:

![image](https://github.com/user-attachments/assets/88a237d7-3ca5-4e18-bed8-16d15019bfa3)

In the case of creating and editing as I mentioned before is not finished, so these functionalities are not working correctly.

If you want to delete, press the button "Borrar". After delete you will see a message indicating the correct functionality:

![image](https://github.com/user-attachments/assets/ebfba5ff-1df2-4de8-b417-379ae39ca1b6)

![image](https://github.com/user-attachments/assets/ef9f4ab3-3fc5-4daf-a84e-dae0cb6cd349)




