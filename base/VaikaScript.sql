-- Créer la base de données si elle n'existe pas déjà
CREATE DATABASE IF NOT EXISTS cars;

-- Utiliser la base de données
USE cars;


CREATE TABLE users (
    idUser INT AUTO_INCREMENT PRIMARY KEY,  
    pseudo VARCHAR(255) NOT NULL,          
    pwd VARCHAR(255) NOT NULL              
);



-- Création des tables avec vérification de leur existence
CREATE TABLE IF NOT EXISTS Brand (
    IdBrand INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS BodyType (
    IdBodyType INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS EnginType (
    IdEnginType INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Vendeur (
    IdVendeur INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Garage (
    IdGarage INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Mecanicien (
    IdMecanicien INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Model (
    IdModel INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    IdBrand INT NOT NULL,
    IdEnginType INT NOT NULL,
    IdBodyType INT NOT NULL,
    Color VARCHAR(50) NOT NULL,
    FOREIGN KEY (IdBrand) REFERENCES Brand(IdBrand),
    FOREIGN KEY (IdEnginType) REFERENCES EnginType(IdEnginType),
    FOREIGN KEY (IdBodyType) REFERENCES BodyType(IdBodyType)
);

CREATE TABLE IF NOT EXISTS Car (
    IdCar INT AUTO_INCREMENT PRIMARY KEY,
    IdModel INT NOT NULL,
    Prix DECIMAL(15,2),
    FOREIGN KEY (IdModel) REFERENCES Model(IdModel)
);

CREATE TABLE IF NOT EXISTS Vente (
    IdVente INT AUTO_INCREMENT PRIMARY KEY,
    DateVente DATE NOT NULL,
    IdVendeur INT,
    Client VARCHAR(100),
    IdCar INT NOT NULL,
    FOREIGN KEY (IdVendeur) REFERENCES Vendeur(IdVendeur),
    FOREIGN KEY (IdCar) REFERENCES Car(IdCar)
);

CREATE TABLE IF NOT EXISTS Reparation (
    IdReparation INT AUTO_INCREMENT PRIMARY KEY,
    DateDebutReparation DATE NOT NULL,
    DateFinReparation DATE NOT NULL,
    IdCar INT NOT NULL,
    IdGarage INT NOT NULL,
    IdMecanicien INT NOT NULL,
    Client VARCHAR(100),
    Prix DECIMAL(15,2),
    Description VARCHAR(100),
    FOREIGN KEY (IdCar) REFERENCES Car(IdCar),
    FOREIGN KEY (IdGarage) REFERENCES Garage(IdGarage),
    FOREIGN KEY (IdMecanicien) REFERENCES Mecanicien(IdMecanicien)
);
