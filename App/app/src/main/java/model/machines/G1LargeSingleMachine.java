package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * G1LargeSingleMachine
 */
public class G1LargeSingleMachine extends Machine {

    public G1LargeSingleMachine() {
        super(MachineTier.G1, MachineSubTier.LARGE, 4);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier == null) throw new InvalidArgumentValError("gpuTier", "null");
        if (gpuTier != GpuTier.SINGLE) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount > 1) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}