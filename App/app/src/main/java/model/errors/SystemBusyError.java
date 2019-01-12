package model.errors;

/**
 * SystemBusyError
 */
public class SystemBusyError extends MachineError {

    private static final long serialVersionUID = 1L;

    public SystemBusyError() {
        super("System can't handle your request at this time!");
    }

    
}