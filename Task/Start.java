package Task;
interface BookOperations {
    void addQuantity(int amount);
    void sellQuantity(int amount);
}
interface BookShopOperations {
    boolean insertBook(Book b);
    boolean removeBook(Book b);
    void showAllBooks();
    Book searchBook(String isbn);
}
abstract class Book implements BookOperations {
    private String isbn;
    private String bookTitle;
    private String authorName;
    private double price;
    private int availableQuantity;
    Book() {}
    Book(String isbn, String bookTitle, String authorName, double price, int availableQuantity) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }
    void setIsbn(String isbn) { this.isbn = isbn; }
    void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    void setAuthorName(String authorName) { this.authorName = authorName; }
    void setPrice(double price) { this.price = price; }
    void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    String getIsbn() { return isbn; }
    String getBookTitle() { return bookTitle; }
    String getAuthorName() { return authorName; }
    double getPrice() { return price; }
    int getAvailableQuantity() { return availableQuantity; }
    public void addQuantity(int amount) {
        availableQuantity += amount;
    }
    public void sellQuantity(int amount) {
        if (availableQuantity >= amount) {
            availableQuantity -= amount;
        } else {
            System.out.println("Not enough stock!");
        }
    }
    abstract void showDetails();
}
class StoryBook extends Book {
    private String category;

    StoryBook() {}

    StoryBook(String isbn, String bookTitle, String authorName,
              double price, int availableQuantity, String category) {
        super(isbn, bookTitle, authorName, price, availableQuantity);
        this.category = category;
    }
    void setCategory(String category) { this.category = category; }
    String getCategory() { return category; }
    void showDetails() {
        System.out.println("StoryBook:");
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Title: " + getBookTitle());
        System.out.println("Author: " + getAuthorName());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getAvailableQuantity());
        System.out.println("Category: " + category);
        }
}
class TextBook extends Book {
    private int standard;
    TextBook() {}
    TextBook(String isbn, String bookTitle, String authorName,
             double price, int availableQuantity, int standard) {
        super(isbn, bookTitle, authorName, price, availableQuantity);
        this.standard = standard;
    }
    void setStandard(int standard) { this.standard = standard; }
    int getStandard() { return standard; }
    void showDetails() {
        System.out.println("TextBook:");
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Title: " + getBookTitle());
        System.out.println("Author: " + getAuthorName());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getAvailableQuantity());
        System.out.println("Standard: " + standard);
    }
}
class BookShop implements BookShopOperations {
    private String name;
    private Book[] listOfBooks = new Book[100];
    BookShop() {}
    BookShop(String name) {
        this.name = name;
    }
    void setName(String name) { this.name = name; }
    String getName() { return name; }
    public boolean insertBook(Book b) {
        for (int i = 0; i < listOfBooks.length; i++) {
            if (listOfBooks[i] == null) {
                listOfBooks[i] = b;
                return true;
            }
        }
        return false;
    }
    public boolean removeBook(Book b) {
        for (int i = 0; i < listOfBooks.length; i++) {
            if (listOfBooks[i] == b) {
                listOfBooks[i] = null;
                return true;
            }
        }
        return false;
    }
    public void showAllBooks() {
        for (Book b : listOfBooks) {
            if (b != null) {
                b.showDetails();
            }
        }
    }
    public Book searchBook(String isbn) {
        for (Book b : listOfBooks) {
            if (b != null && b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }
}
public class Start {
    public static void main(String[] args) {

        BookShop shop = new BookShop("My Book Shop");
        StoryBook s1 = new StoryBook("S1", "Story A", "Author A", 200, 10, "Fiction");
        StoryBook s2 = new StoryBook("S2", "Story B", "Author B", 250, 5, "Adventure");
        StoryBook s3 = new StoryBook("S3", "Story C", "Author C", 300, 8, "Drama");
        StoryBook s4 = new StoryBook("S4", "Story D", "Author D", 180, 12, "Fantasy");
        StoryBook s5 = new StoryBook("S5", "Story E", "Author E", 220, 7, "Mystery");
        TextBook t1 = new TextBook("T1", "Math", "Author X", 500, 15, 10);
        TextBook t2 = new TextBook("T2", "Physics", "Author Y", 600, 6, 11);
        TextBook t3 = new TextBook("T3", "Chemistry", "Author Z", 550, 9, 12);
        TextBook t4 = new TextBook("T4", "Biology", "Author W", 520, 11, 9);
        TextBook t5 = new TextBook("T5", "ICT", "Author Q", 450, 13, 8);
        shop.insertBook(s1);
        shop.insertBook(s2);
        shop.insertBook(s3);
        shop.insertBook(s4);
        shop.insertBook(s5);
        shop.insertBook(t1);
        shop.insertBook(t2);
        shop.insertBook(t3);
        shop.insertBook(t4);
        shop.insertBook(t5);
        shop.showAllBooks();
        Book found = shop.searchBook("T3");
        if (found != null) {
            System.out.println("Found Book:");
            found.showDetails();
        }
        t1.addQuantity(5);
        t1.sellQuantity(3);
        System.out.println("After Update:");
        t1.showDetails();
    }
}