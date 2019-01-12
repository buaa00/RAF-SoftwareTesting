package model.errors;

/**
 * MachineStateRejectionError
 */
public class MachineStateRejectionError extends MachineError {

    private static final long serialVersionUID = 1L;

    public MachineStateRejectionError(String status, String action) {
        super("Resource is in "+ status + "state and can't perform " + action);
    }
}