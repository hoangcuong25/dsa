import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static BookManager bookManager = new BookManager();

    public static void main(String[] args) throws Exception {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    bookManager.displayAllBooks();
                    break;
                case 2:
                    System.out.println("Show all orders");
                    // Add order functionality here
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== MENU Online Bookstore =====");
        System.out.println("1. Display list books");
        System.out.println("2. Show all orders");
        System.out.println("0. Exit");
        System.out.println("================");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
