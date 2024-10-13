DROP DATABASE IF EXISTS concesionario;
CREATE DATABASE concesionario;
Use concesionario;


 CREATE TABLE `vehiculo` (
  `Id` int unsigned NOT NULL AUTO_INCREMENT primary key,
  `Matricula` varchar(20) DEFAULT NULL,
  `Marca` varchar(50) DEFAULT NULL,
  `Modelo` varchar(50) DEFAULT NULL,
  `Precio` double(10,2) DEFAULT NULL,
  `Carga` varchar(50) DEFAULT NULL,
  `tipo` varchar(10) DEFAULT NULL
  );
 INSERT INTO vehiculo (Matricula, Marca, Modelo, Precio, Carga, Tipo)
  Values ("1234ABC", "Nissan", "Almera Tino", 1500.00, "300", "coche");
  
  INSERT INTO vehiculo (Matricula, Marca, Modelo, Precio, Carga, Tipo)
  Values ("1793ASC", "ford", "raptor", 2000.00, "1500", "coche");
 
  INSERT INTO vehiculo (Matricula, Marca, Modelo, Precio, Carga, Tipo)
  Values ("2024CYCLE", "Kawasaki", "3000", 2000.00, "10", "moto");
 
  INSERT INTO vehiculo (Matricula, Marca, Modelo, Precio, Carga, Tipo)
  Values ("M071C0", "Brum Brum", "2k", 200.20, "22", "moto");
  
  
CREATE TABLE `coche` (
  `Id` int unsigned NOT NULL AUTO_INCREMENT ,
  `IdVehiculo` int unsigned NOT NULL,
  `Plazas` int DEFAULT NULL,
  `Tipo` varchar(20) DEFAULT NULL,
   PRIMARY KEY (Id),
   FOREIGN KEY (IdVehiculo) REFERENCES Vehiculo(Id) ON DELETE CASCADE ON UPDATE CASCADE
  );
 
  
  INSERT INTO coche(IdVehiculo, Plazas, Tipo)
  Values (1,5,"FAMILIAR");
  
  INSERT INTO coche(IdVehiculo,Plazas, Tipo)
  Values (2,6,"TODOTERRENO");
  
 CREATE TABLE `moto` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdVehiculo` int unsigned NOT NULL,
  `Plazas` int DEFAULT NULL,
  `Tipo` varchar(20) DEFAULT NULL,
    PRIMARY KEY (Id),
   FOREIGN KEY (IdVehiculo) REFERENCES Vehiculo(Id) ON DELETE CASCADE ON UPDATE CASCADE
  );

 INSERT INTO moto(IdVehiculo, Plazas, Tipo)
  Values (3, 1, "DEPORTIVA");
  
  INSERT INTO moto(IdVehiculo,Plazas, Tipo)
  Values (4, 3, "MONTANNA");