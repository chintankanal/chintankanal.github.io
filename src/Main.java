

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mutualfund.monitor.model.Field;
import mutualfund.monitor.model.FieldValue;
import mutualfund.monitor.model.Scheme;
import mutualfund.monitor.util.CsvReader;
import mutualfund.monitor.util.CsvWriter;

/**
 *
 * @author Chintan Kanal
 */
public class Main {
    
    private static final String filepath = "D://mutualfunds/historicaldata.csv";
    
    public static void main(String[] args) {
//        try {
            
            List<FieldValue> fieldValues = new ArrayList<FieldValue>();
            fieldValues.add(new FieldValue(Field.SchemeAlphaRankWithinCategory, "10"));
            fieldValues.add(new FieldValue(Field.SchemeCode, "5"));
            System.out.println("before sort: " + fieldValues);
            Collections.sort(fieldValues);
            System.out.println("after sort: " + fieldValues);
            
//            List<Field> fields = new ArrayList<Field>();
//            fields.add(Field.SchemeCode);
//            fields.add(Field.SchemeName);
//            fields.add(Field.Mean);
//            fields.add(Field.StdDev);
//            
//            readCsv(filepath, fields, "SchemeCode");
////            Scheme scheme = new Scheme();
//            List<FieldValue> fieldValues = new ArrayList<FieldValue>();
//            fieldValues.add(new FieldValue(Field.SchemeCode, "55555"));
//            fieldValues.add(new FieldValue(Field.SchemeName, "SBIMF1"));
//            fieldValues.add(new FieldValue(Field.Mean, "15.2"));
//            fieldValues.add(new FieldValue(Field.StdDev, "10.2"));
//            Map<Scheme, List<FieldValue>> toWrite = new HashMap();
////            toWrite.put(scheme, fieldValues);
//            
//            writeCsv(filepath, toWrite, fields, ',', true);
            
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
    }
    
    public static Map<String, List<FieldValue>> readCsv(String filepath, Collection<Field> fieldsToRead, String keyFieldName) throws IOException {
        Map<String, List<FieldValue>> retval = new HashMap<String, List<FieldValue>>();
        CsvReader csvReader = new CsvReader(filepath, ',');
        csvReader.readHeaders();
        while(csvReader.readRecord()) {
            String key = csvReader.get(keyFieldName);
            key = csvReader.get(0);
            List<FieldValue> fieldValues = new ArrayList<FieldValue>();
            for(Field field : fieldsToRead) {
                String value = csvReader.get(field.getDisplayName());
                fieldValues.add(new FieldValue(field, value));
            }
            retval.put(key, fieldValues);
        }
        return retval;
    }
    
    public static void writeCsv(String filepath, Map<Scheme, List<FieldValue>> fieldValues, List<Field> headers, char separator, boolean append) throws IOException {
        CsvReader csvReader = new CsvReader(filepath, separator);
        CsvWriter csvWriter = new CsvWriter(filepath, separator, Charset.defaultCharset());
        csvWriter.setAppend(append);
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
}
