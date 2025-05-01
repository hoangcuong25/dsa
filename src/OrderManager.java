import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager {
    private Queue<order> orderQueue;
    private List<order> completedOrders;
    private BookManager bookManager;

    public OrderManager(BookManager bookManager) {
        this.orderQueue = new LinkedBlockingQueue<>();
        this.completedOrders = new ArrayList<>();
        this.bookManager = bookManager;
    }

    public order createOrder(String customerName, String shippingAddress) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order newOrder = new order(orderId, customerName, shippingAddress);
        orderQueue.add(newOrder);
        return newOrder;
    }

    public void addBookToOrder(order order, String bookId, int quantity) {
        Book book = bookManager.findBookById(bookId);
        if (book != null) {
            order.addBook(book, quantity);
            System.out.println("Book added to order successfully!");
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    public void displayAllOrders() {
        if (orderQueue.isEmpty() && completedOrders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n===== PENDING ORDERS =====");
        for (order order : orderQueue) {
            order.displayOrder();
            System.out.println("-----------------------");
        }

        System.out.println("\n===== COMPLETED ORDERS =====");
        for (order order : completedOrders) {
            order.displayOrder();
            System.out.println("-----------------------");
        }
    }

    public order findOrderById(String orderId) {
        // Search in queue
        for (order order : orderQueue) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        // Search in completed orders
        for (order order : completedOrders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public List<order> searchOrdersByCustomerName(String customerName) {
        List<order> results = new ArrayList<>();
        // Search in queue
        for (order order : orderQueue) {
            if (order.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                results.add(order);
            }
        }
        // Search in completed orders
        for (order order : completedOrders) {
            if (order.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                results.add(order);
            }
        }
        return results;
    }

    public void displayOrderQueue() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in queue.");
            return;
        }

        System.out.println("\n===== ORDER QUEUE =====");
        int position = 1;
        for (order order : orderQueue) {
            System.out.println("\nPosition in queue: " + position);
            order.displayOrder();
            System.out.println("-----------------------");
            position++;
        }
    }
}