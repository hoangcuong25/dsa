import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
        initializeBooks();
    }

    private void initializeBooks() {
        books.add(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", 12.99, "Fiction"));
        books.add(new Book("B002", "To Kill a Mockingbird", "Harper Lee", 14.99, "Fiction"));
        books.add(new Book("B003", "1984", "George Orwell", 11.99, "Science Fiction"));
        books.add(new Book("B004", "Pride and Prejudice", "Jane Austen", 9.99, "Romance"));
        books.add(new Book("B005", "The Hobbit", "J.R.R. Tolkien", 16.99, "Fantasy"));
        books.add(new Book("B006", "The Catcher in the Rye", "J.D. Salinger", 13.99, "Fiction"));
        books.add(new Book("B007", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 19.99, "Fantasy"));
        books.add(new Book("B008", "The Da Vinci Code", "Dan Brown", 15.99, "Mystery"));
        books.add(new Book("B009", "The Alchemist", "Paulo Coelho", 10.99, "Fiction"));
        books.add(new Book("B010", "The Hunger Games", "Suzanne Collins", 17.99, "Science Fiction"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void displayAllBooks() {
        System.out.println("\n===== LIST OF BOOKS =====");
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }
}