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
public class CategoryRiskMeasureCreator implements ResponseProcessor<RiskMeasure> {

    public Map<ResponseObjectType, List<RiskMeasure>> process(String schemeCode, Document response) {
        Map<ResponseObjectType, List<RiskMeasure>> retval = new HashMap<ResponseObjectType, List<RiskMeasure>>();
        int nthChildIndex = getNthChildIndex(response, "Risk Measures");
        int tableRowNthChildIndex = getTableRowNthChildIndex(response, nthChildIndex, "Category");
        float mean = getMean(response, nthChildIndex, tableRowNthChildIndex);
        float stdDev = getStdDev(response, nthChildIndex, tableRowNthChildIndex);
        float sharpeRatio = getSharpeRatio(response, nthChildIndex, tableRowNthChildIndex);
        float sortinoRatio = getSortinoRatio(response, nthChildIndex, tableRowNthChildIndex);
        float beta = getBeta(response, nthChildIndex, tableRowNthChildIndex);
        float alpha = getAlpha(response, nthChildIndex, tableRowNthChildIndex);
        String riskNote = getRiskNote(response, nthChildIndex);
        List<RiskMeasure> riskMeasures = new ArrayList<RiskMeasure>();
        String categorySchemeCode = MFUtil.getSchemeCodeForCategory(schemeCode);
        riskMeasures.add(new RiskMeasure(categorySchemeCode, RiskMeasureType.Mean, mean, riskNote, Field.CategoryMean, null, null, null));
        riskMeasures.add(new RiskMeasure(categorySchemeCode, RiskMeasureType.StdDev, stdDev, riskNote, Field.CategoryStdDev, null, null, null));
        riskMeasures.add(new RiskMeasure(categorySchemeCode, RiskMeasureType.Sharpe, sharpeRatio, riskNote, Field.CategorySharpe, null, null, null));
        riskMeasures.add(new RiskMeasure(categorySchemeCode, RiskMeasureType.Sortino, sortinoRatio, riskNote, Field.CategorySortino, null, null, null));
        riskMeasures.add(new RiskMeasure(categorySchemeCode, RiskMeasureType.Beta, beta, riskNote, Field.CategoryBeta, null, null, null));
        riskMeasures.add(new RiskMeasure(categorySchemeCode, RiskMeasureType.Alpha, alpha, riskNote, Field.CategoryAlpha, null, null, null));
        retval.put(ResponseObjectType.CategoryRiskMeasure, riskMeasures);
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
