// This is a program that calculates the best fare options in New York City.
// Created by J Herng.
import java.util.Scanner;
class BestFareCalculator {

    // Instance fields declaration
    int days;
    int rides;

    String[] fareOptions = {"Pay-per-ride", "7-day Unlimited Rides", "30-day Unlimited Rides"};
    double[] farePrices = {2.75, 33.00, 127.00};

    // Contructor method
    public BestFareCalculator(int daysNum, int ridesNum, int agesNum, boolean disability){
        this.days = daysNum;
        this.rides = ridesNum;

        // Condition for discount price if the person is 65 years old or older or facing disability
        if (agesNum >= 65 || disability){
            System.out.println("You will be given discount!");
            this.farePrices[0] = 1.35;
            this.farePrices[1] = 16.50;
            this.farePrices[2] = 63.50;
        }

    }

    // Method to calculate pay per ride if using the 7-day Unlimited Rides fare
    public double unlimited7Price(){

        int numOfWeeks = days / 7;
        int n = days % 7;

        if (n != 0){
            numOfWeeks++;
        }

        double payPerRide7 = numOfWeeks * farePrices[1] / rides;
        return payPerRide7;
    }

    // method to calculate pay per ride for all the three different fares
    public double[] getRidePrices(){
        double[] payPerRides = new double[3];
        payPerRides[0] = farePrices[0];
        payPerRides[1] = unlimited7Price();
        payPerRides[2] = farePrices[2] / rides;
        return payPerRides;
    }

    // method to compare the pay per ride within the 3 options to get the lowest price and best fare option
    public String getBestFare() {
        double[] payPerRides = getRidePrices();
        double lowestPrice = payPerRides[0];
        String bestOption = fareOptions[0];
        for (int i = 1; i < 3; i++) {
            if (payPerRides[i] < lowestPrice) {
                lowestPrice = payPerRides[i];
                bestOption = fareOptions[i];
            }
        }
        return "You should get the " + bestOption + " option at $" + lowestPrice + " per ride.";
    }

    public static void main(String[] args){

        // Object declaration of Scanner class
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter the number of days you will be using transit system (up to 30 days): ");
        int days = sc.nextInt();

        System.out.print("Please enter the number of individual rides you expect to take in that time: ");
        int rides = sc.nextInt();

        System.out.print("Please enter your age: ");
        int ages = sc.nextInt();

        System.out.println("Are you a disabled person? Give true for yes, false for no.");
        boolean disability = sc.nextBoolean();

        // Declaration of object of BestFareCalculator class
        BestFareCalculator person1 = new BestFareCalculator(days, rides, ages, disability);
        System.out.println(person1.getBestFare());
    }
}
