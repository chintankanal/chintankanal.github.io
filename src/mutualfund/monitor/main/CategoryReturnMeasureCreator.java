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
import mutualfund.monitor.util.MFUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Chintan Kanal
 */
public class CategoryReturnMeasureCreator implements ResponseProcessor<ReturnMeasure> {

    public Map<ResponseObjectType, List<ReturnMeasure>> process(String schemeCode, Document response) {
        Map<ResponseObjectType, List<ReturnMeasure>> retval = new HashMap<ResponseObjectType, List<ReturnMeasure>>();
        List<ReturnMeasure> returnMeasures = new ArrayList<ReturnMeasure>();
        schemeCode = MFUtil.getSchemeCodeForCategory(schemeCode);
        int nthChildIndex = getNthChildIndex(response, "Trailing Returns");
        int tableRowNthChildIndex = getTableRowNthChildIndex(response, nthChildIndex, "Category");
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
        String returnsNote = response.select(".margin_top15px:nth-child("+nthChildIndex+") .footnote").text();
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.YTD, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+ytdNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryYtdTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Day, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+oneDayNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryOneDayTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Week, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+oneWeekNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryOneWeekTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Month, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+oneMonthNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryOneMonthTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 3, DurationType.Month, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+threeMonthNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryThreeMonthTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 6, DurationType.Month, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+sixMonthNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategorySixMonthTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Year, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+oneYearNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryOneYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 3, DurationType.Year, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+threeYearNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryThreeYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 5, DurationType.Year, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+fiveYearNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryFiveYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 7, DurationType.Year, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+sevenYearNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategorySevenYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 10, DurationType.Year, getReturns(response, ".margin_top15px:nth-child("+nthChildIndex+") tr:nth-child("+tableRowNthChildIndex+") .align_right:nth-child("+tenYearNthChildIndex+")"), ReturnType.Trailing, returnsNote, Field.CategoryTenYearTrailingReturn, null, null, null));
        retval.put(ResponseObjectType.CategoryReturnMeasure, returnMeasures);
        return retval;
    }
    
    private float getReturns(Document doc, String pattern) {
        float retval = 0;
        Elements elements = doc.select(pattern);
        retval = MFUtil.isNumber(elements.text()) ? Float.valueOf(elements.text()) : 0;
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
