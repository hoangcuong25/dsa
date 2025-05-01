import java.util.Scanner;
import java.util.List;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static BookManager bookManager = new BookManager();
    private static OrderManager orderManager = new OrderManager(bookManager);
    private static order currentOrder = null;

    public static void main(String[] args) throws Exception {
        boolean running = true;

        while (running) {
            clearScreen();
            displayMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    bookSubMenu();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Show all orders");
                    orderManager.displayAllOrders();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 0:
                    clearScreen();
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void bookSubMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            clearScreen();
            displayBookSubMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    clearScreen();
                    System.out.println("\n===== LIST OF BOOKS =====");
                    bookManager.displayAllBooks();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Search books by title or author:");
                    String keyword = scanner.nextLine();
                    List<Book> searchResults = bookManager.searchBooks(keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println("--------------------------------");
                        System.out.println("Search results:");
                        for (Book book : searchResults) {
                            book.displayInfo();
                        }
                    }
                    break;
                case 3:
                    sortBooksMenu();
                    break;
                case 4:
                    addToCartMenu();
                    break;
                case 0:
                    backToMain = true;
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (!backToMain) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private static void sortBooksMenu() {
        clearScreen();
        System.out.println("\n===== SORT BOOKS =====");
        System.out.println("1. Sort by price (Ascending)");
        System.out.println("2. Sort by price (Descending)");
        System.out.println("0. Back to previous menu");
        System.out.println("=====================");
        System.out.print("Enter your choice: ");

        int choice = getChoice();
        switch (choice) {
            case 1:
                clearScreen();
                bookManager.sortBooksByPrice();
                System.out.println("\nBooks sorted by price (Ascending):");
                bookManager.displayAllBooks();
                break;
            case 2:
                clearScreen();
                bookManager.sortBooksByPriceDescending();
                System.out.println("\nBooks sorted by price (Descending):");
                bookManager.displayAllBooks();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== MENU Online Bookstore =====");
        System.out.println("1. Books Management");
        System.out.println("2. Show all orders");
        System.out.println("0. Exit");
        System.out.println("================");
        System.out.print("Enter your choice: ");
    }

    private static void displayBookSubMenu() {
        System.out.println("\n===== BOOKS MANAGEMENT =====");
        System.out.println("1. Display all books");
        System.out.println("2. Search books");
        System.out.println("3. Sort books");
        System.out.println("4. Add to cart");
        System.out.println("0. Back to main menu");
        System.out.println("=====================");
        System.out.print("Enter your choice: ");
    }

    private static void addToCartMenu() {
        clearScreen();
        System.out.println("\n===== ADD TO CART =====");
        System.out.println("List of available books:");
        bookManager.displayAllBooks();
        System.out.println("\nEnter book ID to add to cart (or '0' to cancel):");
        String bookId = scanner.nextLine();

        if (bookId.equals("0")) {
            return;
        }

        Book selectedBook = bookManager.findBookById(bookId);
        if (selectedBook != null) {
            if (currentOrder == null) {
                currentOrder = orderManager.createOrder();
            }
            orderManager.addBookToOrder(currentOrder, bookId);
        } else {
            System.out.println("\nBook not found with ID: " + bookId);
        }

        System.out.println("\n1. Continue shopping");
        System.out.println("2. View cart");
        System.out.println("3. Place order");
        System.out.println("0. Cancel");
        System.out.print("Enter your choice: ");

        int choice = getChoice();
        switch (choice) {
            case 1:
                addToCartMenu();
                break;
            case 2:
                if (currentOrder != null) {
                    currentOrder.displayOrder();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                break;
            case 3:
                if (currentOrder != null) {
                    currentOrder.setStatus("Completed");
                    System.out.println("\nOrder placed successfully!");
                    currentOrder = null;
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                break;
            case 0:
                if (currentOrder != null) {
                    currentOrder = null;
                    System.out.println("\nOrder cancelled.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
