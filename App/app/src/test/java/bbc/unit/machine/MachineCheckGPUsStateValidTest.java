package bbc.unit.machine;

import model.enums.gpus.GpuState;
import model.enums.gpus.GpuTier;
import model.errors.MachineError;
import model.gpus.GPU;
import model.machines.G1LargeSingleMachine;
import model.machines.G2LargeClusterMachine;
import model.machines.G2LargeDoubleMachine;
import model.machines.Machine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class MachineCheckGPUsStateValidTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {
                        new G1LargeSingleMachine(), GpuTier.SINGLE, GpuState.STOPPED, GpuState.STOPPED,   1
                },
                {
                        new G1LargeSingleMachine(), GpuTier.SINGLE, GpuState.RUNNING, GpuState.RUNNING,  1
                },
                {
                        new G2LargeDoubleMachine(), GpuTier.DOUBLE, GpuState.STOPPED,  GpuState.STOPPED, 2
                },
                {
                        new G2LargeDoubleMachine(), GpuTier.DOUBLE, GpuState.RUNNING,  GpuState.RUNNING, 2
                },
                {
                        new G2LargeClusterMachine(), GpuTier.CLUSTER, GpuState.STOPPED,  GpuState.STOPPED, 4
                },
                {
                        new G2LargeClusterMachine(), GpuTier.CLUSTER, GpuState.RUNNING,  GpuState.RUNNING, 4
                },
        });
    }

    private Machine machine;
    private GpuTier gpuTier;
    private GpuState gpuState;
    private GpuState testState;
    private Integer numberOfGPUs;

    public MachineCheckGPUsStateValidTest(Machine machine, GpuTier gpuTier, GpuState gpuState, GpuState testState, Integer numberOfGPUs) {
        this.machine = machine;
        for (int i = 0 ; i < numberOfGPUs.intValue(); i++) {
            GPU g = new GPU(gpuTier,this.machine);
            g.setState(gpuState);
            this.machine.attachGPU(g);
        }
        this.testState = testState;
    }

    @Test
    public void checkGPUsTierValid() {
        try {
            machine.checkGPUsState(testState);
        }
        catch (MachineError e) {
            fail(e.getMessage());
        }
    }
}
