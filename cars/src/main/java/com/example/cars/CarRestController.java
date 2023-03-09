package com.example.cars;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarRestController {
    @Autowired
    private CarService carService;
    //private List<Car> cars = new ArrayList<>();

    /*
     * @PostConstruct
     * {
     * "make": "Volkswagen",
     * "model": "Golf",
     * "year": 2006
     * }
     */

    @PostMapping("/cars")
    public ResponseEntity<Object> addCar(@RequestBody Car car) {

        carService.addCar(car);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/cars")
    public List<String> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/search")
    public List<String> searchCars(@RequestParam String keyword) {
        return carService.searchCars(keyword);
    } //Add a new parameter called keyword with the value you want to search for example: ("Toyota").

    @DeleteMapping("/cars/{index}")
    public Car deleteCar(@PathVariable int index) {
        return carService.deleteCar(index);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
    }
}
