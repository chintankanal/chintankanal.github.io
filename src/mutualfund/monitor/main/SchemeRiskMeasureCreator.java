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
import mutualfund.monitor.model.SchemeRiskMeasure;
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
public class SchemeRiskMeasureCreator implements ResponseProcessor<RiskMeasure> {
    
    public Map<ResponseObjectType, List<RiskMeasure>> process(String schemeCode, Document response) {
        Map<ResponseObjectType, List<RiskMeasure>> retval = new HashMap<ResponseObjectType, List<RiskMeasure>>();
        int nthChildIndex = getNthChildIndex(response, "Risk Measures");
        int tableRowNthChildIndex = getTableRowNthChildIndex(response, nthChildIndex, "Fund");
        float mean = getMean(response, nthChildIndex, tableRowNthChildIndex);
        float stdDev = getStdDev(response, nthChildIndex, tableRowNthChildIndex);
        float sharpeRatio = getSharpeRatio(response, nthChildIndex, tableRowNthChildIndex);
        float sortinoRatio = getSortinoRatio(response, nthChildIndex, tableRowNthChildIndex);
        float beta = getBeta(response, nthChildIndex, tableRowNthChildIndex);
        float alpha = getAlpha(response, nthChildIndex, tableRowNthChildIndex);
        
        tableRowNthChildIndex = getTableRowNthChildIndex(response, nthChildIndex, "Rank within Category");
        int meanRankWithinCategory = getMeanRankWithinCategory(response, nthChildIndex, tableRowNthChildIndex);
        int stdDevRankWithinCategory = getStdDevRankWithinCategory(response, nthChildIndex, tableRowNthChildIndex);
        int sharpeRankWithinCategory = getSharpeRankWithinCategory(response, nthChildIndex, tableRowNthChildIndex);
        int sortinoRankWithinCategory = getSortinoRankWithinCategory(response, nthChildIndex, tableRowNthChildIndex);
        int alphaRankWithinCategory = getAlphaRankWithinCategory(response, nthChildIndex, tableRowNthChildIndex);
        int betaRankWithinCategory = getBetaRankWithinCategory(response, nthChildIndex, tableRowNthChildIndex);
        
        tableRowNthChildIndex = getTableRowNthChildIndex(response, nthChildIndex, "Number of funds in category");
        int numberOfFundsInCategory = getNumberOfFundInCategory(response, nthChildIndex, tableRowNthChildIndex);
        String riskNote = getRiskNote(response, nthChildIndex);
        List<RiskMeasure> riskMeasures = new ArrayList<RiskMeasure>();
        riskMeasures.add(new SchemeRiskMeasure(schemeCode, RiskMeasureType.Mean, mean, riskNote, meanRankWithinCategory, numberOfFundsInCategory, Field.Mean, Field.SchemeMeanRankWithinCategory, Field.NumberOfSchemesWithinCategory, Field.RiskNote));
        riskMeasures.add(new SchemeRiskMeasure(schemeCode, RiskMeasureType.StdDev, stdDev, riskNote, stdDevRankWithinCategory, numberOfFundsInCategory, Field.StdDev, Field.SchemeStdDevRankWithinCategory, null, null));
        riskMeasures.add(new SchemeRiskMeasure(schemeCode, RiskMeasureType.Sharpe, sharpeRatio, riskNote, sharpeRankWithinCategory, numberOfFundsInCategory, Field.Sharpe, Field.SchemeSharpeRankWithinCategory, null, null));
        riskMeasures.add(new SchemeRiskMeasure(schemeCode, RiskMeasureType.Sortino, sortinoRatio, riskNote, sortinoRankWithinCategory, numberOfFundsInCategory, Field.Sortino, Field.SchemeSortinoRankWithinCategory, null, null));
        riskMeasures.add(new SchemeRiskMeasure(schemeCode, RiskMeasureType.Beta, beta, riskNote, betaRankWithinCategory, numberOfFundsInCategory, Field.Beta, Field.SchemeBetaRankWithinCategory, null, null));
        riskMeasures.add(new SchemeRiskMeasure(schemeCode, RiskMeasureType.Alpha, alpha, riskNote, alphaRankWithinCategory, numberOfFundsInCategory, Field.Alpha, Field.SchemeAlphaRankWithinCategory, null, null));
        retval.put(ResponseObjectType.SchemeRiskMeasure, riskMeasures);
        return retval;
    }
    
    private float getMean(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        float retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Mean");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Float.valueOf(elements.text());
            }
        }
        return retval;
    }
    
    private float getStdDev(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        float retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Std Dev");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Float.valueOf(elements.text());
            }
        }
        return retval;
    }
    
    private float getSharpeRatio(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        float retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Sharpe");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Float.valueOf(elements.text());
            }
        }
        return retval;
    }
    
    private float getSortinoRatio(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        float retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Sortino");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Float.valueOf(elements.text());
            }
        }
        return retval;
    }
    
    private float getBeta(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        float retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Beta");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Float.valueOf(elements.text());
            }
        }
        return retval;
    }
    
    private float getAlpha(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        float retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Alpha");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Float.valueOf(elements.text());
            }
        }
        return retval;
    }
    
    private int getMeanRankWithinCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        getNthChildIndex(doc, "Risk Measures");
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Mean");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getStdDevRankWithinCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Std Dev");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getSharpeRankWithinCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Sharpe");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getSortinoRankWithinCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Sortino");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getBetaRankWithinCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Beta");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getAlphaRankWithinCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Alpha");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getNumberOfFundInCategory(Document doc, int nThChildIndex, int tableRowNthChildIndex) {
        int retval = 0;
        int thNthChildIndex = getThNthChildIndex(doc, nThChildIndex, "Mean");
        if(nThChildIndex != -1 && tableRowNthChildIndex != -1 && thNthChildIndex != -1) {
            Elements elements = doc.select(".margin_top15px:nth-child("+nThChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+thNthChildIndex+")");
            if(MFUtil.isNumber(elements.text())) {
                retval = Integer.parseInt(elements.text());
            }
        }
        return retval;
    }
    
    private int getNthChildIndex(Document doc, String toCheck) {
        int index = 0;
        Elements elements = doc.select(".margin_top15px:nth-child("+index+") span");
        while(!elements.text().startsWith(toCheck) && index != -1) {
            index++;
            if(index == 20) {
                index = -1;
            }
            else {
                elements = doc.select(".margin_top15px:nth-child("+index+") span");
            }
        }
        return index;
    }
    
    private int getTableRowNthChildIndex(Document doc, int nthChildIndex, String toCheck) {
        int index = 0;
        Elements elements = doc.select(".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+index+") td:nth-child(1)");
        while(!elements.text().equalsIgnoreCase(toCheck) && index != -1) {
            index++;
            if(index == 20) {
                index = -1;
            }
            else {
                elements = doc.select(".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+index+") td:nth-child(1)");
            }
        }
        return index;
    }
    
    private int getThNthChildIndex(Document doc, int nthChildIndex, String toCheck) {
        int index = 0;
        Elements elements = doc.select(".margin_top15px:nth-child("+nthChildIndex+") th:nth-child("+index+")");
        while(!elements.text().equalsIgnoreCase(toCheck) && index != -1) {
            index++;
            if(index == 20) {
                index = -1;
            }
            else {
                elements = doc.select(".margin_top15px:nth-child("+nthChildIndex+") th:nth-child("+index+")");
            }
        }
        return index;
    }
    
    private String getRiskNote(Document doc, int nthChildIndex) {
        String retval = "Unknown";
        Elements elements = doc.select(".margin_top15px:nth-child("+nthChildIndex+") .footnote");
        retval = elements.text();
        return retval;
    }

}
