/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class RiskMeasure {
    String schemeCode;
    RiskMeasureType riskMeasureType;
    float riskMeasureValue;
    String riskNote;
    Field field;
    Field rankField;
    Field numberOfFundsField;
    Field riskNoteField;

    public RiskMeasure(String schemeCode, RiskMeasureType riskMeasureType, float riskMeasureValue, String riskNote, Field field, Field rankField, Field numberOfFundsField, Field riskNoteField) {
        this.schemeCode = schemeCode;
        this.riskMeasureType = riskMeasureType;
        this.riskMeasureValue = riskMeasureValue;
        this.riskNote = riskNote;
        this.field = field;
        this.rankField = rankField;
        this.numberOfFundsField = numberOfFundsField;
        this.riskNoteField = riskNoteField;
    }

    public RiskMeasureType getRiskMeasureType() {
        return riskMeasureType;
    }

    public float getRiskMeasureValue() {
        return riskMeasureValue;
    }

    public String getRiskNote() {
        return riskNote;
    }

    public String getSchemeCode() {
        return schemeCode;
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

    public Field getRiskNoteField() {
        return riskNoteField;
    }
    
    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder();
        retval.append("RiskMeasure[\nschemeCode: ").append(schemeCode).
                append(",\nRiskType: ").append(riskMeasureType.name()).append(": ").append(riskMeasureValue).
                append(",\nRiskNote: ").append(riskNote).
                append("\n]");
        return retval.toString();
    }
    
}
