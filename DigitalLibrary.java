import java.util.*;
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;
    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}
public class DigitalLibrary {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();
    static String adminPassword = "admin123";
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== DIGITAL LIBRARY MANAGEMENT =====");
            System.out.println("1. Admin Module");
            System.out.println("2. User Module");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> adminModule();
                case 2 -> userModule();
                case 3 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }
    static void adminModule() {
        System.out.print("Enter Admin Password: ");
        String pass = sc.next();
        if (!pass.equals(adminPassword)) {
            System.out.println("❌ Wrong password!");
            return;
        }
        int choice;
        do {
            System.out.println("\n===== ADMIN MODULE =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> deleteBook();
                case 4 -> System.out.println("Logging out Admin...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
    static void userModule() {
        int choice;
        do {
            System.out.println("\n===== USER MODULE =====");
            System.out.println("1. Search Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> searchBook();
                case 2 -> issueBook();
                case 3 -> returnBook();
                case 4 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("✅ Book added successfully!");
    }
    static void viewBooks() {
        System.out.println("\n===== BOOK LIST =====");
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }
    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                books.remove(b);
                System.out.println("✅ Book deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }
    static void searchBook() {
        sc.nextLine();
        System.out.print("Enter keyword to search: ");
        String key = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.title.toLowerCase().contains(key) || b.author.toLowerCase().contains(key)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("❌ No matching books found!");
    }
    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    System.out.println("✅ Book issued successfully!");
                } else {
                    System.out.println("❌ Book already issued!");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }
    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    b.isIssued = false;
                    System.out.println("✅ Book returned successfully!");
                } else {
                    System.out.println("❌ This book was not issued!");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }
}
