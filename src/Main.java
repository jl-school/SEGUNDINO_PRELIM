import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat money = new DecimalFormat("#,##0.00");

        System.out.println("============================================================");
        System.out.println("              E-COMMERCE FLASH SALE CHECKOUT");
        System.out.println("============================================================");

        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter Unit Price (PHP): ");
        double unitPrice = sc.nextDouble();

        System.out.print("Enter Quantity to Order: ");
        int quantity = sc.nextInt();

        System.out.print("Enter Available Stock Level: ");
        int stockLevel = sc.nextInt();

        System.out.println();
        System.out.println("--- PROCESSING ORDER VALIDATION ---");
        System.out.println();

        boolean inStock = (quantity <= stockLevel);

        double discountRate;
        String discountLabel;
        if (quantity >= 10) {
            discountRate = 0.15;
            discountLabel = "15% (Bulk Tier Applied)";
        } else if (quantity >= 5) {
            discountRate = 0.05;
            discountLabel = "5% (Standard Tier Applied)";
        } else {
            discountRate = 0.0;
            discountLabel = "0% (No Discount)";
        }

        double grossSubtotal = unitPrice * quantity;
        double discountSavings = 0.0;
        double totalAmountDue = grossSubtotal;

        if (!inStock) {
            System.out.println("!!! ORDER DENIED: Requested quantity exceeds available stock. !!!");
            discountRate = 0.0;
            discountLabel = "N/A (Order Denied)";
            discountSavings = 0.0;
            totalAmountDue = 0.0;
        } else {
            discountSavings = grossSubtotal * discountRate;
            totalAmountDue = grossSubtotal - discountSavings;
        }

        System.out.println();
        System.out.println("============================================================");
        System.out.println("                 CHECKOUT ORDER REPORT");
        System.out.println("============================================================");
        System.out.printf("%-20s%s%n", "Customer Name:", customerName);
        System.out.printf("%-20s%s%n", "Unit Price:", "PHP " + money.format(unitPrice));
        System.out.printf("%-20s%s%n", "Quantity Ordered:", quantity + " units");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-20s%s%n", "Stock Status:", inStock ? "PASSED (In Stock)" : "FAILED (Out of Stock)");
        System.out.printf("%-20s%s%n", "Discount Rate:", discountLabel);
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-20s%s%n", "Gross Subtotal:", "PHP " + money.format(grossSubtotal));
        System.out.printf("%-20s%s%n", "Discount Savings:", "PHP " + money.format(discountSavings));
        System.out.printf("%-20s%s%n", "TOTAL AMOUNT DUE:", "PHP " + money.format(totalAmountDue));
        System.out.println("============================================================");

        sc.close();
    }
}