/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class ReturnMeasure {
    
    String schemeCode;
    int duration;
    DurationType durationType;
    float returnPct;
    ReturnType returnType;
    String returnsNote;
    Field field;
    Field rankField;
    Field numberOfFundsField;
    Field returnsNoteField;

    public ReturnMeasure(String schemeCode, int duration, DurationType durationType, float returnPct, ReturnType returnType, String returnsNote, Field field, Field rankField, Field numberOfFundsField, Field returnsNoteField) {
        this.schemeCode = schemeCode;
        this.duration = duration;
        this.durationType = durationType;
        this.returnPct = returnPct;
        this.returnType = returnType;
        this.returnsNote = returnsNote;
        this.field = field;
        this.rankField = rankField;
        this.numberOfFundsField = numberOfFundsField;
        this.returnsNoteField = returnsNoteField;
    }

    public String getSchemeCode() {
        return schemeCode;
    }
    
    public int getDuration() {
        return duration;
    }

    public DurationType getDurationType() {
        return durationType;
    }

    public float getReturnPct() {
        return returnPct;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public Field getField() {
        return field;
    }

    public Field getRankField() {
        return rankField;
    }

    public Field getNumberOfFundsField() {
        return numberOfFundsField;
    }

    public String getReturnsNote() {
        return returnsNote;
    }
    
    public Field getReturnsNoteField() {
        return returnsNoteField;
    }
    
    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder();
        retval.append("ReturnMeasure[\nschemeCode: ").append(schemeCode).
                append(",\nDuration:").append(duration).append(" ").append(durationType.name()).
                append(",\nReturnPct: ").append(returnPct).append(" ").append(returnType.name()).
                append(" ").append(returnsNote).
                append("\n]");
        return retval.toString();
    }

}
