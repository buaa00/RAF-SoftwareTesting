package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * SMicroMachine
 */
public class SMicroMachine extends Machine {

    public SMicroMachine() {
        super(MachineTier.S, MachineSubTier.MICRO, 1);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier != null) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount != 0) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}