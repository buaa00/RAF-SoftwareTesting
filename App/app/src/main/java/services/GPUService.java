package services;

import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
import model.errors.InvalidRequestError;
import model.errors.MachineError;
import model.gpus.GPU;
import model.machines.Machine;
import repositories.MachineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GPURepository
 */
public class GPUService {

    private MachineRepository machineRepository;
    
    public GPUService() {
        this.machineRepository = MachineRepository.getInstance();
    }


    public GPU startGPU(int id, int machineId) throws MachineError {
        Machine machine = machineRepository.getById(machineId); //invalid request error
        if (machine == null) throw new InvalidRequestError();
        GPU g = machine.searchGPU(id);   //invalid request error
        g.checkState(GpuState.STOPPED);  //invalid request error
        g.checkAttached(GpuAttachedState.ATTACHED); //invalid request error
        
        Random rand = new Random();

        try {
            Thread.sleep((rand.nextInt(5) + 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setState(GpuState.RUNNING);

        return g;
    } 

    public GPU stopGPU(int id, int machineId) throws MachineError {
        Machine machine = machineRepository.getById(machineId); //invalid request error
        if (machine == null) throw new InvalidRequestError();
        GPU g = machine.searchGPU(id);   //invalid request error
        g.checkState(GpuState.RUNNING);  //invalid request error
        g.checkAttached(GpuAttachedState.ATTACHED);  //invalid request error
        
        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(5) + 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setState(GpuState.STOPPED);
        return g;
    } 
    
    public List<GPU> searchGPUs(int machineId) {
        Machine machine = machineRepository.getByIdNullable(machineId);
        if (machine == null) return new ArrayList<GPU>();
        return machine.getGPUs();     
    }
}