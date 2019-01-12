package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * T2LargeMachine
 */
public class T2LargeMachine extends Machine {

    public T2LargeMachine() {
        super(MachineTier.T2, MachineSubTier.LARGE, 4);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier != null) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount != 0) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}