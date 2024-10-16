package cme.grad.code.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PrimeCache {
    private static final String FILE_PATH = "prime_numbers.txt";
    private Set<Integer> primeCache = new HashSet<>();

    public PrimeCache() {
        // Load primes from the file at startup
        loadPrimesFromFile();
    }

    public void addPrime(int prime) {
        if (primeCache.add(prime)) {
            writePrimeToFile(prime);
        }
    }

    public boolean isPrimeCached(int number) {
        return primeCache.contains(number);
    }

    private void loadPrimesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int prime = Integer.parseInt(line);
                    primeCache.add(prime);
                } catch (NumberFormatException e) {
                    // Ignore lines that cannot be parsed as integers
                }
            }
        } catch (FileNotFoundException e) {
            // File not found is okay, there is no prime number stored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePrimeToFile(int prime) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (Integer.parseInt(line) == prime) {
                    return; // Prime number already exists in the file
                }
            }
        } catch (FileNotFoundException e) {
            // File not found is okay, there is no prime number stored
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(prime + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}