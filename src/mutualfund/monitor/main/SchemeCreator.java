/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mutualfund.monitor.model.Option;
import mutualfund.monitor.model.Plan;
import mutualfund.monitor.model.ResponseObjectType;
import mutualfund.monitor.model.Scheme;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Chintan Kanal
 */
public class SchemeCreator implements ResponseProcessor<Scheme> {

    public Map<ResponseObjectType, List<Scheme>> process(String schemeCode, Document response) {
        String schemeName = getSchemeName(response);
        String subscriptionStatus = getSubscriptionStatus(response);
        Option option = Option.valueOf(getOption(response));
        Plan plan = Plan.valueOf(getPlan(response));
        float nav = getNav(response);
        String category = getCategory(response);
        String assets = getAssets(response);
        String expense = getExpense(response);
        String benchmarkIndex = getBenchmarkIndex(response);
        Map<ResponseObjectType, List<Scheme>> retval = new HashMap<ResponseObjectType, List<Scheme>>();
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        Scheme scheme = new Scheme(schemeCode, schemeName, subscriptionStatus, nav, plan, option, category, assets, expense, benchmarkIndex, currentDate);
//        System.out.println(scheme);
        List<Scheme> schemes = new ArrayList<Scheme>();
        schemes.add(scheme);
        retval.put(ResponseObjectType.Scheme, schemes);
        return retval;
    }
    
    private String getSchemeName(Document doc) {
        String retval = "Unknown";
        Elements elements = doc.select(".fundname_rating_sep");//<span class="fundname_rating_sep">Franklin India Prima Fund - Direct Plan</span>
        retval = elements.text();
        return retval.substring(0, retval.indexOf("-")).trim();
    }
    
    private String getOption(Document doc) {
        String retval = "Unknown";
        Elements elements = doc.select(".active , .fundname_rating_sep");//<span class="fundname_rating_sep">Franklin India Prima Fund - Direct Plan</span>
        retval = elements.text();
        int startIndex = retval.indexOf(" - ") + 3;
        return retval.substring(startIndex, retval.indexOf(" ", startIndex)).trim();
    }
    
    private String getSubscriptionStatus(Document doc) {
        String retval = "Unknown";
        Elements elements = doc.select(".padding_right_none");
        retval = elements.text();
        if(!retval.contains("Status:")) {
            try {
                String rawHtmlText = doc.html();
                int index = rawHtmlText.indexOf("Status:");
                if(index > 0) {
                    retval = rawHtmlText.substring(index, rawHtmlText.indexOf("&nbsp", index+1));
                }
            } catch(Exception ex) {
                System.out.println("Exception in getSubscriptionStatus() - could not parse raw html for subscription status: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        if(retval.contains("Status:")) {
            if(retval.indexOf("|") != -1) {
                retval = retval.substring(retval.indexOf("Status:") + 8, retval.indexOf("|"));
            }
            else {
                retval = retval.substring(retval.indexOf("Status:") + 8);
            }
        }
        return retval.trim();
    }
    
    private String getPlan(Document doc) {
        String retval = "Unknown";
        Elements elements = doc.select(".fund-type li:nth-child(1)");//<li>Growth: <span style="font-family:rupee; font-size:14px;padding:0;line-height:12px;">R</span> 50.318</li>
        retval = elements.text();
        return retval.substring(0, retval.indexOf(":")); //Growth:R 746.5011
    }
    
    private float getNav(Document doc) {
        Elements elements = doc.select(".fund-type li:nth-child(1)");//<li>Growth: <span style="font-family:rupee; font-size:14px;padding:0;line-height:12px;">R</span> 50.318</li>
        String value = elements.text();
        return Float.valueOf(value.substring(value.indexOf(":")+3));
    }
    
    private String getCategory(Document doc) {
        Elements elements = doc.select(".fund-detail a");
        String retval = elements.text();
        return retval;
    }
    
    private String getAssets(Document doc) {
        Elements elements = doc.select("tr:nth-child(2) .head+ td");//<td class="head">Assets:</td><td><span style="font-family:rupee; font-size:14px;padding:0;line-height:12px;">R</span>4,174crore (As on Nov 30, 2016)</td>
        String retval = elements.text();
        return retval.substring(retval.indexOf(" ") + 1);
    }
    
    private String getExpense(Document doc) {
        Elements elements = doc.select(".last-tr .head+ td");//<tr class="last-tr"><td class="head">Expense:</td><td>1.77% (As on Nov 30, 2016)</td></tr>
        String retval = elements.text();
        return retval;
    }
    
    private String getBenchmarkIndex(Document doc) {
        Elements elements = doc.select(".margin_top15px:nth-child(15) tr:nth-child(3) td:nth-child(1)");
        String retval = elements.text();
        return retval;
    }

}
