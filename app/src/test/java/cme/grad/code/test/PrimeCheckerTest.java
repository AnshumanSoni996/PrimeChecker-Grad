package cme.grad.code.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class PrimeCheckerTest {

    @Test
    void testIsPrime() {
        assertTrue(PrimeChecker.isPrime(2));
        assertTrue(PrimeChecker.isPrime(3));
        assertTrue(PrimeChecker.isPrime(5));
        assertFalse(PrimeChecker.isPrime(1));
        assertFalse(PrimeChecker.isPrime(4));
        assertFalse(PrimeChecker.isPrime(6));
    }

    @Test
    void testFindPrimeNumbers() {
        int[] primeNumbers = PrimeChecker.findPrimeNumbers("12345");
        Set<Integer> expectedPrimes = new HashSet<>();
        expectedPrimes.add(2);
        expectedPrimes.add(3);
        expectedPrimes.add(5);
        expectedPrimes.add(23);
        assertEquals(expectedPrimes.size(), primeNumbers.length);
        for (int prime : primeNumbers) {
            assertTrue(expectedPrimes.contains(prime));
        }

        primeNumbers = PrimeChecker.findPrimeNumbers("abcdefg");
        assertEquals(0, primeNumbers.length);

        primeNumbers = PrimeChecker.findPrimeNumbers("101");
        expectedPrimes.clear();
        expectedPrimes.add(101);
        assertEquals(expectedPrimes.size(), primeNumbers.length);
        for (int prime : primeNumbers) {
            assertTrue(expectedPrimes.contains(prime));
        }
    }

    @Test
    void testGetUsername() {
        // Test valid username
        Scanner scanner = new Scanner("username\n");
        String username = PrimeChecker.getUsername(scanner);
        assertEquals("username", username);

        // Test username with spaces, then a valid username
        scanner = new Scanner("username with space\nvalidusername\n");
        username = PrimeChecker.getUsername(scanner);
        assertEquals("validusername", username);

        // Test empty username, then a valid username
        scanner = new Scanner(" \nvalidusername\n");
        username = PrimeChecker.getUsername(scanner);
        assertEquals("validusername", username);
    }

    @Test
    void testGetNumericValue() {
        // Test valid numeric value
        Scanner scanner = new Scanner("12345\n");
        String numericValue = PrimeChecker.getNumericValue(scanner);
        assertEquals("12345", numericValue);
    
        // Test invalid input, then valid numeric value
        scanner = new Scanner("abcdefg\n12345\n");
        numericValue = PrimeChecker.getNumericValue(scanner);
        assertEquals("12345", numericValue);
    
        // Test exit command
        scanner = new Scanner("exit\n");
        numericValue = PrimeChecker.getNumericValue(scanner);
        assertEquals("exit", numericValue);
    }
}