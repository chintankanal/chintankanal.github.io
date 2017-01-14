/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class SchemeReturnMeasure extends ReturnMeasure {
    
    int rankWithinCategory;
    int numberOfFundsInCategory;

    public SchemeReturnMeasure(String schemeCode, int duration, DurationType durationType, float returnPct, ReturnType returnType, String returnsNote, int rankWithinCategory, int numberOfFundsInCategory, Field field, Field rankField, Field numberOfFundsField, Field returnsNoteField) {
        super(schemeCode, duration, durationType, returnPct, returnType, returnsNote, field, rankField, numberOfFundsField, returnsNoteField);
        this.rankWithinCategory = rankWithinCategory;
        this.numberOfFundsInCategory = numberOfFundsInCategory;
    }

    public int getRankWithinCategory() {
        return rankWithinCategory;
    }

    public int getNumberOfFundsInCategory() {
        return numberOfFundsInCategory;
    }
    
    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder();
        retval.append("SchemeReturnMeasure[\nschemeCode: ").append(schemeCode).
                append(",\nDuration:").append(duration).append(" ").append(durationType.name()).
                append(",\nReturnPct: ").append(returnPct).append(" ").append(returnType.name()).
                append(" ").append(returnsNote).
                append("\nrankWithinCategory: ").append(rankWithinCategory).
                append("\nnumberOfFundsInCategory: ").append(numberOfFundsInCategory).
                append("\n]");
        return retval.toString();
    }
    
}
