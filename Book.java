import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Default: book is available
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Mark book as borrowed
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    // Mark book as returned
    public void returnBook() {
        isAvailable = true;
        System.out.println(title + " has been returned.");
    }

    // Display book details
    public void displayBook() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}

// Library class
class Library {
    private ArrayList<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // Add book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " has been added to the library.");
    }

    // Remove book by ID
    public void removeBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                System.out.println("Book with ID " + bookId + " has been removed.");
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    // Search book by ID
    public Book searchBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Display all books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Library Books:");
            for (Book book : books) {
                book.displayBook();
            }
        }
    }
}

// Main class with user interaction
public class LibraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Adding some books initially
        library.addBook(new Book(101, "Java Programming", "James Gosling"));
        library.addBook(new Book(102, "Python Basics", "Guido van Rossum"));
        library.addBook(new Book(103, "Data Structures", "Robert Lafore"));

        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display Books");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;

                case 2: // Remove Book
                    System.out.print("Enter Book ID to remove: ");
                    int removeId = scanner.nextInt();
                    library.removeBook(removeId);
                    break;

                case 3: // Search Book
                    System.out.print("Enter Book ID to search: ");
                    int searchId = scanner.nextInt();
                    Book foundBook = library.searchBook(searchId);
                    if (foundBook != null) {
                        foundBook.displayBook();
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 4: // Display Books
                    library.displayBooks();
                    break;

                case 5: // Borrow Book
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    Book borrowBook = library.searchBook(borrowId);
                    if (borrowBook != null) {
                        borrowBook.borrowBook();
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 6: // Return Book
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    Book returnBook = library.searchBook(returnId);
                    if (returnBook != null) {
                        returnBook.returnBook();
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 7: // Exit
                    System.out.println("Exiting the Library System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 7.");
            }
        }
    }
}
