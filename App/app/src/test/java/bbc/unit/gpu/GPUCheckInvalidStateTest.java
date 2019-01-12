package bbc.unit.gpu;

import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
import model.enums.gpus.GpuTier;
import model.errors.MachineError;
import model.gpus.GPU;
import model.errors.InvalidRequestError;
import model.machines.G1LargeSingleMachine;
import model.machines.G2LargeClusterMachine;
import model.machines.G2LargeDoubleMachine;
import model.machines.Machine;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;


/**
 * GPUCheckInvalidStateTest
 */
@RunWith(Parameterized.class)
public class GPUCheckInvalidStateTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        new GPU(GpuTier.SINGLE, new G1LargeSingleMachine() ), GpuState.RUNNING, GpuState.STOPPED
                },
                {
                        new GPU(GpuTier.DOUBLE, new G2LargeDoubleMachine() ), GpuState.STOPPED, GpuState.RUNNING
                }

        });
    }


    private GPU gpu;
    private GpuState state;
    private GpuState expectedState;

    public GPUCheckInvalidStateTest(GPU gpu, GpuState state, GpuState expectedState) {
        this.gpu = gpu;
        this.state = state;
        this.expectedState = expectedState;
    }

    @Test
    public void checkInvalidStateTest() {
        gpu.setState(state);
        try{
            gpu.checkState(expectedState);
            fail("Expected InvalidRequestError");
        }
        catch (MachineError e) {
            Assert.assertEquals("Invalid request!", e.getMessage());
        }

    }


}