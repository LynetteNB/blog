package com.example.blog.services;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    private double side;

    public CalculateService() { }

    public String calculateInfo (double side) {
        this.side = side;
        return calculatePerimeter() + calculateArea() + calculateVolume() + calculateCircleArea() + calculateCircumference() + isPrime();
    }

    private String calculatePerimeter() {
        return "The perimeter of a perfect square with length " + side + " is " + side*4 + ". \n";
    }
    private String calculateArea() {
        return "The area of a perfect square with length " + side + " is " + side*side + ". \n";
    }
    private String calculateVolume() {
        return "The volume of a perfect cube with length " + side + " is " + side*side*side + ". \n";
    }
    private String calculateCircleArea() {
        return "The area of a circle with radius " + side + " is " + side*side*Math.PI + ". \n";
    }
    private String calculateCircumference() {
        return "The circumference of a circle with radius " + side + " is " + side*2*Math.PI + ". \n";
    }
    private String isPrime() {
        if(side <= 1){
            return side + " is not prime.";
        } else {
            for(int i = 2; i < side; i++){
                if(side % i == 0) {
                    return side + " is not prime.";
                }
            }
            return side + " is prime!";
        }
    }
}
