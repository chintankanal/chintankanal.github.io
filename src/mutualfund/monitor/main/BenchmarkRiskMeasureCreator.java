/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mutualfund.monitor.model.Field;
import mutualfund.monitor.model.ResponseObjectType;
import mutualfund.monitor.model.RiskMeasure;
import mutualfund.monitor.model.RiskMeasureType;
import mutualfund.monitor.util.MFUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Chintan Kanal
 */
public class BenchmarkRiskMeasureCreator implements ResponseProcessor<RiskMeasure> {

    public Map<ResponseObjectType, List<RiskMeasure>> process(String schemeCode, Document response) {
        Map<ResponseObjectType, List<RiskMeasure>> retval = new HashMap<ResponseObjectType, List<RiskMeasure>>();
        float mean = getMean(response);
        float stdDev = getStdDev(response);
        float sharpeRatio = getSharpeRatio(response);
        float sortinoRatio = getSortinoRatio(response);
        float beta = getBeta(response);
        float alpha = getAlpha(response);
        String riskNote = getRiskNote(response);
        List<RiskMeasure> riskMeasures = new ArrayList<RiskMeasure>();
        String benchmarkSchemeCode = MFUtil.getSchemeCodeForBenchmark(schemeCode);
        riskMeasures.add(new RiskMeasure(benchmarkSchemeCode, RiskMeasureType.Mean, mean, riskNote, Field.BenchmarkMean, null, null, null));
        riskMeasures.add(new RiskMeasure(benchmarkSchemeCode, RiskMeasureType.StdDev, stdDev, riskNote, Field.BenchmarkStdDev, null, null, null));
        riskMeasures.add(new RiskMeasure(benchmarkSchemeCode, RiskMeasureType.Sharpe, sharpeRatio, riskNote, Field.BenchmarkSharpe, null, null, null));
        riskMeasures.add(new RiskMeasure(benchmarkSchemeCode, RiskMeasureType.Sortino, sortinoRatio, riskNote, Field.BenchmarkSortino, null, null, null));
        riskMeasures.add(new RiskMeasure(benchmarkSchemeCode, RiskMeasureType.Beta, beta, riskNote, Field.BenchmarkBeta, null, null, null));
        riskMeasures.add(new RiskMeasure(benchmarkSchemeCode, RiskMeasureType.Alpha, alpha, riskNote, Field.BenchmarkAlpha, null, null, null));
        retval.put(ResponseObjectType.BenchmarkRiskMeasure, riskMeasures);
        return retval;
    }
    
    private float getMean(Document doc) {
        float retval = 0;
        Elements element = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) .align_right:nth-child(2)");
        if(MFUtil.isNumber(element.text())) {
            retval = Float.valueOf(element.text());
        }
        return retval;
    }
    
    private float getStdDev(Document doc) {
        float retval = 0;
        Elements element = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) .align_right:nth-child(3)");
        if(MFUtil.isNumber(element.text())) {
            retval = Float.valueOf(element.text());
        }
        return retval;
    }
    
    private float getSharpeRatio(Document doc) {
        float retval = 0;
        Elements element = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) .align_right:nth-child(4)");
        if(MFUtil.isNumber(element.text())) {
            retval = Float.valueOf(element.text());
        }
        return retval;
    }
    
    private float getSortinoRatio(Document doc) {
        float retval = 0;
        Elements element = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) .align_right:nth-child(5)");
        if(MFUtil.isNumber(element.text())) {
            retval = Float.valueOf(element.text());
        }
        return retval;
    }
    
    private float getBeta(Document doc) {
        float retval = 0;
        Elements element = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) .align_right:nth-child(6)");
        if(MFUtil.isNumber(element.text())) {
            retval = Float.valueOf(element.text());
        }
        return retval;
    }
    
    private float getAlpha(Document doc) {
        float retval = 0;
        Elements element = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) .align_right:nth-child(7)");
        if(MFUtil.isNumber(element.text())) {
            retval = Float.valueOf(element.text());
        }
        return retval;
    }
    
    private String getRiskNote(Document doc) {
        String retval = "Unknown";
        Elements elements = doc.select(".margin_top15px:nth-child(15) .footnote");
        retval = elements.text();
        return retval;
    }

}
