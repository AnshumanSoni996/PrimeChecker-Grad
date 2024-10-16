package cme.grad.code.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PrimeChecker {

    private static final Logger logger = LogManager.getLogger(PrimeChecker.class);
    private static PrimeCache primeCache = new PrimeCache();

    // Function to check if the number is prime
    public static boolean isPrime(int number) {
        logger.debug("Checking if {} is prime", number);
        if (primeCache.isPrimeCached(number)) {
            logger.debug("{} is already cached as prime", number);
            return true;
        }

        if (number <= 1) {
            logger.debug("{} is not prime", number);
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                logger.debug("{} is not prime", number);
                return false;
            }
        }

        // Add the prime number to the cache
        logger.info("Adding {} to prime cache", number);
        primeCache.addPrime(number);
        return true;
    }

    // Function to find prime numbers in the numeric sequence
    public static int[] findPrimeNumbers(String numericValue) {
        logger.info("Finding prime numbers in {}", numericValue);
        Set<Integer> primeNumbers = new HashSet<>();
        for (int i = 0; i < numericValue.length(); i++) {
            for (int j = i + 1; j <= numericValue.length(); j++) {
                try {
                    int number = Integer.parseInt(numericValue.substring(i, j));
                    if (isPrime(number)) {
                        logger.debug("Found prime number {}", number);
                        primeNumbers.add(number);
                    }
                } catch (NumberFormatException e) {
                    logger.error("Invalid number format: {}", e.getMessage());
                }
            }
        }
        logger.info("Found {} prime numbers", primeNumbers.size());
        int[] result = new int[primeNumbers.size()];
        int index = 0;
        for (Object prime : primeNumbers.stream().sorted().toArray()) {
            result[index++] = (int) prime;
            logger.info("Prime number: {}", prime);
        }
        return result;
    }

    // Main method
    public static void main(String[] args) {
        logger.info("Starting PrimeChecker program");
        Scanner scanner = new Scanner(System.in);
        String username;

        // Get username
        username = getUsername(scanner);

        logger.info("Username: {}", username);

        boolean continueProgram = true;
        while (continueProgram) {
            String numericValue = getNumericValue(scanner);
            if (numericValue.equals("exit")) {
                continueProgram = false;
            } else {
                logger.info("Numeric Value: {}", numericValue);

                // Find and print prime numbers in the numeric value sequence
                int[] primeNumbers = findPrimeNumbers(numericValue);
                System.out.print("Prime Numbers in sequence: ");
                for (int i = 0; i < primeNumbers.length; i++) {
                    System.out.print(primeNumbers[i]);
                    if (i < primeNumbers.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            if (continueProgram) {
                System.out.print("Do you want to enter another number? (y/n): ");
                String response = scanner.nextLine();
                while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
                    System.out.print("Invalid input. Please enter 'y' or 'n': ");
                    response = scanner.nextLine();
                }
                if (!response.equalsIgnoreCase("y")) {
                    continueProgram = false;
                }
            }
        }

        scanner.close();
        logger.info("Finished PrimeChecker program");
    }

    public static String getUsername(Scanner scanner) {
        while (true) {
            logger.debug("Prompting for username");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            try {
                ValidatorFactory.Validator validator = ValidatorFactory.getValidator("username");
                validator.validate(username);
                logger.info("Valid username: {}", username);
                return username;
            } catch (IllegalArgumentException e) {
                logger.error("Invalid username: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static String getNumericValue(Scanner scanner) {
        while (true) {
            logger.debug("Prompting for numeric value");
            System.out.print("Enter numeric value (or 'exit' to end the program): ");
            String numericValue = scanner.nextLine();
    
            if (numericValue.equalsIgnoreCase("exit")) {
                return "exit";
            }
    
            try {
                // Using the ValidatorFactory to validate input
                ValidatorFactory.Validator validator = ValidatorFactory.getValidator("emptyInput");
                validator.validate(numericValue);
                
                validator = ValidatorFactory.getValidator("specialCharacter");
                validator.validate(numericValue);
                
                logger.info("Valid numeric value: {}", numericValue);
                return numericValue;
            } catch (IllegalArgumentException e) {
                logger.error("Invalid numeric value: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}