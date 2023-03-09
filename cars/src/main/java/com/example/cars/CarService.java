package com.example.cars;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CarService {
    private List<Car> cars = new ArrayList<>();

    /*
     * @PostConstruct
     * {
     * "make": "Volkswagen",
     * "model": "Golf",
     * "year": 2006
     * }
     */
    
    public void addCar(Car car) {
        if (car.getMake() == null || car.getMake().isEmpty() ||
                car.getModel() == null || car.getModel().isEmpty() ||
                car.getYear() <= 1886 || car.getYear() >= 2024) {
            throw new IllegalArgumentException("Invalid car data");
        }
        cars.add(car);
    }

    public List<String> getAllCars() {
        List<String> carDetails = new ArrayList<>();
        for (Car car : cars) {
            carDetails.add("Make:" + car.getMake() + "  Model:" + car.getModel() + "  Year:" + car.getYear() + "  ");
        }
        return carDetails;
    }

    public Car deleteCar(int index) {
        if (index < 0 || index >= cars.size()) {
            throw new IllegalArgumentException("Invalid car index");
        }
        return cars.remove(index);
    }

    //Add a new parameter called keyword with the value you want to search for example: ("Toyota").
    public List<String> searchCars(String keyword) {
        List<String> matchingCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().toLowerCase().contains(keyword.toLowerCase()) ||
                    car.getModel().toLowerCase().contains(keyword.toLowerCase()) ||
                    String.valueOf(car.getYear()).contains(keyword)) {
                matchingCars.add("Make:" + car.getMake() + "  Model:" + car.getModel() + "  Year:" + car.getYear() + "  ");
            }
        }
        return matchingCars;
    }
}
