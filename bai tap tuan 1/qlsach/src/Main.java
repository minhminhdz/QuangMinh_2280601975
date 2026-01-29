import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);

        String menu = """
                \n--------------------------------------
                CHƯƠNG TRÌNH QUẢN LÝ SÁCH
                1. Thêm 1 cuốn sách
                2. Xóa 1 cuốn sách (theo ID)
                3. Thay đổi cuốn sách (theo ID)
                4. Xuất thông tin tất cả sách
                5. Tìm sách có tựa đề chứa chữ "Lập trình"
                6. Lấy tối đa K cuốn sách có giá <= P
                7. Tìm sách theo danh sách tác giả
                0. Thoát
                --------------------------------------
                Chọn chức năng: """;

        int chon = 0;
        do {
            System.out.print(menu);
            chon = x.nextInt();
            x.nextLine(); // Xử lý trôi lệnh sau khi nhập số

            switch (chon) {
                case 1 -> {
                    System.out.println("--- THÊM SÁCH ---");
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("-> Đã thêm sách thành công!");
                }
                case 2 -> {
                    System.out.print("Nhập mã sách cần xóa: ");
                    int idDel = x.nextInt();
                    boolean removed = listBook.removeIf(b -> b.getId() == idDel);
                    System.out.println(removed ? "-> Đã xóa thành công!" : "-> Không tìm thấy mã sách!");
                }
                case 3 -> {
                    System.out.print("Nhập mã sách cần sửa: ");
                    int idEdit = x.nextInt();
                    Optional<Book> bookOpt = listBook.stream()
                            .filter(b -> b.getId() == idEdit)
                            .findFirst();

                    if (bookOpt.isPresent()) {
                        System.out.println("Nhập thông tin mới cho sách:");
                        bookOpt.get().input();
                        System.out.println("-> Cập nhật thành công!");
                    } else {
                        System.out.println("-> Không tìm thấy sách!");
                    }
                }
                case 4 -> {
                    System.out.println("--- DANH SÁCH SÁCH ---");
                    if (listBook.isEmpty()) System.out.println("(Danh sách trống)");
                    else listBook.forEach(Book::output);
                }
                case 5 -> {
                    System.out.println("--- SÁCH 'LẬP TRÌNH' ---");
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhập số lượng sách (K): ");
                    int k = x.nextInt();
                    System.out.print("Nhập giá tối đa (P): ");
                    long p = x.nextLong();
                    System.out.println("--- KẾT QUẢ ---");
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.print("Nhập các tác giả (cách nhau bởi dấu phẩy): ");
                    String inputAuthors = x.nextLine();
                    Set<String> authorSet = Arrays.stream(inputAuthors.split(","))
                            .map(String::trim)
                            .collect(Collectors.toSet());
                    System.out.println("--- KẾT QUẢ TÌM KIẾM ---");
                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor()))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Kết thúc chương trình.");
                default -> System.out.println("Chức năng không hợp lệ!");
            }
        } while (chon != 0);
    }
}