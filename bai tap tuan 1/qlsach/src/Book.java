import java.util.Scanner;

public class Book {
    // 1. Khai báo thuộc tính
    private int id;
    private String title;
    private String author;
    private long price;

    // 2. Constructors (Hàm khởi tạo)
    public Book() {
    }

    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // 3. Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    // 4. Hàm nhập thông tin sách
    public void input() {
        Scanner x = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        this.id = Integer.parseInt(x.nextLine());

        System.out.print("Nhập tên sách: ");
        this.title = x.nextLine();

        System.out.print("Nhập tác giả: ");
        this.author = x.nextLine();

        System.out.print("Nhập đơn giá: ");
        this.price = x.nextLong();
    }

    // 5. Hàm xuất thông tin sách
    public void output() {
        String msg = """
                     BOOK: id= %d, title=%s, author=%s, price=%d
                     """.formatted(id, title, author, price);
        System.out.print(msg);
    }
}