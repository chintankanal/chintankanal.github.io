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
public class BenchmarkReturnMeasureCreator implements ResponseProcessor<ReturnMeasure> {

    public Map<ResponseObjectType, List<ReturnMeasure>> process(String schemeCode, Document response) {
        Map<ResponseObjectType, List<ReturnMeasure>> retval = new HashMap<ResponseObjectType, List<ReturnMeasure>>();
        List<ReturnMeasure> returnMeasures = new ArrayList<ReturnMeasure>();
        schemeCode = MFUtil.getSchemeCodeForBenchmark(schemeCode);
        String returnsNote = response.select(".margin_top15px:nth-child(15) .footnote").text();
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.YTD, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(2)"), ReturnType.Trailing, returnsNote, Field.BenchmarkYtdTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Day, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(3)"), ReturnType.Trailing, returnsNote, Field.BenchmarkOneDayTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Week, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(4)"), ReturnType.Trailing, returnsNote, Field.BenchmarkOneWeekTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Month, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(5)"), ReturnType.Trailing, returnsNote, Field.BenchmarkOneMonthTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 3, DurationType.Month, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(6)"), ReturnType.Trailing, returnsNote, Field.BenchmarkThreeMonthTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 6, DurationType.Month, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(7)"), ReturnType.Trailing, returnsNote, Field.BenchmarkSixMonthTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 1, DurationType.Year, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(8)"), ReturnType.Trailing, returnsNote, Field.BenchmarkOneYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 3, DurationType.Year, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(9)"), ReturnType.Trailing, returnsNote, Field.BenchmarkThreeYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 5, DurationType.Year, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(10)"), ReturnType.Trailing, returnsNote, Field.BenchmarkFiveYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 7, DurationType.Year, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(11)"), ReturnType.Trailing, returnsNote, Field.BenchmarkSevenYearTrailingReturn, null, null, null));
        returnMeasures.add(new ReturnMeasure(schemeCode, 10, DurationType.Year, getReturns(response, ".margin_top15px:nth-child(16) tr:nth-child(3) .align_right:nth-child(12)"), ReturnType.Trailing, returnsNote, Field.BenchmarkTenYearTrailingReturn, null, null, null));
        retval.put(ResponseObjectType.BenchmarkReturnMeasure, returnMeasures);
        return retval;
    }
    
    private float getReturns(Document doc, String pattern) {
        float retval = 0;
        Elements elements = doc.select(pattern);
        retval = MFUtil.isNumber(elements.text()) ? Float.valueOf(elements.text()) : 0;
        return retval;
    }

}
