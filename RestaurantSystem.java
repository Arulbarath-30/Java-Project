import java.util.*;

class MenuItem {
    String name;
    double price;

    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    List<MenuItem> items = new ArrayList<>();

    void addItem(MenuItem item) {
        items.add(item);
        System.out.println(item.name + " added to your order.");
    }

    double getTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.price;
        }
        return total;
    }

    void showOrder() {
        if (items.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }
        System.out.println("\n--- Your Order ---");
        for (MenuItem item : items) {
            System.out.println(item.name + " - ₹" + item.price);
        }
        System.out.println("Total: ₹" + getTotal());
    }
}

public class RestaurantSystem {
    static List<MenuItem> menu = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Order order = new Order();

    public static void main(String[] args) {
        loadMenu();
        int choice;
        do {
            showMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showMenu();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    order.showOrder();
                    break;
                case 4:
                    payBill();
                    break;
                case 5:
                    System.out.println("Thank you for visiting!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void loadMenu() {
        menu.add(new MenuItem("Pizza", 250));
        menu.add(new MenuItem("Burger", 120));
        menu.add(new MenuItem("Pasta", 180));
        menu.add(new MenuItem("Coffee", 80));
        menu.add(new MenuItem("Ice Cream", 100));
    }

    static void showMainMenu() {
        System.out.println("\n=== Welcome to Foodie's Restaurant ===");
        System.out.println("1. View Menu");
        System.out.println("2. Place Order");
        System.out.println("3. View Order");
        System.out.println("4. Pay");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    static void showMenu() {
        System.out.println("\n--- Menu ---");
        int i = 1;
        for (MenuItem item : menu) {
            System.out.println(i++ + ". " + item.name + " - ₹" + item.price);
        }
    }

    static void placeOrder() {
        showMenu();
        System.out.print("Enter item number to add to order (0 to stop): ");
        while (true) {
            int itemNum = scanner.nextInt();
            if (itemNum == 0) break;
            if (itemNum >= 1 && itemNum <= menu.size()) {
                order.addItem(menu.get(itemNum - 1));
            } else {
                System.out.println("Invalid item number.");
            }
        }
    }

    static void payBill() {
        order.showOrder();
        System.out.print("Confirm payment? (yes/no): ");
        String confirm = scanner.next();
        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("Payment of ₹" + order.getTotal() + " successful. Thank you!");
            order = new Order(); // Clear the order
        } else {
            System.out.println("Payment cancelled.");
        }
    }
}
