DROP DATABASE IF EXISTS concesionario;
CREATE DATABASE concesionario;
Use concesionario;



CREATE TABLE `coche` (
  `Id` int unsigned NOT NULL AUTO_INCREMENT primary key,
  `Matricula` varchar(10) DEFAULT NULL,
  `Marca` varchar(20) DEFAULT NULL,
  `Modelo` varchar(20) DEFAULT NULL,
  `FechaSalida` date DEFAULT NULL,
  `Precio` double(6,2) DEFAULT NULL,
  `Carga` varchar(20) DEFAULT NULL,
  `Plazas` int DEFAULT NULL,
  `Tipo` enum('FAMILIAR','DEPORTIVO','TODOTERRENO','LIMUSINA') DEFAULT NULL
  );
  
 INSERT INTO Coche (Matricula, Marca, Modelo, FechaSalida, Precio, Carga, Plazas, Tipo) 
 VALUES ("1234ABC","Nissan","Almera Tino", "2002-01-01", 1500.00, "300KG", 5,"FAMILIAR");
 
 


