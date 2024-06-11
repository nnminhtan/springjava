import entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        var scanner = new Scanner(System.in);
        var msg = """
Chương trình quản lý sách
1. Thêm 1 cuốn sách
2. Xoá 1 cuốn sách
3. Thay đổi thông tin 1 cuốn sách
4. Xuất thông tin tất cả các cuốn sách
5. Tìm kiếm sách "2"
6. Lấy sách tối đa theo giá
7. Tìm kiếm sách theo tên tác giả
0. Thoát
Chọn chức năng:""";
        int choice;
        do {
            System.out.print(msg);
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    Book book1 = new Book(1,"Adventure of monke 1", "Minh tan", 20);
                    Book book2 = new Book(2,"Adventure of monke 2", "Minh tan", 10);
                    Book book3 = new Book(3,"Adventure of monke 3", "Minh tan", 24);
                    Book book4 = new Book(4,"Adventure of monke 4", "monke", 20);
                    Book book5 = new Book(5,"Adventure of monke 5", "monke", 12);
                    Book book6 = new Book(6,"Adventure of monke 6", "Minh tan", 22);


                    // Create an ArrayList to store the books

                    // Add preset books to the ArrayList
                    books.add(book1);
                    books.add(book2);
                    books.add(book3);
                    books.add(book4);
                    books.add(book5);
                    books.add(book6);
                }
                case 2 -> {
                    System.out.print("Nhập mã sách cần xoá: ");
                    var id = Integer.parseInt(scanner.nextLine());
                    books.stream()
                            .filter(book -> book.getId() == id)
                            .findFirst()

                            .ifPresent(books::remove);

                    System.out.println("Đã xoá sách có mã " + id);
                }
                case 3 -> {
                    System.out.print("Nhập mã sách: ");
                    var id = scanner.nextInt();
                    scanner.nextLine();
                    books.stream()
                            .filter(book -> book.getId() == id)
                            .findFirst()

                            .ifPresent(Book::input);

                    System.out.println("Đã thay đổi thông tin sách!");
                }
                case 4 -> {
                    System.out.println("Thông tin tất cả cuốn sách: ");
                    books.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Thông tin các sách có chứa \"2\": ");
                    books.stream()
                            .filter(book -> book.getTitle().contains("2"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhap gia tien: ");
                    long price = Long.parseLong(scanner.nextLine());
                    books.stream().filter(book -> price >= book.getPrice()).limit(price)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.print("Nhap ten tac gia: ");
                    var name = scanner.nextLine();
                    books.stream().filter(book -> (book.getAuthor().contains(name)))
                            .forEach(Book::output);
                }
                case 0 -> System.out.println("Đã thoát");
                default -> throw new IllegalStateException();
            }
        } while (choice != 0);
    }
}
