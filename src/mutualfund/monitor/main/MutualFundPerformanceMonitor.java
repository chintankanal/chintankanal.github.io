/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.main;

import java.util.List;
import mutualfund.monitor.model.FieldMonitor;
import mutualfund.monitor.model.MutualFundPerformance;

/**
 *
 * @author Chintan Kanal
 */
public interface MutualFundPerformanceMonitor {
    public List<FieldMonitor> monitor(List<MutualFundPerformance> pastPerformances, MutualFundPerformance currentPerformance);
}
