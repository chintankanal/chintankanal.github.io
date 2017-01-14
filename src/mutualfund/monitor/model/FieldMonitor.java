/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class FieldMonitor {
    private Field field;
    private String prevValue;
    private String newValue;
    private Float prevBest;
    private Float prevWorst;
    private String prevBestTime;
    private String prevWorstTime;

    public FieldMonitor(Field field, String prevValue, String newValue, Float prevBest, Float prevWorst, String prevBestTime, String prevWorstTime) {
        this.field = field;
        this.prevValue = prevValue;
        this.newValue = newValue;
        this.prevBest = prevBest;
        this.prevWorst = prevWorst;
        this.prevBestTime = prevBestTime;
        this.prevWorstTime = prevWorstTime;
    }

    public Field getField() {
        return field;
    }

    public String getNewValue() {
        return newValue;
    }

    public Float getPrevBest() {
        return prevBest;
    }

    public String getPrevBestTime() {
        return prevBestTime;
    }

    public String getPrevValue() {
        return prevValue;
    }

    public Float getPrevWorst() {
        return prevWorst;
    }

    public String getPrevWorstTime() {
        return prevWorstTime;
    }

}
