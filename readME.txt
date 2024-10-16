# Prime Number Checker

A Java application that finds prime numbers within a given numeric sequence.

## Prerequisites

- Java Development Kit (JDK) 21+
- Gradle 8.8+

## Setup

1. Clone the repository or unzip the project files.
2. Navigate to the project root directory.

## Build

Run: 'gradle build'

## Run Application

Execute: 'gradle run'

## Run Tests

Execute: 'gradle test'

## Project Structure

- 'app/src/main/java/cme/grad/code/test/': Main Java source files
- 'app/src/test/java/cme/grad/code/test/': Test files
- 'app/src/main/resources/': Configuration files
- 'logs/': Log files directory

## Key Files

- 'PrimeChecker.java': Main class with prime checking logic
- 'ValidatorFactory.java': Input validation logic
- 'PrimeCache.java': Prime number caching
- 'log4j2.xml': Logging configuration

## Logging

Logs are written to console and 'logs/app.log'.

## Notes

- The app creates 'prime_numbers.txt' to cache primes.
- Ensure write permissions for logging and caching.

## Troubleshooting

- Verify Java and Gradle versions.
- Check 'logs' directory exists and is writable.