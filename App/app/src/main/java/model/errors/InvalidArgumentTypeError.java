package model.errors;

/**
 * InvalidArgumentTypeError
 */
public class InvalidArgumentTypeError extends MachineError{

    private static final long serialVersionUID = 1L;

    public InvalidArgumentTypeError(String name) {
        super("Field " + name + " received unexpected value type");
    }
    
}