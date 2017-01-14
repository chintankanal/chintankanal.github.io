/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mutualfund.monitor.model.DurationType;
import mutualfund.monitor.model.Field;
import mutualfund.monitor.model.ResponseObjectType;
import mutualfund.monitor.model.ReturnMeasure;
import mutualfund.monitor.model.ReturnType;
import mutualfund.monitor.model.SchemeReturnMeasure;
import mutualfund.monitor.util.MFUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Chintan Kanal
 */
public class SchemeReturnMeasureCreator implements ResponseProcessor<ReturnMeasure> {

    public Map<ResponseObjectType, List<ReturnMeasure>> process(String schemeCode, Document response) {
        Map<ResponseObjectType, List<ReturnMeasure>> retval = new HashMap<ResponseObjectType, List<ReturnMeasure>>();
        List<ReturnMeasure> returnMeasures = new ArrayList<ReturnMeasure>();
        int nthChildIndex = getNthChildIndex(response, "Trailing Returns");
        int trIndexOfFund = getTableRowNthChildIndex(response, nthChildIndex, "Fund");
        int trIndexOfRankWithinCategory = getTableRowNthChildIndex(response, nthChildIndex, "Rank within Category");
        int trIndexOfNumberOfFunds = getTableRowNthChildIndex(response, nthChildIndex, "Number of funds in category");
        int ytdNthChildIndex = getThNthChildIndex(response, nthChildIndex, "YTD");
        int oneDayNthChildIndex = getThNthChildIndex(response, nthChildIndex, "1-Day");
        int oneWeekNthChildIndex = getThNthChildIndex(response, nthChildIndex, "1-W");
        int oneMonthNthChildIndex = getThNthChildIndex(response, nthChildIndex, "1-M");
        int threeMonthNthChildIndex = getThNthChildIndex(response, nthChildIndex, "3-M");
        int sixMonthNthChildIndex = getThNthChildIndex(response, nthChildIndex, "6-M");
        int oneYearNthChildIndex = getThNthChildIndex(response, nthChildIndex, "1-Y");
        int threeYearNthChildIndex = getThNthChildIndex(response, nthChildIndex, "3-Y");
        int fiveYearNthChildIndex = getThNthChildIndex(response, nthChildIndex, "5-Y");
        int sevenYearNthChildIndex = getThNthChildIndex(response, nthChildIndex, "7-Y");
        int tenYearNthChildIndex = getThNthChildIndex(response, nthChildIndex, "10-Y");
        float ytdReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+ytdNthChildIndex+")");
        float oneDayReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+oneDayNthChildIndex+")");
        float oneWeekReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+oneWeekNthChildIndex+")");
        float oneMonthReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+oneMonthNthChildIndex+")");
        float threeMonthsReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+threeMonthNthChildIndex+")");
        float sixMonthsReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+sixMonthNthChildIndex+")");
        float oneYearReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+oneYearNthChildIndex+")");
        float threeYearReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+threeYearNthChildIndex+")");
        float fiveYearReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+fiveYearNthChildIndex+")");
        float sevenYearReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+sevenYearNthChildIndex+")");
        float tenYearReturns = getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfFund+") .align_right:nth-child("+tenYearNthChildIndex+")");
        String returnsNote = response.select(".margin_top15px:nth-child("+nthChildIndex+") .footnote").text();
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 1, DurationType.YTD, ytdReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+ytdNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+ytdNthChildIndex+")"), Field.YtdTrailingReturn, Field.YtdTrailingReturnRank, Field.NumberOfFundsWithYtdTrailingReturn, Field.ReturnsNote));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 1, DurationType.Day, oneDayReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+oneDayNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+oneDayNthChildIndex+")"), Field.OneDayTrailingReturn, Field.OneDayTrailingReturnRank, Field.NumberOfFundsWithOneDayTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 1, DurationType.Week, oneWeekReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+oneWeekNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+oneWeekNthChildIndex+")"), Field.OneWeekTrailingReturn, Field.OneWeekTrailingReturnRank, Field.NumberOfFundsWithOneWeekTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 1, DurationType.Month, oneMonthReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+oneMonthNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+oneMonthNthChildIndex+")"), Field.OneMonthTrailingReturn, Field.OneMonthTrailingReturnRank, Field.NumberOfFundsWithOneMonthTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 3, DurationType.Month, threeMonthsReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+threeMonthNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+threeMonthNthChildIndex+")"), Field.ThreeMonthTrailingReturn, Field.ThreeMonthTrailingReturnRank, Field.NumberOfFundsWithThreeMonthTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 6, DurationType.Month, sixMonthsReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+sixMonthNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+sixMonthNthChildIndex+")"), Field.SixMonthTrailingReturn, Field.SixMonthTrailingReturnRank, Field.NumberOfFundsWithSixMonthTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 1, DurationType.Year, oneYearReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+oneYearNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+oneYearNthChildIndex+")"), Field.OneYearTrailingReturn, Field.OneYearTrailingReturnRank, Field.NumberOfFundsWithOneYearTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 3, DurationType.Year, threeYearReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+threeYearNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+threeYearNthChildIndex+")"), Field.ThreeYearTrailingReturn, Field.ThreeYearTrailingReturnRank, Field.NumberOfFundsWithThreeYearTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 5, DurationType.Year, fiveYearReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+fiveYearNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+fiveYearNthChildIndex+")"), Field.FiveYearTrailingReturn, Field.FiveYearTrailingReturnRank, Field.NumberOfFundsWithFiveYearTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 7, DurationType.Year, sevenYearReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+sevenYearNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+sevenYearNthChildIndex+")"), Field.SevenYearTrailingReturn, Field.SevenYearTrailingReturnRank, Field.NumberOfFundsWithSevenYearTrailingReturn, null));
        returnMeasures.add(new SchemeReturnMeasure(schemeCode, 10, DurationType.Year, tenYearReturns, ReturnType.Trailing, returnsNote, getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfRankWithinCategory+") .align_right:nth-child("+tenYearNthChildIndex+")"), getPatternValue(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+trIndexOfNumberOfFunds+") .align_right:nth-child("+tenYearNthChildIndex+")"), Field.TenYearTrailingReturn, Field.TenYearTrailingReturnRank, Field.NumberOfFundsWithTenYearTrailingReturn, null));
        retval.put(ResponseObjectType.SchemeReturnMeasure, returnMeasures);
        return retval;
    }
    
    private float getReturns(Document doc, String pattern) {
        float retval = 0;
        Elements elements = doc.select(pattern);
        retval = MFUtil.isNumber(elements.text()) ? Float.valueOf(elements.text()) : 0;
        return retval;
    }
    
    private int getPatternValue(Document doc, String pattern) {
        int retval = 0;
        Elements elements = doc.select(pattern);
        retval = MFUtil.isNumber(elements.text()) ? Integer.valueOf(elements.text()) : 0;
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
    
}
