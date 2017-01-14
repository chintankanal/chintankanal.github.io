/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public enum Field {
    QueryDate(true, "QueryDate", 1),
    SchemeCode(true, "SchemeCode", 2),
    SchemeName(true, "Scheme", 3),
    SubscriptionStatus(true, "Status", 4),
    Nav(true, "NAV", 5),
    Plan(true, "Plan", 6),
    Option(true, "Option", 7),
    Category(true, "Category", 8),
    Assets(true, "AUM", 9),
    Expense(true, "ExpenseRatio", 10),
    BenchmarkIndex(true, "Benchmark", 11),
    SchemeManager(true, "FundManager", 12),
    Mean(true, "Mean", 13),
    StdDev(true, "StdDev", 14),
    Sharpe(true, "Sharpe", 15),
    Sortino(true, "Sortino", 16),
    Beta(true, "Beta", 17),
    Alpha(true, "Alpha", 18),
    BenchmarkMean(true, "BenchmarkMean", 19),
    BenchmarkStdDev(true, "BenchmarkStdDev", 20),
    BenchmarkSharpe(true, "BenchmarkSharpe", 21),
    BenchmarkSortino(true, "BenchmarkSortino", 22),
    BenchmarkBeta(true, "BenchmarkBeta", 23),
    BenchmarkAlpha(true, "BenchmarkAlpha", 24),
    CategoryMean(true, "CategoryMean", 25),
    CategoryStdDev(true, "CategoryStdDev", 26),
    CategorySharpe(true, "CategorySharpe", 27),
    CategorySortino(true, "CategorySortino", 28),
    CategoryBeta(true, "CategoryBeta", 29),
    CategoryAlpha(true, "CategoryAlpha", 30),
    SchemeMeanRankWithinCategory(true, "FundMeanRankWithinCategory", 31),
    SchemeStdDevRankWithinCategory(true, "FundStdRankWithinCategory", 32),
    SchemeSharpeRankWithinCategory(true, "FundSharpeRankWithinCategory", 33),
    SchemeSortinoRankWithinCategory(true, "FundSortinoRankWithinCategory", 34),
    SchemeBetaRankWithinCategory(true, "FundBetaRankWithinCategory", 35),
    SchemeAlphaRankWithinCategory(true, "FundAlphaRankWithinCategory", 36),
    NumberOfSchemesWithinCategory(true, "NumberOfFundsWithinCategory", 37),
    RiskNote(true, "Risk Measures From", 38),
    
    YtdTrailingReturn(true, "YTD (Trailing Return %)", 43),
    OneDayTrailingReturn(true, "1-Day (Trailing Return %)", 44),
    OneWeekTrailingReturn(true, "1-Week (Trailing Return %)", 45),
    OneMonthTrailingReturn(true, "1-Month (Trailing Return %)", 46),
    ThreeMonthTrailingReturn(true, "3-Month (Trailing Return %)", 47),
    SixMonthTrailingReturn(true, "6-Month (Trailing Return %)", 48),
    OneYearTrailingReturn(true, "1-Year (Trailing Return %)", 49),
    ThreeYearTrailingReturn(true, "3-Year (Trailing Return %)", 50),
    FiveYearTrailingReturn(true, "5-Year (Trailing Return %)", 51),
    SevenYearTrailingReturn(true, "7-Year (Trailing Return %)", 52),
    TenYearTrailingReturn(true, "10-Year (Trailing Return %)", 53),
    
    BenchmarkYtdTrailingReturn(true, "YTD (Trailing Return %) Benchmark", 54),
    BenchmarkOneDayTrailingReturn(true, "1-Day (Trailing Return %)) Benchmark", 55),
    BenchmarkOneWeekTrailingReturn(true, "1-Week (Trailing Return %)) Benchmark", 56),
    BenchmarkOneMonthTrailingReturn(true, "1-Month (Trailing Return %)) Benchmark", 57),
    BenchmarkThreeMonthTrailingReturn(true, "3-Month (Trailing Return %)) Benchmark", 58),
    BenchmarkSixMonthTrailingReturn(true, "6-Month (Trailing Return %)) Benchmark", 59),
    BenchmarkOneYearTrailingReturn(true, "1-Year (Trailing Return %)) Benchmark", 60),
    BenchmarkThreeYearTrailingReturn(true, "3-Year (Trailing Return %)) Benchmark", 61),
    BenchmarkFiveYearTrailingReturn(true, "5-Year (Trailing Return %)) Benchmark", 62),
    BenchmarkSevenYearTrailingReturn(true, "7-Year (Trailing Return %)) Benchmark", 63),
    BenchmarkTenYearTrailingReturn(true, "10-Year (Trailing Return %)) Benchmark", 64),
    
    CategoryYtdTrailingReturn(true, "YTD (Trailing Return %) Category", 65),
    CategoryOneDayTrailingReturn(true, "1-Day (Trailing Return %)) Category", 66),
    CategoryOneWeekTrailingReturn(true, "1-Week (Trailing Return %)) Category", 67),
    CategoryOneMonthTrailingReturn(true, "1-Month (Trailing Return %)) Category", 68),
    CategoryThreeMonthTrailingReturn(true, "3-Month (Trailing Return %)) Category", 69),
    CategorySixMonthTrailingReturn(true, "6-Month (Trailing Return %)) Category", 70),
    CategoryOneYearTrailingReturn(true, "1-Year (Trailing Return %)) Category", 71),
    CategoryThreeYearTrailingReturn(true, "3-Year (Trailing Return %)) Category", 72),
    CategoryFiveYearTrailingReturn(true, "5-Year (Trailing Return %)) Category", 73),
    CategorySevenYearTrailingReturn(true, "7-Year (Trailing Return %)) Category", 74),
    CategoryTenYearTrailingReturn(true, "10-Year (Trailing Return %)) Category", 75),
    
    YtdTrailingReturnRank(true, "YTD (Trailing Return %) Rank", 76),
    OneDayTrailingReturnRank(true, "1-Day (Trailing Return %)) Rank", 77),
    OneWeekTrailingReturnRank(true, "1-Week (Trailing Return %)) Rank", 78),
    OneMonthTrailingReturnRank(true, "1-Month (Trailing Return %)) Rank", 79),
    ThreeMonthTrailingReturnRank(true, "3-Month (Trailing Return %)) Rank", 80),
    SixMonthTrailingReturnRank(true, "6-Month (Trailing Return %)) Rank", 81),
    OneYearTrailingReturnRank(true, "1-Year (Trailing Return %)) Rank", 82),
    ThreeYearTrailingReturnRank(true, "3-Year (Trailing Return %)) Rank", 83),
    FiveYearTrailingReturnRank(true, "5-Year (Trailing Return %)) Rank", 84),
    SevenYearTrailingReturnRank(true, "7-Year (Trailing Return %)) Rank", 85),
    TenYearTrailingReturnRank(true, "10-Year (Trailing Return %)) Rank", 86),
    
    NumberOfFundsWithYtdTrailingReturn(true, "Number of Funds with YTD Trailing Return", 98),
    NumberOfFundsWithOneDayTrailingReturn(true, "Number of Funds with 1-Day Trailing Return", 99),
    NumberOfFundsWithOneWeekTrailingReturn(true, "Number of Funds with 1-Week Trailing Return", 100),
    NumberOfFundsWithOneMonthTrailingReturn(true, "Number of Funds with 1-Month Trailing Return", 101),
    NumberOfFundsWithThreeMonthTrailingReturn(true, "Number of Funds with 3-Months Trailing Return", 102),
    NumberOfFundsWithSixMonthTrailingReturn(true, "Number of Funds with 6-Months Trailing Return", 103),
    NumberOfFundsWithOneYearTrailingReturn(true, "Number of Funds with 1-Year Trailing Return", 104),
    NumberOfFundsWithThreeYearTrailingReturn(true, "Number of Funds with 3-Year Trailing Return", 105),
    NumberOfFundsWithFiveYearTrailingReturn(true, "Number of Funds with 5-Year Trailing Return", 106),
    NumberOfFundsWithSevenYearTrailingReturn(true, "Number of Funds with 7-Year Trailing Return", 107),
    NumberOfFundsWithTenYearTrailingReturn(true, "Number of Funds with 10-Year Trailing Return", 108),
    ReturnsNote(true, "Returns Measures From", 109);
    
    private boolean display;
    private String displayName;
    private int displaySequence;

    private Field(boolean display, String displayName, int displaySequence) {
        this.display = display;
        this.displayName = displayName;
        this.displaySequence = displaySequence;
    }

    public boolean isDisplay() {
        return display;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDisplaySequence() {
        return displaySequence;
    }
    
}
