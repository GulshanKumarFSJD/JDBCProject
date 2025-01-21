package GlobalExceptionHandler;

public class ExceptionHandler {
    public static void handleException(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}
