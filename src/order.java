import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class order {
    private String orderId;
    private List<Book> books;
    private String orderDate;
    private String status;
    private double totalPrice;

    public order(String orderId) {
        this.orderId = orderId;
        this.books = new ArrayList<>();
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "Pending";
        this.totalPrice = 0.0;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addBook(Book book) {
        books.add(book);
        totalPrice += book.getPrice();
    }

    public void displayOrder() {
        System.out.println("\n===== ORDER DETAILS =====");
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("\nBooks in order:");
        for (Book book : books) {
            book.displayInfo();
        }
        System.out.println("\nTotal Price: $" + String.format("%.2f", totalPrice));
        System.out.println("=======================");
    }
}
