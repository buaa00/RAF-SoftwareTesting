package bbc.unit.machine;

import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
import model.enums.machines.MachineState;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.gpus.GPU;
import model.machines.*;
import model.enums.gpus.GpuTier;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MachineConstructorTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        new SMicroMachine(), MachineTier.S, MachineSubTier.MICRO, 1, MachineState.TERMINATED, true
                },
                {
                        new T1SmallMachine(), MachineTier.T1, MachineSubTier.SMALL, 1, MachineState.TERMINATED, true
                },
                {
                        new T1MediumMachine(), MachineTier.T1, MachineSubTier.MEDIUM, 2, MachineState.TERMINATED, true
                },
                {
                        new T2MediumMachine(), MachineTier.T2, MachineSubTier.MEDIUM, 3, MachineState.TERMINATED, true
                },
                {
                        new T2LargeMachine(), MachineTier.T2, MachineSubTier.LARGE, 4, MachineState.TERMINATED, true
                },
                {
                        new G1LargeSingleMachine(), MachineTier.G1, MachineSubTier.LARGE, 4, MachineState.TERMINATED, true
                },
                {
                        new G2LargeDoubleMachine(), MachineTier.G2, MachineSubTier.LARGE, 4, MachineState.TERMINATED, true
                },
                {
                        new G2LargeClusterMachine(), MachineTier.G2, MachineSubTier.LARGE, 8, MachineState.TERMINATED, true
                },
        });
    }

    private Machine machine;
    private MachineTier tier;
    private MachineSubTier subTier;
    private MachineState state;
    private Integer cpuCount;
    private Boolean free;

    public MachineConstructorTest(Machine machine, MachineTier tier, MachineSubTier subTier, Integer cpuCount, MachineState state, Boolean free) {
        this.machine = machine;
        this.tier = tier;
        this.subTier = subTier;
        this.cpuCount = cpuCount;
        this.free = free;
        this.state = state;
    }

    @Test
    public void machineConstructorTest() {
        Assert.assertEquals(tier, machine.getTier());
        Assert.assertEquals(subTier, machine.getSubTier());
        Assert.assertEquals(state, machine.getState());
        Assert.assertEquals(cpuCount.intValue(), machine.getCpuCount());
        Assert.assertEquals(free.booleanValue(), machine.isFree());

    }
}
