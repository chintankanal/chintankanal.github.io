/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mutualfund.monitor.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import mutualfund.monitor.model.FieldMonitor;
import mutualfund.monitor.model.FieldValue;
import mutualfund.monitor.model.MutualFundPerformance;
import mutualfund.monitor.model.ResponseObjectType;
import mutualfund.monitor.model.ReturnMeasure;
import mutualfund.monitor.model.RiskMeasure;
import mutualfund.monitor.model.Scheme;
import mutualfund.monitor.util.MFUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Chintan Kanal
 */
public class MutualFundMain {
    
    private List<ResponseProcessor> responseProcessors;
    private List<MutualFundPerformanceMonitor> mutualFundMonitors;
    private String file = "D://mutualfunds/historicaldata.csv";
    
    private static final String perfURL = "https://www.valueresearchonline.com/funds/fundperformance.asp?schemecode=";
    private static final String analysisURL = "https://www.valueresearchonline.com/funds/fundanalysis.asp?schemecode=";
    private static final String[] schemeCodes = {"16411", "15787", "16182", "16563", "16003", "16010", "16581", "17134", "15879", "16280", "17363", "16125", "16573", "16269", "15920", "16235", "16114", "15960", "16382", "16586", "16438", "15870", "16952", "16807", "16811", "15726", "16717", "16224", "15690", "16324", "16501"};
//    private static final String[] schemeCodes = {"16811", "15726", "16717", "16224", "15690", "16324", "16501"};
//    private static final String[] schemeCodes = {"16224"};
    
    private  MutualFundMain() {
        mutualFundMonitors = new ArrayList<MutualFundPerformanceMonitor>();
        mutualFundMonitors.add(new MutualFundSchemePerformanceMonitor());
        
        responseProcessors = new ArrayList<ResponseProcessor>();
        responseProcessors.add(new SchemeCreator());
        responseProcessors.add(new SchemeRiskMeasureCreator());
        responseProcessors.add(new BenchmarkRiskMeasureCreator());
        responseProcessors.add(new CategoryRiskMeasureCreator());
        responseProcessors.add(new SchemeReturnMeasureCreator());
        responseProcessors.add(new BenchmarkReturnMeasureCreator());
        responseProcessors.add(new CategoryReturnMeasureCreator());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MutualFundMain().testIt();
    }
    
    private void testIt() {
        try {
            for(String schemeCode : schemeCodes) {
                Document doc = getResponse(perfURL + schemeCode);
                Map<ResponseObjectType, List> allProcessedObjects = new HashMap<ResponseObjectType, List>();
                for(ResponseProcessor responseProcessor : responseProcessors) {
                    Map<ResponseObjectType, List> processedObjects = responseProcessor.process(schemeCode, doc);
                    List objects = null;
                    for(Map.Entry<ResponseObjectType, List> responseObjectEntry : processedObjects.entrySet()) {
                        objects = allProcessedObjects.get(responseObjectEntry.getKey());
                        if(objects == null) {
                            objects = new ArrayList();
                            allProcessedObjects.put(responseObjectEntry.getKey(), objects);
                        }
                        objects.addAll(responseObjectEntry.getValue());
                    }
                }
                doc = getResponse(analysisURL + schemeCode);
                Scheme scheme = (Scheme) allProcessedObjects.get(ResponseObjectType.Scheme).iterator().next();
                scheme.setSchemeManager(getMutualFundManager(doc));
                System.out.println(scheme);
                List<RiskMeasure> riskMeasures = new ArrayList<RiskMeasure>();
                List<ReturnMeasure> returnMeasures = new ArrayList<ReturnMeasure>();
                
                riskMeasures.addAll(allProcessedObjects.get(ResponseObjectType.SchemeRiskMeasure));
                riskMeasures.addAll(allProcessedObjects.get(ResponseObjectType.BenchmarkRiskMeasure));
                riskMeasures.addAll(allProcessedObjects.get(ResponseObjectType.CategoryRiskMeasure));
                
                returnMeasures.addAll(allProcessedObjects.get(ResponseObjectType.SchemeReturnMeasure));
                returnMeasures.addAll(allProcessedObjects.get(ResponseObjectType.BenchmarkReturnMeasure));
                returnMeasures.addAll(allProcessedObjects.get(ResponseObjectType.CategoryReturnMeasure));
                
                MutualFundPerformance schemePerformance = new MutualFundPerformance(scheme, riskMeasures, returnMeasures);
                Map<Scheme, List<FieldValue>> fieldValuesByScheme = new HashMap<Scheme, List<FieldValue>>();
                for(MutualFundPerformanceMonitor mfMonitor : mutualFundMonitors) {
                    List<FieldMonitor> fieldMonitor = mfMonitor.monitor(null, schemePerformance);
                    List<FieldValue> fieldValues = MFUtil.getFieldValue(fieldMonitor);
                    Collections.sort(fieldValues);
                    fieldValuesByScheme.put(scheme, fieldValues);
                }
                MFUtil.writeCsv(file, fieldValuesByScheme, MFUtil.getDisplayabeFields(), ',', true);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    private Document getResponse(String url) throws MalformedURLException, IOException {
        URL address = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) address.openConnection();
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
        con.setRequestProperty("upgrade-insecure-requests", "1");
        return Jsoup.parse(print_content(con));
    }
    
    private String getMutualFundManager(Document doc) {
        Elements elements = doc.select(".min_height_600px .sectionHead td > a");
        return elements.text();
    }
    
    private void print_https_cert(HttpsURLConnection con) {

        if (con != null) {

            try {

                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String print_content(HttpsURLConnection con) {
        StringBuilder retval = new StringBuilder();
        if (con != null) {
            try {
//                System.out.println("****** Content of the URL ********");
                BufferedReader br =
                        new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String input;
                while ((input = br.readLine()) != null) {
                    retval.append(input);
//                    System.out.println(input);
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return retval.toString();
    }
}
