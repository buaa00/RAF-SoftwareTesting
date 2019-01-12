package bbc.unit.machine;

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

public class MachineHaveGPUsValidTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {
                        new G1LargeSingleMachine(), GpuTier.SINGLE
                },
                {
                        new G2LargeDoubleMachine(), GpuTier.DOUBLE
                },
                {
                        new G2LargeClusterMachine(), GpuTier.CLUSTER
                },
        });
    }

    private Machine machine;
    private GpuTier gpuTier;

    public MachineHaveGPUsValidTest(Machine machine, GpuTier gpuTier) {
        this.machine = machine;
        this.gpuTier = gpuTier;
        this.machine.attachGPU(new GPU(gpuTier,this.machine));
    }

    @Test
    public void haveGPUsValidTest() {
        try{
            machine.haveGPUs();
        }
        catch (MachineError e) {
            fail(e.getMessage());
        }
    }
}
