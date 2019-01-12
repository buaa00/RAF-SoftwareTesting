package model.machines;

import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * G2LargeClusterMachine
 */
public class G2LargeClusterMachine extends Machine {

    public G2LargeClusterMachine() {
        super(MachineTier.G2, MachineSubTier.LARGE, 8);
    }

    @Override
    public void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError {
        if (gpuTier == null) throw new InvalidArgumentValError("gpuTier", "null");
        if (gpuTier != GpuTier.CLUSTER) throw new InvalidArgumentValError("gpuTier", gpuTier.toString());
        if (gpuCount > 4) throw new InvalidArgumentValError("gpuCount", gpuCount + "");
	}
}