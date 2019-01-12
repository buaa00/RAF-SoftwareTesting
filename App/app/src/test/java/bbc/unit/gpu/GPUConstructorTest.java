package bbc.unit.gpu;

import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
import model.gpus.GPU;
import model.machines.G1LargeSingleMachine;
import model.machines.G2LargeClusterMachine;
import model.machines.G2LargeDoubleMachine;
import model.machines.Machine;
import model.enums.gpus.GpuTier;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


/**
 * GpuConstructorTest
 */
@RunWith(Parameterized.class)
public class GPUConstructorTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        GpuTier.SINGLE, new G1LargeSingleMachine()
                },
                {
                        GpuTier.DOUBLE, new G1LargeSingleMachine()
                },
                {
                        GpuTier.DOUBLE, new G2LargeDoubleMachine()
                },
                {
                        GpuTier.CLUSTER, new G2LargeClusterMachine()
                },
        });
    }

    private GpuTier gpuTier;
    private Machine attachedTo;

    public GPUConstructorTest(GpuTier gpuTier, Machine attachedTo) {
        this.gpuTier = gpuTier;
        this.attachedTo = attachedTo;
    }

    @Test
    public void constructorTest() {
        GPU g = new GPU(gpuTier,attachedTo);
        Assert.assertEquals(gpuTier,g.getTier());
        Assert.assertEquals(attachedTo,g.getAttachedTo());
        Assert.assertEquals(g.getAttachedState(), GpuAttachedState.ATTACHED);
        Assert.assertEquals(g.getState(), GpuState.STOPPED);
    }


}