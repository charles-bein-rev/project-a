//Written by Charley Bein
//Written 8/13/21
//DecimalFormat code sourced from https://stackoverflow.com/questions/8895337/how-do-i-limit-the-number-of-decimals-printed-for-a-double
package main;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Converter {
	private static Scanner scanner = new Scanner(System.in);
	private static DecimalFormat decimalFormat = new DecimalFormat("#.000");
	public static void main(String[] args) {
		//Run choose direction
		chooseConversion();
		
		if(scanner != null) {
			scanner.close();
		}
	}

	private static void chooseConversion() {
		//Choose SI -> Imperial or Imperial -> SI
		int directionChoice;
		int unitChoice;
		boolean invalidChoice;
		boolean quitSystem = false;
		boolean quitConversion = false;
		String input;
		while(!quitSystem) {
			do {
				invalidChoice = false;
				System.out.println("Are you converting FROM SI or Imperial? Choose:");
				System.out.println("1. SI/Metric");
				System.out.println("2. Imperial");
				System.out.println("3. Quit Program");
				input = scanner.next();
				directionChoice = parseDirection(input);
				if(directionChoice == -1) {
					invalidChoice = true;
					System.out.println("\n\n\nInvalid choice, please select a menu option.\n");
				}
			} while(invalidChoice);
			if(directionChoice == 3) {
				quitSystem = true;
				break;
			}
			
			quitConversion = false;
			while(!quitConversion) {
				do {
					invalidChoice = false;
					System.out.println("\nWhat kind of unit would you like to convert? Choose:");
					System.out.println("1. Distance (miles/kilometers)");
					System.out.println("2. Mass (kilograms/pounds)");
					System.out.println("3. Temperature (celsius/fahrenheit)");
					System.out.println("4. Back to direction choice");
					System.out.println("5. Quit Program");
					input = scanner.next();
					unitChoice = parseUnits(input);
					System.out.println();
					if(unitChoice == -1) {
						invalidChoice = true;
						System.out.println("\n\nInvalid choice, please select a menu option.");
					}
				} while(invalidChoice);
				switch(unitChoice) {
				case 1:
					distanceConversion(directionChoice);
					break;
				case 2:
					massConversion(directionChoice);
					break;
				case 3:
					temperatureConversion(directionChoice);
					break;
				case 4:
					quitConversion = true;
					System.out.println();
					break;
				case 5:
					quitConversion = true;
					quitSystem = true;
					break;
				}
				System.out.println();
			}
		}
	}

	private static int parseDirection(String input) {
		int choice;

		switch(input.toLowerCase()) {
		case "1":
		case "2":
		case "3":
			choice = Integer.parseInt(input);
			break;
		case "si":
		case "metric":
			choice = 1;
			break;
		case "imperial":
			choice = 2;
			break;
		case "quit":
		case "q":
			choice = 3;
			break;
		default:
			choice = -1;
		}

		return choice;
	}

	private static int parseUnits(String input) {
		int choice;

		switch(input.toLowerCase()) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
			choice = Integer.parseInt(input);
			break;
		case "distance":
		case "kilometers":
		case "miles":
		case "km":
		case "mi":
			choice = 1;
			break;
		case "mass":
		case "weight":
		case "kilograms":
		case "pounds":
		case "kg":
		case "lb":	
			choice = 2;
			break;
		case "temperature":
		case "temp":
		case "farenheit":
		case "celsius":
		case "f":
		case "c":
			choice = 3;
		case "back":
		case "b":
			choice = 4;
			break;
		case "quit":
		case "q":
			choice = 5;
			break;
		default:
			choice = -1;
		}

		return choice;
	}

	private static void distanceConversion(int direction) {
		//1. km -> mi divide by 1.609
		//2. mi -> km multiply by 1.609
		float input;
		double result;

		if(direction == 1) {
			System.out.println("Enter the distance in kilometers:");
			input = scanner.nextFloat();
			result = input / 1.609;
			System.out.println(input + " km is equal to " + decimalFormat.format(result) + " mi");
		} else if(direction == 2) {
			System.out.println("Enter the distance in miles:");
			input = scanner.nextFloat();
			result = input * 1.609;
			System.out.println(input + " mi is equal to " + decimalFormat.format(result) + " km");

		} else {
			System.out.println("Should never be reached");
		}
	}

	private static void massConversion(int direction) {
		//1. kg -> lb multiply by 2.205
		//2. lb -> kg divide by 2.205
		float input;
		double result;
		
		if(direction == 1) {
			System.out.println("Enter the mass in kilograms:");
			input = scanner.nextFloat();
			result = input * 2.205;
			System.out.println(input + " kg is equal to " + decimalFormat.format(result) + " lb");
		} else if(direction == 2) {
			System.out.println("Enter the mass in pounds:");
			input = scanner.nextFloat();
			result = input / 2.205;
			System.out.println(input + " lb is equal to " + decimalFormat.format(result) + " kg");
		} else {
			System.out.println("Should never be reached");
		}
	}

	private static void temperatureConversion(int direction) {
		//1. C -> F multiply by 1.8 and add 32
		//2. F -> C subtract 32 and multiply by .5556
		float input;
		double result;
		
		if(direction == 1) {
			System.out.println("Enter the temperature in degrees Celsius:");
			input = scanner.nextFloat();
			result = (input * 1.8) + 32;
			System.out.println(input + "°C is equal to " + decimalFormat.format(result) + "°F");
		} else if(direction == 2) {
			System.out.println("Enter the temperature in degrees Fahrenheit:");
			input = scanner.nextFloat();
			result = (input - 32) * .5556;
			System.out.println(input + "°F is equal to " + decimalFormat.format(result) + "°C");
		} else {
			System.out.println("Should never be reached");
		}
	}


}
