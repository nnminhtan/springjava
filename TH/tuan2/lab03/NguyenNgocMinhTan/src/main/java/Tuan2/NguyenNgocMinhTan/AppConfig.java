package Tuan2.NguyenNgocMinhTan;
import Tuan2.NguyenNgocMinhTan.entities.Book;
import Tuan2.NguyenNgocMinhTan.entities.Category;
import Tuan2.NguyenNgocMinhTan.entities.Nhanvien;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class AppConfig {
    @Bean
    public List<Category> getCategories(){
        var categories = new ArrayList<Category>();
        categories.add(new Category(1L, "Comedy"));
        categories.add(new Category(2L, "Công nghệ thông tin"));
        categories.add(new Category(3L, "DingDong"));
    return categories;
    }
    public String getCategoryNameById(Long id) {
        return getCategories().stream()
                .filter(category -> category.getId().equals(id))
                .map(Category::getName)
                .findFirst()
                .orElse("Unknown Category");
    }
    @Bean
    public List<Book> getBooks() {
        var books = new ArrayList<Book>();
        books.add(new Book(1L, "Lập trình Web Spring Framework", "Ánh Nguyễn", 29.99, getCategoryNameById(1L)));
        books.add(new Book(2L, "Lập trình ứng dụng Java", "Huy Cường", 45.63, getCategoryNameById(2L)));
        books.add(new Book(3L, "Lập trình Web Spring Boot", "Xuân Nhân", 12., getCategoryNameById(3L)));
        books.add(new Book(4L, "Lập trình Web Spring MVC", "Ánh Nguyễn", .12, getCategoryNameById(2L)));
        return books;
    }
    @Bean
    public List<Nhanvien> getNhanViens(){
        var nhanviens = new ArrayList<Nhanvien>();
        nhanviens.add(new Nhanvien(1L,"Mario"));
        nhanviens.add(new Nhanvien(2L,"Wario"));
        nhanviens.add(new Nhanvien(3L,"Luigi"));
        nhanviens.add(new Nhanvien(4L,"Tan"));
        return nhanviens;
    }

}
