package model.errors;

/**
 * InvalidRequestError
 */
public class InvalidRequestError extends MachineError {

    private static final long serialVersionUID = 1L;

    public InvalidRequestError() {
        super("Invalid request!");
    }
}