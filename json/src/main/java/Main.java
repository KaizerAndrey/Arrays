import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] productNames = {"Хлеб", "Молоко", "Масло"};
        int[] prices = {25, 100, 115};

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("config.xml");

        XPath xPath = XPathFactory.newInstance().newXPath();
        String loadFileName = xPath
                .compile("config/load/fileName")
                .evaluate(doc);
        boolean loadFileEnabled = Boolean.parseBoolean(xPath
                .compile("/config/load/enabled")
                .evaluate(doc));


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
        basket.saveJson(new File("basket.json"));

        basket.printCart();

    }
}
