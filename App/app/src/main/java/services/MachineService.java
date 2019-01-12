package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enums.gpus.GpuState;
import model.enums.gpus.GpuTier;
import model.enums.machines.MachineState;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.InvalidArgumentValError;
import model.errors.MachineError;
import model.errors.SystemBusyError;
import model.gpus.GPU;
import model.machines.Machine;
import repositories.MachineRepository;

/**
 * Service
 */
public class MachineService {

    private MachineRepository machineRepository;
    private GPUService gpuService;

    public MachineService() {
        machineRepository = MachineRepository.getInstance();
        gpuService = new GPUService();
    }

    public List<Machine> getMachines() {
        return machineRepository.getCurrentMachines();
    }


	public Machine createMachine(MachineTier tier, MachineSubTier subTier, GpuTier gpuTier, int gpuCount)  throws MachineError {
        List<Machine> availableMachines = machineRepository.getFreeMachines();
        availableMachines = machineRepository.getByTier(availableMachines, tier);
        if (availableMachines.size() == 0) 
            throw new InvalidArgumentValError("MachineTier", tier.toString()); 

        availableMachines = machineRepository.getBySubTier(availableMachines, subTier);
        if (availableMachines.size() == 0) 
            throw new InvalidArgumentValError("MachineSubTier", subTier.toString()); 

        Machine machine = availableMachines.get(0);
        machine.validateGpuTier(gpuTier, gpuCount);

        int countTerminated = machineRepository.numberofSpecificState(MachineState.TERMINATED);
        if (countTerminated > 20) throw new SystemBusyError();

        machine.setFree(false);
        machine.setState(MachineState.STOPPED);

        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(5) + 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        machineRepository.addNewMachine(machine);

        for (int i = 0 ; i < gpuCount; i++) {
            this.attachGPU(machine.getId(), gpuTier);
        }
        return machine;
    }
    
    public Machine startMachine(int id) throws MachineError {
        Machine machine = machineRepository.getById(id); //invalid request error
        machine.checkState(MachineState.STOPPED,"startMachine"); //MachineStateRejectionError
        machine.checkGPUsState(GpuState.STOPPED); //InvalidRequestError
        machine.setState(MachineState.STARTING); //InvalidRequestError

        for (GPU g: machine.getGPUs()) {
            gpuService.startGPU(g.getId(), machine.getId());
        }

        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(5) + 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        machine.setState(MachineState.RUNNING);
        return machine;
    }


    public Machine stopMachine(int id) throws MachineError {
        Machine machine = machineRepository.getById(id); //invalid request error
        machine.checkState(MachineState.RUNNING,"stopMachine"); //MachineStateRejectionError
        machine.checkGPUsState(GpuState.RUNNING);

        machine.setState(MachineState.STOPPING);

        for (GPU g: machine.getGPUs()) {
            gpuService.stopGPU(g.getId(), machine.getId());
        }

        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(5) + 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        machine.setState(MachineState.STOPPED);
        return machine;
    }


    public Machine attachGPU(int machineId, GpuTier gpuTier) throws MachineError {
        Machine machine = machineRepository.getById(machineId); //invalid request error
        machine.checkGPUsTier(gpuTier);  //InvalidRequestError
        machine.checkForFreeSpace(gpuTier); //GpuLimitExceededError
    
        ArrayList<MachineState> possibleStates = new ArrayList<MachineState>();  
        possibleStates.add(MachineState.RUNNING); 
        possibleStates.add(MachineState.STOPPED); 
    
        machine.checkStates(possibleStates,"attachGPU"); //MachineStateRejectionError
        boolean wasRunning = false;
        if (machine.getState() == MachineState.RUNNING) {
            this.stopMachine(machine.getId());
            wasRunning = true;
        }

        machine.checkAllGPUsState(GpuState.STOPPED);

        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(5) + 10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GPU g = new GPU(gpuTier, machine);
        machine.attachGPU(g);
        if (wasRunning) {
            this.startMachine(machine.getId());
        }
        return machine;
    }


    public Machine detachGPU(int machineId) throws MachineError {
        Machine machine = machineRepository.getById(machineId); //invalid request error
        machine.haveGPUs();  //InvalidRequestError
        ArrayList<MachineState> possibleStates = new ArrayList<MachineState>();  
        possibleStates.add(MachineState.RUNNING); 
        possibleStates.add(MachineState.STOPPED); 
    
        machine.checkStates(possibleStates,"detachGPU"); //MachineStateRejectionError
        boolean wasRunning = false;
        if (machine.getState() == MachineState.RUNNING) {
            this.stopMachine(machine.getId());
            wasRunning = true;
        }

        machine.checkAllGPUsState(GpuState.STOPPED);

        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(5) + 10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        machine.detachGPU();

        if (wasRunning) {
            this.startMachine(machine.getId());
        }
        return machine;
    }


    public Machine restartMachine(int machineId) throws MachineError {
        Machine machine = machineRepository.getById(machineId); //invalid request error
        machine.checkState(MachineState.RUNNING, "restartMachine"); //MachineStateRejectionError
        this.stopMachine(machineId);
        this.startMachine(machineId);
        return machine;
    }   

    
}