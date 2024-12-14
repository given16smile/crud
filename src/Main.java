package main;
import vaika.*;
import utile.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        try {
            List<Car> cars=Car.find(60000.0, null, null, null);

       for (Car c : cars) {
            System.out.println(c.getIdCar());
       }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


