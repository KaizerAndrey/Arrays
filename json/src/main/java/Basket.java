import com.google.gson.Gson;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Basket implements Serializable {
    protected String[] productNames;
    protected int[] prices;
    protected int[] counts;

    public Basket(String[] productNames, int[] prices) {
        this.productNames = productNames;
        this.prices = prices;
        this.counts = new int[prices.length];
    }

    private Basket(String[] productNames, int[] prices, int[] counts) {
        this.productNames = productNames;
        this.prices = prices;
        this.counts = counts;
    }

    public void addToCart(int productNum, int amount) {
        counts[productNum] = +amount;
    }

    public void printCart() {

    }

    public void saveJson(File jsonFile) throws IOException {
        try (PrintWriter out = new PrintWriter(jsonFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            out.println(json);
        }
    }

    public static Basket loadFromJson(File jsonFile) throws IOException {
        try (Scanner scanner = new Scanner(jsonFile)) {
            String json = scanner.nextLine();
            Gson gson = new Gson();
            return gson.fromJson(json, Basket.class);
        }
    }

}
