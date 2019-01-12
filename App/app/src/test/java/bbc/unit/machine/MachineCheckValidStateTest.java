package bbc.unit.machine;

import model.enums.gpus.GpuState;
import model.enums.gpus.GpuTier;
import model.enums.machines.MachineState;
import model.errors.MachineError;
import model.gpus.GPU;
import model.machines.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class MachineCheckValidStateTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {
                        new SMicroMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },
                {
                        new SMicroMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new SMicroMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new SMicroMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new SMicroMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },

                {
                        new T1MediumMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },
                {
                        new T1MediumMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new T1MediumMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new T1MediumMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new T1MediumMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },

                {
                        new T1SmallMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },
                {
                        new T1SmallMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new T1SmallMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new T1SmallMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new T1SmallMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },

                {
                        new T2MediumMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },
                {
                        new T2MediumMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new T2MediumMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new T2MediumMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new T2MediumMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },
                {
                        new T2MediumMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },

                {
                        new T2LargeMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new T2LargeMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new T2LargeMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new T2LargeMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },
                {
                        new T2LargeMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },

                {
                        new G1LargeSingleMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new G1LargeSingleMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new G1LargeSingleMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new G1LargeSingleMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },
                {
                        new G1LargeSingleMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },

                {
                        new G2LargeDoubleMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },

                {
                        new G2LargeClusterMachine(), MachineState.RUNNING, MachineState.RUNNING,
                },
                {
                        new G2LargeClusterMachine(), MachineState.STARTING, MachineState.STARTING,
                },
                {
                        new G2LargeClusterMachine(), MachineState.STOPPED, MachineState.STOPPED,
                },
                {
                        new G2LargeClusterMachine(), MachineState.STOPPING, MachineState.STOPPING,
                },
                {
                        new G2LargeClusterMachine(), MachineState.TERMINATED, MachineState.TERMINATED,
                },

        });
    }


    private Machine machine;
    private MachineState state;
    private MachineState expectedState;

    public MachineCheckValidStateTest(Machine machine, MachineState state, MachineState expectedState) {
        this.machine = machine;
        this.state = state;
        this.expectedState = expectedState;
    }

    @Test
    public void checkValidStateTest() {
        machine.setState(state);
        try{
            machine.checkState(expectedState, "" );
        }
        catch ( MachineError error) {
            fail(error.getMessage());
        }
    }

}
