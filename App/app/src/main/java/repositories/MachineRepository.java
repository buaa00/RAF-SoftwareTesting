package repositories;

import java.util.List;
import java.util.ArrayList;

import model.enums.machines.MachineState;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidRequestError;
import model.errors.MachineError;
import model.machines.G1LargeSingleMachine;
import model.machines.G2LargeClusterMachine;
import model.machines.G2LargeDoubleMachine;
import model.machines.Machine;
import model.machines.SMicroMachine;
import model.machines.T1MediumMachine;
import model.machines.T2MediumMachine;
import model.machines.T1SmallMachine;
import model.machines.T2LargeMachine;

/**
 * MachineRepository
 */
public class MachineRepository  {

    private static MachineRepository instance = null;
    private List<Machine> allMachines;
    private List<Machine> currentMachines;


    private MachineRepository() {
        this.allMachines = populateMachines();
        this.currentMachines = new ArrayList<>();
    }

    
    public static MachineRepository getInstance() {
        if (instance == null) instance = new MachineRepository();
        return instance;
    }

    public void addNewMachine(Machine m) {
        currentMachines.add(m);
    }

    public List<Machine> getFreeMachines() {
        List<Machine> toReturn = new ArrayList<>();
        for (Machine m:allMachines) {
            if (m.isFree()) toReturn.add(m);
        }
        return toReturn;
    }

    public int numberofSpecificState(MachineState state) {
        int count = 0;
        for (Machine m:currentMachines) {
            if (m.getState() == state) count++;
        }
        return count;
    }

    public Machine getById(int id) throws MachineError {
        for (Machine m:currentMachines) {
            if (m.getId() == id) return m;
        }
        throw new InvalidRequestError();
    }

    public Machine getByIdNullable(int id)  {
        for (Machine m:currentMachines) {
            if (m.getId() == id) return m;
        }
        return null;
    }


    public List<Machine> getByTier(List<Machine> filter, MachineTier machineTier) {
        List<Machine> toReturn = new ArrayList<>();
        for (Machine m:filter) {
            if (m.getTier() == machineTier) toReturn.add(m);
        }
        return toReturn;
    }

    public List<Machine> getBySubTier(List<Machine> filter, MachineSubTier machineSubTier) {
        List<Machine> toReturn =  new ArrayList<>();
        for (Machine m:filter) {
            if (m.getSubTier() == machineSubTier) toReturn.add(m);
        }
        return toReturn;
    }

    public List<Machine> getByCount(List<Machine> filter, int count) {
        List<Machine> toReturn =  new ArrayList<>();
        for (Machine m:filter) {
            if (m.getCpuCount() == count ) toReturn.add(m);
        }
        return toReturn;
    }




    /**
     * @return the allMachines
     */
    public List<Machine> getAllMachines() {
        return allMachines;
    }

    /**
     * @return the currentMachines
     */
    public List<Machine> getCurrentMachines() {
        return currentMachines;
    }


    private List<Machine> populateMachines() {
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            machines.add(new SMicroMachine());
            machines.add(new T1SmallMachine());
            machines.add(new T1MediumMachine());
            machines.add(new T2MediumMachine());
            machines.add(new T2LargeMachine());
            machines.add(new G1LargeSingleMachine());
            machines.add(new G2LargeDoubleMachine());
            machines.add(new G2LargeClusterMachine());
        }
        return machines;
    }
    
}