import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] productNames = {"Хлеб", "Молоко", "Масло"};
        int[] prices = {25, 100, 115};

        Basket basket = new Basket(productNames, prices);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите товар и количество: ");
            String line = scanner.nextLine();
            if (line.equals("end")) {
                break;
            }
            String[] parts = line.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1;
            int count = Integer.parseInt(parts[1]);
            basket.addToCart(productNum, count);
        }
        basket.saveBin(new File("basket.bin"));

        basket.printCart();
    }
}

