package bbc.unit.machine;

import model.enums.gpus.GpuTier;
import model.errors.MachineError;
import model.gpus.GPU;
import model.machines.G1LargeSingleMachine;
import model.machines.G2LargeClusterMachine;
import model.machines.G2LargeDoubleMachine;
import model.machines.Machine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class MachineCheckGPUsTierInvalidTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {
                        new G1LargeSingleMachine(), GpuTier.SINGLE, GpuTier.DOUBLE, 1
                },
                {
                        new G2LargeDoubleMachine(), GpuTier.DOUBLE, GpuTier.CLUSTER, 2
                },
                {
                        new G2LargeClusterMachine(), GpuTier.CLUSTER,GpuTier.DOUBLE, 4
                },
        });
    }

    private Machine machine;
    private GpuTier gpuTier;
    private GpuTier testTier;
    private Integer numberOfGPUs;

    public MachineCheckGPUsTierInvalidTest(Machine machine, GpuTier gpuTier, GpuTier testTier, Integer numberOfGPUs) {
        this.machine = machine;
        for (int i = 0 ; i < numberOfGPUs.intValue(); i++) {
            this.machine.attachGPU(new GPU(gpuTier,this.machine));
        }
        this.testTier = testTier;
    }

    @Test
    public void checkGPUsTierValid() {
        try {
            machine.checkGPUsTier(testTier);
            fail("Expected InvalidRequestError");
        }
        catch (MachineError e) {
            Assert.assertEquals("Invalid request!", e.getMessage());
        }
    }
}
