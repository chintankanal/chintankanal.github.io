/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class Scheme {
    private String schemeCode;
    private String schemeName;
    private String subscriptionStatus;
    private float nav;
    private Plan plan;
    private Option option;
    private String category;
    private String assets;
    private String expense;
    private String benchmarkIndex;
    private String schemeManager;
    private String queryDate;

    public Scheme(String schemeCode, String schemeName, String subscriptionStatus, float nav, Plan plan, Option option, String category, String assets, String expense, String benchmarkIndex, String queryDate) {
        this.schemeCode = schemeCode;
        this.schemeName = schemeName;
        this.subscriptionStatus = subscriptionStatus;
        this.nav = nav;
        this.plan = plan;
        this.option = option;
        this.category = category;
        this.assets = assets;
        this.expense = expense;
        this.benchmarkIndex = benchmarkIndex;
        this.queryDate = queryDate;
    }

    public String getAssets() {
        return assets;
    }

    public String getBenchmarkIndex() {
        return benchmarkIndex;
    }

    public String getCategory() {
        return category;
    }

    public String getExpense() {
        return expense;
    }

    public float getNav() {
        return nav;
    }

    public Option getOption() {
        return option;
    }

    public Plan getPlan() {
        return plan;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public String getSchemeManager() {
        return schemeManager;
    }

    public void setSchemeManager(String schemeManager) {
        this.schemeManager = schemeManager;
    }
    
    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder();
        retval.append("Scheme[\nschemeCode: ").append(schemeCode).
                append(",\nschemeName: ").append(schemeName).
                append(",\nsubscriptionStatus: ").append(subscriptionStatus).
                append(",\nnav: ").append(nav).
                append(",\nplan: ").append(plan).
                append(",\noption: ").append(option).
                append(",\ncategory: ").append(category).
                append(",\nassets: ").append(assets).
                append(",\nbenchmark:").append(benchmarkIndex).
                append(",\nschemeManager: ").append(schemeManager).
                append(",\nqueryDate: ").append(queryDate).append("\n]");
        return retval.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.schemeCode.equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return schemeCode.hashCode() + schemeName.hashCode();
    }
    
}
