package cleo;

/**
 * Represents exceptions specific to the Cleo application.
 * This exception is thrown when the application encounters
 * an invalid input or any issue during its operation.
 */
public class CleoException extends Exception {
    /**
     * Creates a new CleoException with the specified message.
     *
     * @param message The message of the exception.
     */
    public CleoException(String message) {
        super(message);
    }
}
