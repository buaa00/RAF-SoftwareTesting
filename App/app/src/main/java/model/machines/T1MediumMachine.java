package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * T1MediumMachine
 */
public class T1MediumMachine extends Machine {

    public T1MediumMachine() {
        super(MachineTier.T1, MachineSubTier.MEDIUM, 2);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier != null) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount != 0) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}