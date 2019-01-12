package model.errors;

/**
 * InvalidArgumentValError
 */
public class InvalidArgumentValError extends MachineError{

    private static final long serialVersionUID = 1L;

    public InvalidArgumentValError(String name, String value) {
        super("Field " + name + " received invalid argument " + value);
    }
    
}