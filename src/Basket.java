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

    public void saveTxt(File textFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String productName : productNames) {
                out.print(productName + " ");
            }
            out.println();
            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();
            for (int count : counts) {
                out.print(count + " ");
            }
            out.println();
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(textFile))) {
            String[] productNames = scanner.nextLine().trim().split(" ");
            int[] prices = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] counts = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new Basket(productNames, prices, counts);
        }
    }
    public void saveBin(File binFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFile))) {
            out.writeObject(this);
        }

    }
    public static Basket loadFromBinFile(File binFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFile))) {
            return (Basket) in.readObject();
        }
    }
}