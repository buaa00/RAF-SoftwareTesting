package bbc.unit.machine;

import model.enums.machines.MachineState;
import model.errors.MachineError;
import model.machines.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class MachineCheckInvalidStateTest {
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {
                        new SMicroMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },
                {
                        new SMicroMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new SMicroMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new SMicroMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new SMicroMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },

                {
                        new T1MediumMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },
                {
                        new T1MediumMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new T1MediumMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new T1MediumMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new T1MediumMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },

                {
                        new T1SmallMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },
                {
                        new T1SmallMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new T1SmallMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new T1SmallMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new T1SmallMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },

                {
                        new T2MediumMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },
                {
                        new T2MediumMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new T2MediumMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new T2MediumMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new T2MediumMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },
                {
                        new T2MediumMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },

                {
                        new T2LargeMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new T2LargeMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new T2LargeMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new T2LargeMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },
                {
                        new T2LargeMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },

                {
                        new G1LargeSingleMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new G1LargeSingleMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new G1LargeSingleMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new G1LargeSingleMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },
                {
                        new G1LargeSingleMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },

                {
                        new G2LargeDoubleMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },
                {
                        new G2LargeDoubleMachine(), MachineState.TERMINATED, MachineState.RUNNING,
                },

                {
                        new G2LargeClusterMachine(), MachineState.RUNNING, MachineState.STARTING,
                },
                {
                        new G2LargeClusterMachine(), MachineState.STARTING, MachineState.STOPPED,
                },
                {
                        new G2LargeClusterMachine(), MachineState.STOPPED, MachineState.STOPPING,
                },
                {
                        new G2LargeClusterMachine(), MachineState.STOPPING, MachineState.TERMINATED,
                },
                {
                        new G2LargeClusterMachine(), MachineState.TERMINATED, MachineState.STOPPING,
                },

        });

    }

    private Machine machine;
    private MachineState state;
    private MachineState expectedState;
    private String action;

    public MachineCheckInvalidStateTest(Machine machine, MachineState state, MachineState expectedState) {
        this.machine = machine;
        this.state = state;
        this.expectedState = expectedState;
        this.action = "action";
    }

    @Test
    public void checkValidStateTest() {
        machine.setState(state);
        try{
            machine.checkState(expectedState, action );
            fail("Expected MachineStateRejectionError");
        }
        catch ( MachineError e) {
            String expectedMessage = "Resource is in "+ machine.getState().toString() + "state and can't perform " + action;
            Assert.assertEquals(expectedMessage, e.getMessage());

        }
    }




}
