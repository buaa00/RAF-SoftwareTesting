package model.errors;

/**
 * MachineError
 */
public class MachineError extends Exception{
    
    private static final long serialVersionUID = 1L;

    public MachineError(String name) {
        super(name);
    }
    
}