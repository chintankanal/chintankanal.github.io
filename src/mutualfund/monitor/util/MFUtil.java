/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import mutualfund.monitor.model.Field;
import mutualfund.monitor.model.FieldMonitor;
import mutualfund.monitor.model.FieldValue;
import mutualfund.monitor.model.MutualFundPerformance;
import mutualfund.monitor.model.Option;
import mutualfund.monitor.model.Plan;
import mutualfund.monitor.model.ReturnMeasure;
import mutualfund.monitor.model.RiskMeasure;
import mutualfund.monitor.model.Scheme;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Chintan Kanal
 */
public class MFUtil {
    
    //numeric pattern to identify numbers including negative numbers and scientific notation
    private final static Pattern numeric = Pattern.compile( "^(-?0|-?[1-9]\\d*)(\\.\\d+)?(E\\d+)?$" );
    
    public static boolean isNumber(String value) {
        return StringUtils.isNotEmpty(value) && numeric.matcher(value).matches();
    }
    
    public static Map<String, List<FieldValue>> readCsv(String filepath, Collection<Field> fieldsToRead, String keyFieldName) throws IOException {
        Map<String, List<FieldValue>> retval = new HashMap<String, List<FieldValue>>();
        CsvReader csvReader = new CsvReader(filepath, ',');
        csvReader.readHeaders();
        while(csvReader.readRecord()) {
            String key = csvReader.get(keyFieldName);
            List<FieldValue> fieldValues = new ArrayList<FieldValue>();
            for(Field field : fieldsToRead) {
                String value = csvReader.get(field.getDisplayName());
                fieldValues.add(new FieldValue(field, value));
            }
            retval.put(key, fieldValues);
        }
        csvReader.close();
        return retval;
    }
    
    public static void writeCsv(String filepath, Map<Scheme, List<FieldValue>> fieldValues, List<Field> headers, char separator, boolean append) throws IOException {
        CsvReader csvReader = new CsvReader(filepath, separator);
        CsvWriter csvWriter = new CsvWriter(filepath, separator, Charset.defaultCharset());
        csvWriter.setAppend(append);
        csvReader.readHeaders();
        if(csvReader.getHeaders() == null || csvReader.getHeaders().length == 0) {
            for(Field header : headers) {
                csvWriter.write(header.getDisplayName());
            }
            csvWriter.endRecord();
        }
        csvReader.close();
        for(Map.Entry<Scheme, List<FieldValue>> fieldValueEntry : fieldValues.entrySet()) {
            List<FieldValue> writeValues = fieldValueEntry.getValue();
            for(FieldValue fieldValue : writeValues) {
                csvWriter.write(fieldValue.getValue());
            }
            csvWriter.endRecord();
        }
        csvWriter.close();
    }
    
    public static Scheme getDummyScheme() {
        return new Scheme("dummy", null, null, 0, Plan.Growth, Option.Direct, null, null, null, null, null);
    }
    
    public static MutualFundPerformance getDummyMutualFundPerformance() {
        return new MutualFundPerformance(getDummyScheme(), new ArrayList<RiskMeasure>(), new ArrayList<ReturnMeasure>());
    }
    
    public static String getSchemeCodeForBenchmark(String schemeCode) {
        return "Benchmark-" + schemeCode;
    }
    
    public static String getSchemeCodeForCategory(String schemeCode) {
        return "Category-" + schemeCode;
    }
    
    public static List<FieldValue> getFieldValue(List<FieldMonitor> fieldMonitors) {
        List<FieldValue> retval = new ArrayList<FieldValue>();
        for(FieldMonitor fieldMonitor : fieldMonitors) {
            retval.add(new FieldValue(fieldMonitor.getField(), fieldMonitor.getNewValue()));
        }
        return retval;
    }
    
    public static List<Field> getDisplayabeFields() {
        List<Field> retval = new ArrayList<Field>();
        Field[] fields = Field.values();
        for(int i=0; i<fields.length; i++) {
            retval.add(fields[i]);
        }
        return retval;
    }
    
    
}
