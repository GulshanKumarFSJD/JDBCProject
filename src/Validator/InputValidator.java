package Validator;

public class InputValidator {
    public static boolean isValidAge(int age) {
        return age > 0 && age <= 100;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidGrade(String grade) {
        return grade != null && !grade.trim().isEmpty();
    }
}

