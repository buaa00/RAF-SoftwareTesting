package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * T2MediumMachine
 */
public class T2MediumMachine extends Machine {

    public T2MediumMachine() {
        super(MachineTier.T2, MachineSubTier.MEDIUM, 3);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier != null) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount != 0) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}