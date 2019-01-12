package bbc.unit.machine;

import model.enums.gpus.GpuTier;
import model.errors.MachineError;
import model.gpus.GPU;
import model.machines.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.crypto.Mac;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)

public class MachineHaveGPUsInvalidTest {
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
                {
                        new SMicroMachine(), GpuTier.SINGLE
                },
                {
                        new T1SmallMachine(), GpuTier.SINGLE
                },
                {
                        new T1MediumMachine(), GpuTier.SINGLE
                },
                {
                        new T2MediumMachine(), GpuTier.SINGLE
                },
                {
                        new T2LargeMachine(), GpuTier.SINGLE
                },
        });
    }

    private Machine machine;
    private GpuTier gpuTier;

    public MachineHaveGPUsInvalidTest(Machine machine, GpuTier gpuTier) {
        this.machine = machine;
        this.gpuTier = gpuTier;
    }

    @Test
    public void haveGPUsValidTest() {
        try{
            machine.haveGPUs();
            fail("Expected InvalidRequestError");
        }
        catch (MachineError e) {
            Assert.assertEquals("Invalid request!", e.getMessage());
        }
    }
}
