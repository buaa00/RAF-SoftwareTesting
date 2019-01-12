package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * G2LargeDoubleMachine
 */
public class G2LargeDoubleMachine extends Machine {

    public G2LargeDoubleMachine() {
        super(MachineTier.G2, MachineSubTier.LARGE, 4);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier == null) throw new InvalidArgumentValError("gpuTier", "null");
        if (gpuTier != GpuTier.DOUBLE) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount > 2) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}