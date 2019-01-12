package model.gpus;

import java.time.LocalDateTime;
import model.enums.gpus.GpuAttachedState;
import model.enums.gpus.GpuState;
import model.enums.gpus.GpuTier;
import model.errors.InvalidRequestError;
import model.errors.MachineError;
import model.machines.Machine;

/**
 * GPU
 */
public class GPU {

    private static int incrementalId = 1;
    private int id;
    private GpuTier tier;
    private GpuState state;
    private GpuAttachedState attachedState;
    private Machine attachedTo;
    private LocalDateTime lastRan;
    private LocalDateTime lastStopped;

    public GPU(GpuTier tier, Machine attachedTo) {
        this.id = incrementalId++;
        this.tier = tier;
        this.state = GpuState.STOPPED;
        this.attachedTo = attachedTo;
        this.attachedState = GpuAttachedState.ATTACHED;
        lastRan = LocalDateTime.now();
        lastStopped = LocalDateTime.now();
    }

    public void checkState(GpuState state) throws MachineError {
        if (this.state != state) throw new InvalidRequestError();
    }
    public void checkAttached(GpuAttachedState state) throws MachineError {
        if (this.attachedState != state) throw new InvalidRequestError();
    }

    /**
     * @return the attachedState
     */
    public GpuAttachedState getAttachedState() {
        return attachedState;
    }
    /**
     * @return the attachedTo
     */
    public Machine getAttachedTo() {
        return attachedTo;
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
     * @return the tier
     */
    public GpuTier getTier() {
        return tier;
    }
    /**
     * @return the lastStopped
     */
    public LocalDateTime getLastStopped() {
        return lastStopped;
    }
    /**
     * @return the state
     */
    public GpuState getState() {
        return state;
    }

    /**
     * @param attachedState the attachedState to set
     */
    public void setAttachedState(GpuAttachedState attachedState) {
        this.attachedState = attachedState;
    }
    /**
     * @param attachedTo the attachedTo to set
     */
    public void setAttachedTo(Machine attachedTo) {
        this.attachedTo = attachedTo;
    }
    /**
     * @param state the state to set
     */
    public void setState(GpuState state) {
        this.state = state;
    }
    /**
     * @param tier the tier to set
     */
    public void setTier(GpuTier tier) {
        this.tier = tier;
    }
}