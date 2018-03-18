package lambdasinaction.chap11;

import static lambdasinaction.chap11.Util.execute;

public class RunFindPrice {
    
    private static final String PRODUCT = "PHONE_X";
    private static BestPriceFinderDiscount discount = new BestPriceFinderDiscount();
    private static BestPriceFinderCurrencyExchange exchange = new BestPriceFinderCurrencyExchange();

    public static void main(String[] args) {
//        runV1Discount();
//        runV2Exchange();
        runV3usd();
//        runFastestResults();
        System.exit(0);
    }
    
    private static void runFastestResults() {
        System.out.println("Fastest: >>>>");
        discount.printPricesStreamFastestResponse(PRODUCT);
    }
    
    private static void runV1Discount() {
        System.out.println("V1Discount >>>");
        execute("sequential", () -> discount.findPricesSequential(PRODUCT));
        execute("parallel", () -> discount.findPricesParallel(PRODUCT));
        execute("composed CompletableFuture", () -> discount.findPricesFuture(PRODUCT));
    }
    
    private static void runV2Exchange() {
        System.out.println("V2Exchange >>>");
        execute("sequential", () -> exchange.findPricesSequential(PRODUCT));
        execute("parallel", () -> exchange.findPricesParallel(PRODUCT));
        execute("composed CompletableFuture", () -> exchange.findPricesFuture(PRODUCT));
    }
    
    private static void runV3usd() {
        System.out.println("V3usd >>>");
        execute("combined USDJava7", () -> exchange.findPricesInUSDJava7(PRODUCT));
//        execute("combined USD1", () -> exchange.findPricesInUSD(PRODUCT));
//        execute("combined USD2", () -> exchange.findPricesInUSD2(PRODUCT));
//        execute("combined USD3", () -> exchange.findPricesInUSD3(PRODUCT));
        execute("combined USD4", () -> exchange.findPricesInUSD4(PRODUCT));
    }
    
}
