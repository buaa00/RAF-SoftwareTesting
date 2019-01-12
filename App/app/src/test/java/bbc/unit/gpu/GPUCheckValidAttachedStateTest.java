package bbc.unit.gpu;

import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
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
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;


/**
 * GpuConstructorTest
 */
@RunWith(Parameterized.class)
public class GPUCheckValidAttachedStateTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        new GPU(GpuTier.SINGLE, new G1LargeSingleMachine() ), GpuAttachedState.ATTACHED, GpuAttachedState.ATTACHED
                },
                {
                        new GPU(GpuTier.DOUBLE, new G2LargeDoubleMachine() ), GpuAttachedState.DETACHED, GpuAttachedState.DETACHED
                }

        });
    }


    private GPU gpu;
    private GpuAttachedState state;
    private GpuAttachedState expectedState;

    public GPUCheckValidAttachedStateTest(GPU gpu, GpuAttachedState state, GpuAttachedState expectedState) {
        this.gpu = gpu;
        this.state = state;
        this.expectedState = expectedState;
    }

    @Test
    public void checkValidAttachedState() {
        gpu.setAttachedState(state);
        try{
            gpu.checkAttached(expectedState);
        }
        catch ( MachineError error) {
            fail(error.getMessage());
        }
    }


}