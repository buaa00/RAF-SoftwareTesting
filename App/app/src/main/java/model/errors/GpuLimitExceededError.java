package model.errors;

/**
 * GpuLimitExceededError
 */
public class GpuLimitExceededError extends MachineError {

    public GpuLimitExceededError(String gpuCount, String gpuType) {
        super("GPU limit is exceeded! Current number of GPUs is "+gpuCount+"of type "+gpuType);
    }
}