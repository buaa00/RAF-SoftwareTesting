package model.machines;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
import model.enums.gpus.GpuTier;
import model.enums.machines.MachineState;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.errors.GpuLimitExceededError;
import model.errors.InvalidRequestError;
import model.errors.MachineError;
import model.errors.MachineStateRejectionError;
import model.gpus.GPU;

/**
 * Machine
 */
public abstract class Machine {

    public static int incrementalId = 1;

    private int id;
    private MachineTier tier;
    private MachineSubTier subTier;
    private MachineState state;
    private int cpuCount;
    private ArrayList<GPU> GPUs;
    private LocalDateTime lastRan;
    private LocalDateTime terminated;
    private boolean isFree;

    public Machine(MachineTier tier, MachineSubTier subTier, int cpuCount) {
        this.id = incrementalId++;
        this.tier = tier;
        this.subTier = subTier;
        lastRan = LocalDateTime.now();
        terminated = LocalDateTime.now();
        state = MachineState.TERMINATED;
        GPUs = new ArrayList<>();
        isFree = true;
        this.cpuCount = cpuCount;
    }

    public abstract void validateGpuTier(GpuTier gpuTier, int gpuCount) throws MachineError;


    public void attachGPU(GPU g) {
        GPUs.add(g);
    }

    public GPU searchGPU(int id) throws MachineError {
        for (GPU g:GPUs) {
            if (g.getId() == id) return g;
        }
        throw new InvalidRequestError();
    }

    public void detachGPU() {
        int index = GPUs.size() - 1;
        GPU g = GPUs.get(index);
        GPUs.remove(g);
        g.setAttachedState(GpuAttachedState.DETACHED);
        g.setAttachedTo(null);
    }

    public void checkState(MachineState state, String action) throws MachineError {
        if (this.state != state) throw new MachineStateRejectionError(this.state.toString(), action);
    }
    
    public void haveGPUs() throws MachineError {
        if (this.GPUs.size() == 0) throw new InvalidRequestError();
    }

    public void checkGPUsTier(GpuTier gpuTier) throws MachineError {
        for (GPU g:GPUs) {
            if (g.getTier() != gpuTier) throw new InvalidRequestError();
        }
    }

    public void checkAllGPUsState(GpuState state)  {
        for (GPU g:GPUs) {
            if (g.getState() != state) System.exit(0);
        }
    }

    public void checkStates(List<MachineState> states, String action) throws MachineError {
        for (MachineState state: states) {
            if (state == this.state) return;
        }
        throw new MachineStateRejectionError(this.state.toString(), action);
    }

    public void checkForFreeSpace(GpuTier tier) throws MachineError {
        if (tier==GpuTier.SINGLE) {
            if (GPUs.size() >= 1) throw new GpuLimitExceededError(GPUs.size()+"", tier.toString());
        }
        if (tier==GpuTier.DOUBLE) {
            if (GPUs.size() >= 2) throw new GpuLimitExceededError(GPUs.size()+"", tier.toString());
        }
        if (tier==GpuTier.CLUSTER) {
            if (GPUs.size() >= 4) throw new GpuLimitExceededError(GPUs.size()+"", tier.toString());
        }
    }
 

    public void checkGPUsState(GpuState state) throws MachineError {
        for (GPU g:GPUs) {
            if (g.getState() != state) throw new InvalidRequestError();
        }
    }
 
    /**
     * @return the cpuCount
     */
    public int getCpuCount() {
        return cpuCount;
    }
    /**
     * @return the gPUs
     */
    public ArrayList<GPU> getGPUs() {
        return GPUs;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return the lastRan
     */
    public LocalDateTime getLastRan() {
        return lastRan;
    }
    /**
     * @return the state
     */
    public MachineState getState() {
        return state;
    }
    /**
     * @return the subTier
     */
    public MachineSubTier getSubTier() {
        return subTier;
    }
    /**
     * @return the terminated
     */
    public LocalDateTime getTerminated() {
        return terminated;
    }
    /**
     * @return the tier
     */
    public MachineTier getTier() {
        return tier;
    }
    /**
     * @return the isFree
     */
    public boolean isFree() {
        return isFree;
    }


    /**
     * @param cpuCount the cpuCount to set
     */
    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }
    /**
     * @param isFree the isFree to set
     */
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
    /**
     * @param subTier the subTier to set
     */
    public void setSubTier(MachineSubTier subTier) {
        this.subTier = subTier;
    }
    /**
     * @param terminated the terminated to set
     */
    public void setTerminated(LocalDateTime terminated) {
        this.terminated = terminated;
    }
    /**
     * @param tier the tier to set
     */
    public void setTier(MachineTier tier) {
        this.tier = tier;
    }
    /**
     * @param state the state to set
     */
    public void setState(MachineState state) {
        this.state = state;
    }
    /**
     * @param lastRan the lastRan to set
     */
    public void setLastRan(LocalDateTime lastRan) {
        this.lastRan = lastRan;
    }

    
}