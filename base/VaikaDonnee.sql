insert into users (pseudo,pwd) values ('test', 'test');

INSERT INTO BRAND (Name) VALUES ('Toyota');
INSERT INTO BRAND (Name) VALUES ('Honda');
INSERT INTO BRAND (Name) VALUES ('Ford');
INSERT INTO BRAND (Name) VALUES ('Chevrolet');
INSERT INTO BRAND (Name) VALUES ('Volkswagen');
INSERT INTO BRAND (Name) VALUES ('BMW');
INSERT INTO BRAND (Name) VALUES ('Mercedes-Benz');
INSERT INTO BRAND (Name) VALUES ('Audi');
INSERT INTO BRAND (Name) VALUES ('Nissan');
INSERT INTO BRAND (Name) VALUES ('Hyundai');


INSERT INTO BodyType (Name) VALUES ('Sedan');
INSERT INTO BodyType (Name) VALUES ('Hatchback');
INSERT INTO BodyType (Name) VALUES ('SUV');
INSERT INTO BodyType (Name) VALUES ('Coupe');
INSERT INTO BodyType (Name) VALUES ('Convertible');
INSERT INTO BodyType (Name) VALUES ('Minivan');
INSERT INTO BodyType (Name) VALUES ('Pickup');
INSERT INTO BodyType (Name) VALUES ('Wagon');
INSERT INTO BodyType (Name) VALUES ('Crossover');
INSERT INTO BodyType (Name) VALUES ('Truck');



INSERT INTO EnginType (Name) VALUES ('Gasoline');
INSERT INTO EnginType (Name) VALUES ('Diesel');
INSERT INTO EnginType (Name) VALUES ('Hybrid');
INSERT INTO EnginType (Name) VALUES ('Electric');
INSERT INTO EnginType (Name) VALUES ('Plug-in Hybrid');
INSERT INTO EnginType (Name) VALUES ('Flex-fuel');
INSERT INTO EnginType (Name) VALUES ('Hydrogen');
INSERT INTO EnginType (Name) VALUES ('Natural Gas');
INSERT INTO EnginType (Name) VALUES ('Propane');
INSERT INTO EnginType (Name) VALUES ('Biofuel');



INSERT INTO Vendeur (Name) VALUES ('John Smith');
INSERT INTO Vendeur (Name) VALUES ('Emily Johnson');
INSERT INTO Vendeur (Name) VALUES ('Michael Williams');
INSERT INTO Vendeur (Name) VALUES ('Emma Jones');
INSERT INTO Vendeur (Name) VALUES ('James Brown');
INSERT INTO Vendeur (Name) VALUES ('Olivia Davis');
INSERT INTO Vendeur (Name) VALUES ('William Miller');
INSERT INTO Vendeur (Name) VALUES ('Sophia Wilson');
INSERT INTO Vendeur (Name) VALUES ('Benjamin Moore');
INSERT INTO Vendeur (Name) VALUES ('Isabella Taylor');

CREATE TABLE Garage(
    Idgarage SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);
INSERT INTO Garage (Name) VALUES ('First Avenue Garage');
INSERT INTO Garage (Name) VALUES ('Main Street Auto Repair');
INSERT INTO Garage (Name) VALUES ('Central Garage');
INSERT INTO Garage (Name) VALUES ('Downtown Service Center');
INSERT INTO Garage (Name) VALUES ('Westside Auto Shop');
INSERT INTO Garage (Name) VALUES ('North End Garage');
INSERT INTO Garage (Name) VALUES ('Southside Repair Shop');
INSERT INTO Garage (Name) VALUES ('East End Auto Care');
INSERT INTO Garage (Name) VALUES ('City Auto Garage');
INSERT INTO Garage (Name) VALUES ('Suburban Service Center');


INSERT INTO Mecanicien (Name) VALUES ('Daniel Clark');
INSERT INTO Mecanicien (Name) VALUES ('Sophie Walker');
INSERT INTO Mecanicien (Name) VALUES ('Matthew Hall');
INSERT INTO Mecanicien (Name) VALUES ('Grace Evans');
INSERT INTO Mecanicien (Name) VALUES ('Andrew Hill');
INSERT INTO Mecanicien (Name) VALUES ('Lily Morgan');
INSERT INTO Mecanicien (Name) VALUES ('Jack Allen');
INSERT INTO Mecanicien (Name) VALUES ('Chloe Turner');
INSERT INTO Mecanicien (Name) VALUES ('Ryan Carter');
INSERT INTO Mecanicien (Name) VALUES ('Mia Roberts');


