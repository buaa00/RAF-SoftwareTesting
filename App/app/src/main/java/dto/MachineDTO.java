package dto;

import java.util.ArrayList;
import java.util.List;

import model.machines.Machine;

/**
 * MachineDTO
 */
public class MachineDTO {
    private int id;
    private String tierType;
    private String subTierType;
    private String stateType;
    private int cpuCount;
    private int gpuCount;
    private String lastRan;
    private String terminated;

    public MachineDTO(Machine machine) {
        id = machine.getId();
        tierType = machine.getTier().toString();
        subTierType = machine.getSubTier().toString();
        stateType = machine.getState().toString();
        cpuCount = machine.getCpuCount();
        gpuCount = machine.getGPUs().size();
        lastRan = machine.getLastRan().toString();
        terminated = machine.getTerminated().toString();
    }


    public static List<MachineDTO> convert(List<Machine> machines){
        ArrayList<MachineDTO> dtos = new ArrayList<>();
        for(Machine m : machines){
            dtos.add(new MachineDTO(m));
        }

        return dtos;
    }
}