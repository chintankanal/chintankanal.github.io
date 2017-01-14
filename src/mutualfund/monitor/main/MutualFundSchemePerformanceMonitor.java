/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.main;

import java.util.ArrayList;
import java.util.List;
import mutualfund.monitor.model.DurationType;
import mutualfund.monitor.model.Field;
import mutualfund.monitor.model.FieldMonitor;
import mutualfund.monitor.model.MutualFundPerformance;
import mutualfund.monitor.model.ReturnMeasure;
import mutualfund.monitor.model.ReturnType;
import mutualfund.monitor.model.RiskMeasure;
import mutualfund.monitor.model.RiskMeasureType;
import mutualfund.monitor.model.Scheme;
import mutualfund.monitor.model.SchemeReturnMeasure;
import mutualfund.monitor.model.SchemeRiskMeasure;
import mutualfund.monitor.util.MFUtil;

/**
 *
 * @author Chintan Kanal
 */
public class MutualFundSchemePerformanceMonitor implements MutualFundPerformanceMonitor {

    public List<FieldMonitor> monitor(List<MutualFundPerformance> pastPerformances, MutualFundPerformance currentPerformance) {
        List<FieldMonitor> retval = new ArrayList<FieldMonitor>();
        MutualFundPerformance previousPerformance = getPreviousPerformance(pastPerformances);
        
        Scheme scheme = currentPerformance.getScheme();
        Scheme prevScheme = previousPerformance.getScheme();
        
        retval.add(new FieldMonitor(Field.Assets, prevScheme.getAssets(), scheme.getAssets(), null, null, null, null));
        retval.add(new FieldMonitor(Field.BenchmarkIndex, prevScheme.getBenchmarkIndex(), scheme.getBenchmarkIndex(), null, null, null, null));
        retval.add(new FieldMonitor(Field.Category, prevScheme.getCategory(), scheme.getCategory(), null, null, null, null));
        retval.add(new FieldMonitor(Field.Expense, prevScheme.getExpense(), scheme.getExpense(), null, null, null, null));
        retval.add(new FieldMonitor(Field.Nav, String.valueOf(prevScheme.getNav()), String.valueOf(scheme.getNav()), null, null, null, null));
        retval.add(new FieldMonitor(Field.Option, prevScheme.getOption().name(), scheme.getOption().name(), null, null, null, null));
        retval.add(new FieldMonitor(Field.Plan, prevScheme.getPlan().name(), scheme.getPlan().name(), null, null, null, null));
        retval.add(new FieldMonitor(Field.QueryDate, prevScheme.getQueryDate(), scheme.getQueryDate(), null, null, null, null));
        retval.add(new FieldMonitor(Field.SchemeCode, prevScheme.getSchemeCode(), scheme.getSchemeCode(), null, null, null, null));
        retval.add(new FieldMonitor(Field.SchemeName, prevScheme.getSchemeName(), scheme.getSchemeName(), null, null, null, null));
        retval.add(new FieldMonitor(Field.SubscriptionStatus, prevScheme.getSubscriptionStatus(), scheme.getSubscriptionStatus(), null, null, null, null));
        retval.add(new FieldMonitor(Field.SchemeManager, prevScheme.getSchemeManager(), scheme.getSchemeManager(), null, null, null, null));
        
        for(RiskMeasure currentRiskMeasure : currentPerformance.getRiskMeasures()) {
            String prevValue = "-";
            String newValue = "-";
            Float prevBest = null;
            Float prevWorst = null;
            String prevBestTime = "-";
            String prevWorstTime = "-";
            
            if(currentRiskMeasure.getRiskMeasureValue() != 0) {
                newValue = String.valueOf(currentRiskMeasure.getRiskMeasureValue());
            }
            retval.add(new FieldMonitor(currentRiskMeasure.getField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            
            if(currentRiskMeasure.getRankField() != null) {
                newValue = String.valueOf(((SchemeRiskMeasure)currentRiskMeasure).getRankWithinCategory());
                if(newValue.equals("0")) {
                    newValue = "-";
                }
                retval.add(new FieldMonitor(currentRiskMeasure.getRankField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            }
            if(currentRiskMeasure.getNumberOfFundsField() != null) {
                newValue = String.valueOf(((SchemeRiskMeasure)currentRiskMeasure).getNumberOfFundsWithinCategory());
                if(newValue.equals("0")) {
                    newValue = "-";
                }
                retval.add(new FieldMonitor(currentRiskMeasure.getNumberOfFundsField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            }
            if(currentRiskMeasure.getRiskNoteField() != null) {
                newValue = String.valueOf(((SchemeRiskMeasure)currentRiskMeasure).getRiskNote());
                retval.add(new FieldMonitor(currentRiskMeasure.getRiskNoteField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            }
            
//            if(previousPerformance.getRiskMeasures() != null && !previousPerformance.getRiskMeasures().isEmpty()) {
//                for(RiskMeasure prevRiskMeasure : previousPerformance.getRiskMeasures()) {
//                    if(currentRiskMeasure.getSchemeCode().equals(prevRiskMeasure.getSchemeCode()) &&
//                            currentRiskMeasure.getField() == prevRiskMeasure.getField() && prevRiskMeasure.getRiskMeasureValue() != 0) {
//                        prevValue = String.valueOf(prevRiskMeasure.getRiskMeasureValue());
//                    }
//                    retval.add(new FieldMonitor(currentRiskMeasure.getField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
//                }
//            }
//            else {
//                retval.add(new FieldMonitor(currentRiskMeasure.getField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
//            }
        }
        
        for(ReturnMeasure currentReturnMeasure : currentPerformance.getReturnMeasures()) {
            String prevValue = "-";
            String newValue = "-";
            Float prevBest = null;
            Float prevWorst = null;
            String prevBestTime = "-";
            String prevWorstTime = "-";
            
            if(currentReturnMeasure.getReturnPct() != 0) {
                newValue = String.valueOf(currentReturnMeasure.getReturnPct());
            }
            retval.add(new FieldMonitor(currentReturnMeasure.getField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            if(currentReturnMeasure.getRankField() != null) {
                newValue = String.valueOf(((SchemeReturnMeasure)currentReturnMeasure).getRankWithinCategory());
                if(newValue.equals("0")) {
                    newValue = "-";
                }
                retval.add(new FieldMonitor(currentReturnMeasure.getRankField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            }
            if(currentReturnMeasure.getNumberOfFundsField() != null) {
                newValue = String.valueOf(((SchemeReturnMeasure)currentReturnMeasure).getNumberOfFundsInCategory());
                if(newValue.equals("0")) {
                    newValue = "-";
                }
                retval.add(new FieldMonitor(currentReturnMeasure.getNumberOfFundsField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            }
            if(currentReturnMeasure.getReturnsNoteField() != null) {
                newValue = String.valueOf(((SchemeReturnMeasure)currentReturnMeasure).getReturnsNote());
                retval.add(new FieldMonitor(currentReturnMeasure.getReturnsNoteField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
            }
//            if(previousPerformance.getReturnMeasures() != null && !previousPerformance.getReturnMeasures().isEmpty()) {
//                for(ReturnMeasure prevReturnMeasure : previousPerformance.getReturnMeasures()) {
//                    if(currentReturnMeasure.getSchemeCode().equals(prevReturnMeasure.getSchemeCode()) &&
//                            currentReturnMeasure.getField() == currentReturnMeasure.getField() && currentReturnMeasure.getReturnPct() != 0) {
//                        prevValue = String.valueOf(prevReturnMeasure.getReturnPct());
//                    }
//                    retval.add(new FieldMonitor(currentReturnMeasure.getField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
//                }
//            }
//            else {
//                retval.add(new FieldMonitor(currentReturnMeasure.getField(), prevValue, newValue, prevBest, prevWorst, prevBestTime, prevWorstTime));
//            }
        }
        
//        retval.add(new FieldMonitor(Field.Mean, getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Mean, previousPerformance.getRiskMeasures()), getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Mean, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.StdDev, getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.StdDev, previousPerformance.getRiskMeasures()), getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.StdDev, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.Sharpe, getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Sharpe, previousPerformance.getRiskMeasures()), getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Sharpe, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.Sortino, getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Sortino, previousPerformance.getRiskMeasures()), getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Sortino, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.Beta, getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Beta, previousPerformance.getRiskMeasures()), getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Beta, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.Alpha, getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Alpha, previousPerformance.getRiskMeasures()), getRiskMeasureValue(scheme.getSchemeCode(), RiskMeasureType.Alpha, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.BenchmarkMean, getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Mean, previousPerformance.getRiskMeasures()), getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Mean, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.BenchmarkStdDev, getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.StdDev, previousPerformance.getRiskMeasures()), getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.StdDev, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.BenchmarkSharpe, getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Sharpe, previousPerformance.getRiskMeasures()), getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Sharpe, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.BenchmarkSortino, getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Sortino, previousPerformance.getRiskMeasures()), getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Sortino, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.BenchmarkBeta, getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Beta, previousPerformance.getRiskMeasures()), getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Beta, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.BenchmarkAlpha, getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Alpha, previousPerformance.getRiskMeasures()), getRiskMeasureValue(benchmarkSchemeCode, RiskMeasureType.Alpha, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.CategoryMean, getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Mean, previousPerformance.getRiskMeasures()), getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Mean, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.CategoryStdDev, getRiskMeasureValue(categorySchemeCode, RiskMeasureType.StdDev, previousPerformance.getRiskMeasures()), getRiskMeasureValue(categorySchemeCode, RiskMeasureType.StdDev, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.CategorySharpe, getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Sharpe, previousPerformance.getRiskMeasures()), getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Sharpe, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.CategorySortino, getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Sortino, previousPerformance.getRiskMeasures()), getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Sortino, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.CategoryBeta, getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Beta, previousPerformance.getRiskMeasures()), getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Beta, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.CategoryAlpha, getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Alpha, previousPerformance.getRiskMeasures()), getRiskMeasureValue(categorySchemeCode, RiskMeasureType.Alpha, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.SchemeMeanRankWithinCategory, getSchemeRiskRankValue(RiskMeasureType.Mean, previousPerformance.getRiskMeasures()), getSchemeRiskRankValue(RiskMeasureType.Mean, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.SchemeStdDevRankWithinCategory, getSchemeRiskRankValue(RiskMeasureType.StdDev, previousPerformance.getRiskMeasures()), getSchemeRiskRankValue(RiskMeasureType.StdDev, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.SchemeSharpeRankWithinCategory, getSchemeRiskRankValue(RiskMeasureType.Sharpe, previousPerformance.getRiskMeasures()), getSchemeRiskRankValue(RiskMeasureType.Sharpe, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.SchemeSortinoRankWithinCategory, getSchemeRiskRankValue(RiskMeasureType.Sortino, previousPerformance.getRiskMeasures()), getSchemeRiskRankValue(RiskMeasureType.Sortino, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.SchemeBetaRankWithinCategory, getSchemeRiskRankValue(RiskMeasureType.Beta, previousPerformance.getRiskMeasures()), getSchemeRiskRankValue(RiskMeasureType.Beta, currentPerformance.getRiskMeasures()), null, null, null, null));
//        retval.add(new FieldMonitor(Field.SchemeAlphaRankWithinCategory, getSchemeRiskRankValue(RiskMeasureType.Alpha, previousPerformance.getRiskMeasures()), getSchemeRiskRankValue(RiskMeasureType.Alpha, currentPerformance.getRiskMeasures()), null, null, null, null));
        return retval;
    }
    
    private MutualFundPerformance getPreviousPerformance(List<MutualFundPerformance> pastPerformances) {
        MutualFundPerformance retval = null;
        if(pastPerformances == null || pastPerformances.isEmpty()) {
            retval = MFUtil.getDummyMutualFundPerformance();
        }
        return retval;
    }
    
    private String getRiskMeasureValue(String schemeCode, RiskMeasureType riskMeasureType, List<RiskMeasure> riskMeasures) {
        String retval = "-";
        for(RiskMeasure riskMeasure : riskMeasures) {
            if(riskMeasure.getRiskMeasureType() == riskMeasureType && riskMeasure.getSchemeCode().equals(schemeCode) && riskMeasure.getRiskMeasureValue() != 0) {
                retval = String.valueOf(riskMeasure.getRiskMeasureValue());
            }
        }
        return retval;
    }
    
    private String getSchemeRiskRankValue(RiskMeasureType riskMeasureType, List<RiskMeasure> riskMeasures) {
        String retval = "-";
        for(RiskMeasure riskMeasure : riskMeasures) {
            if(riskMeasure.getRiskMeasureType() == riskMeasureType && riskMeasure instanceof SchemeRiskMeasure && ((SchemeRiskMeasure)riskMeasure).getRankWithinCategory() != 0) {
                retval = String.valueOf(((SchemeRiskMeasure)riskMeasure).getRankWithinCategory());
            }
        }
        return retval;
    }
    
    private String getReturnMeasureValue(String schemeCode, DurationType durationType, ReturnType returnType, List<ReturnMeasure> returnMeasures) {
        String retval = "-";
        for(ReturnMeasure returnMeasure : returnMeasures) {
            if(returnMeasure.getSchemeCode().equals(schemeCode) && 
                    returnMeasure.getDurationType() == durationType && 
                    returnType == returnMeasure.getReturnType()&& 
                    returnMeasure.getReturnPct() != 0) {
                retval = String.valueOf(returnMeasure.getReturnPct());
            }
        }
        return retval;
    }
    
    private String getSchemeReturnRankValue(DurationType durationType, ReturnType returnType, List<ReturnMeasure> returnMeasures) {
        String retval = "-";
        for(ReturnMeasure returnMeasure : returnMeasures) {
            if(returnMeasure.getDurationType() == durationType && returnType == returnMeasure.getReturnType() &&
                    returnMeasure instanceof SchemeReturnMeasure && ((SchemeReturnMeasure)returnMeasure).getRankWithinCategory() != 0) {
                retval = String.valueOf(((SchemeReturnMeasure)returnMeasure).getRankWithinCategory());
            }
        }
        return retval;
    }
}
