/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

import java.util.List;

/**
 *
 * @author Chintan Kanal
 */
public class MutualFundPerformance {
    private Scheme scheme;
    private List<RiskMeasure> riskMeasures;
    private List<ReturnMeasure> returnMeasures;

    public MutualFundPerformance(Scheme scheme, List<RiskMeasure> riskMeasures, List<ReturnMeasure> returnMeasures) {
        this.scheme = scheme;
        this.riskMeasures = riskMeasures;
        this.returnMeasures = returnMeasures;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public List<RiskMeasure> getRiskMeasures() {
        return riskMeasures;
    }

    public List<ReturnMeasure> getReturnMeasures() {
        return returnMeasures;
    }
    
}
