import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderManager {
    private List<order> orders;
    private BookManager bookManager;

    public OrderManager(BookManager bookManager) {
        this.orders = new ArrayList<>();
        this.bookManager = bookManager;
    }

    public order createOrder() {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order newOrder = new order(orderId);
        orders.add(newOrder);
        return newOrder;
    }

    public void addBookToOrder(order order, String bookId) {
        Book book = bookManager.findBookById(bookId);
        if (book != null) {
            order.addBook(book);
            System.out.println("Book added to order successfully!");
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n===== ALL ORDERS =====");
        for (order order : orders) {
            order.displayOrder();
            System.out.println("-----------------------");
        }
    }

    public order findOrderById(String orderId) {
        for (order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public void updateOrderStatus(String orderId, String newStatus) {
        order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            System.out.println("Order status updated successfully!");
        } else {
            System.out.println("Order not found with ID: " + orderId);
        }
    }
}