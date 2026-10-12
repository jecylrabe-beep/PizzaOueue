import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PizzaQueueSystem {

    static class Order {
        int queueNo;
        String name;
        String size;
        int quantity;
        double price;

        Order(int queueNo, String name, String size,
              int quantity, double price) {
            this.queueNo = queueNo;
            this.name = name;
            this.size = size;
            this.quantity = quantity;
            this.price = price;
        }

        double getTotal() {
            return price * quantity;
        }
    }

    static Queue<Order> queue = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);
    static int queueNumber = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== PIZZAHUB =====");
            System.out.println("1. Order Pizza");
            System.out.println("2. View Queue");
            System.out.println("3. Serve Customer");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String input = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    orderPizza();
                    break;
                case 2:
                    viewQueue();
                    break;
                case 3:
                    serveCustomer();
                    break;
                case 4:
                    System.out.println("Thank you for visiting PizzaHub!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Select 1, 2, 3, or 4.");
            }
        }
    }

    static void orderPizza() {
        System.out.print("\nEnter customer name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Customer name cannot be empty!");
            return;
        }

        System.out.println("\n===== PIZZA MENU =====");
        System.out.println("1. Small  - PHP 150.00");
        System.out.println("2. Medium - PHP 250.00");
        System.out.println("3. Large  - PHP 350.00");
        System.out.print("Choose pizza size (1-3): ");

        String input = sc.nextLine().trim();
        int choice;

        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid size selection!");
            return;
        }

        String size;
        double price;

        switch (choice) {
            case 1:
                size = "Small";
                price = 150.00;
                break;
            case 2:
                size = "Medium";
                price = 250.00;
                break;
            case 3:
                size = "Large";
                price = 350.00;
                break;
            default:
                System.out.println("Invalid size selection!");
                return;
        }

        System.out.print("Enter quantity: ");
        input = sc.nextLine().trim();

        int quantity;

        try {
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Quantity must be a valid number!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero!");
            return;
        }

        Order order = new Order(
            queueNumber++, name, size, quantity, price
        );

        queue.offer(order);

        System.out.println("\n===== ORDER RECEIPT =====");
        System.out.println("Queue Number: " + order.queueNo);
        System.out.println("Customer: " + order.name);
        System.out.println("Pizza Size: " + order.size);
        System.out.println("Quantity: " + order.quantity);
        System.out.printf("Price per pizza: PHP %.2f%n", order.price);
        System.out.printf("Total: PHP %.2f%n", order.getTotal());
        System.out.println("Order successfully added to the queue!");
    }

    static void viewQueue() {
        System.out.println("\n===== WAITING QUEUE =====");

        if (queue.isEmpty()) {
            System.out.println("No customers waiting.");
            return;
        }

        for (Order order : queue) {
            System.out.println("-------------------------");
            System.out.println("Queue Number: " + order.queueNo);
            System.out.println("Customer: " + order.name);
            System.out.println("Pizza Size: " + order.size);
            System.out.println("Quantity: " + order.quantity);
            System.out.printf("Total: PHP %.2f%n", order.getTotal());
        }

        System.out.println("-------------------------");
        System.out.println("Total waiting: " + queue.size());
    }

    static void serveCustomer() {
        System.out.println("\n===== SERVE CUSTOMER =====");

        if (queue.isEmpty()) {
            System.out.println("No customers to serve.");
            return;
        }

        Order order = queue.poll();

        System.out.println("Now serving Queue #" + order.queueNo);
        System.out.println("Customer: " + order.name);
        System.out.println("Pizza Size: " + order.size);
        System.out.println("Quantity: " + order.quantity);
        System.out.printf("Total: PHP %.2f%n", order.getTotal());
        System.out.println("Order completed successfully!");
        System.out.println("Remaining customers: " + queue.size());
    }
}