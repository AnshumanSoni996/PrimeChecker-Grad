package cme.grad.code.test;

public class ValidatorFactory {
    public interface Validator {
        void validate(String input) throws IllegalArgumentException;
    }

    public static class SpecialCharacterValidator implements Validator {
        @Override
        public void validate(String input) throws IllegalArgumentException {
            if (!input.matches("\\d+")) {
                throw new IllegalArgumentException("Input must contain only digits or 'exit' to end program");
            }
        }
    }

    public static class EmptyInputValidator implements Validator {
        @Override
        public void validate(String input) throws IllegalArgumentException {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
        }
    }

    public static class UsernameValidator implements Validator {
        @Override
        public void validate(String input) throws IllegalArgumentException {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty.");
            }
            if (input.contains(" ")) {
                throw new IllegalArgumentException("Username cannot contain spaces.");
            }
            if (input.length() < 3 || input.length() > 20) {
                throw new IllegalArgumentException("Username length must be between 3 and 20 characters.");
            }
        }
    }

     public static Validator getValidator(String type) {
        if (type.equals("specialCharacter")) {
            return new SpecialCharacterValidator();
        } else if (type.equals("emptyInput")) {
            return new EmptyInputValidator();
        } else if (type.equals("username")) {
            return new UsernameValidator();
        } else {
            throw new UnsupportedOperationException("Unsupported validator type.");
        }
    }
}