/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class SchemeRiskMeasure extends RiskMeasure {
    
    private int rankWithinCategory;
    private int numberOfFundsWithinCategory;

    public SchemeRiskMeasure(String schemeCode, RiskMeasureType riskMeasureType, float riskMeasureValue, String riskNote, int rankWithinCategory, int numberOfFundsWithinCategory, Field field, Field rankField, Field numberOfFundsField, Field riskNoteField) {
        super(schemeCode, riskMeasureType, riskMeasureValue, riskNote, field, rankField, numberOfFundsField, riskNoteField);
        this.rankWithinCategory = rankWithinCategory;
        this.numberOfFundsWithinCategory = numberOfFundsWithinCategory;
    }

    public int getNumberOfFundsWithinCategory() {
        return numberOfFundsWithinCategory;
    }

    public int getRankWithinCategory() {
        return rankWithinCategory;
    }
    
    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder();
        retval.append("RiskMeasure[\nschemeCode: ").append(schemeCode).
                append(",\n:").append(riskMeasureType.name()).append(": ").append(riskMeasureValue).
                append(",\nRankWithinCategory: ").append(rankWithinCategory).
                append(",\nNumberOfFundsWithinCategory: ").append(numberOfFundsWithinCategory).
                append(",\nRiskNote: ").append(riskNote).
                append("\n]");
        return retval.toString();
    }

}
