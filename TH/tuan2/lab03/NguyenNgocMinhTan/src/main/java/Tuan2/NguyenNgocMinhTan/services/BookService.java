package Tuan2.NguyenNgocMinhTan.services;

import Tuan2.NguyenNgocMinhTan.entities.Book;
import Tuan2.NguyenNgocMinhTan.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final List<Book> books;
    private final List<Category> categories;
    public List<Book> getAllBooks() {
        return books;
    }
    public List<Category> getAllCategories() {
        return categories;
    }
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
    public List<Book> getBooksByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<String> getBooksByTitleContains(String query) {
        return getBooksByTitle(query).stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    // Method to search author names containing the given query string
    public List<String> getBooksByAuthorContains(String query) {
        return getBooksByAuthor(query).stream()
                .map(Book::getAuthor)
                .collect(Collectors.toList());
    }

    public Optional<Category> getCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }

    public Optional<Category> getCategoryByName(String name) {
    return categories.stream()
                    .filter(category -> category.getName().equals(name))
                    .findFirst();
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void addCategory(Category category) {
        categories.add(category);
    }

    public void updateBook(Book book, Category category) {
        var bookOptional = getBookById(book.getId());
        if (bookOptional.isPresent()) {
                Book bookUpdate = bookOptional.get();
                bookUpdate.setTitle(book.getTitle());
                bookUpdate.setAuthor(book.getAuthor());
                bookUpdate.setPrice(book.getPrice());
                bookUpdate.setCategoryName(book.getCategoryName());
        }
    }
    public void updateCategory(Category category){
        var categoryOptional = getCategoryById(category.getId());
        if(categoryOptional.isPresent()){
            Category categoryUpdate = categoryOptional.get();
            categoryUpdate.setName(category.getName());
        }

    }
    public void deleteBookById(Long id) {
        getBookById(id).ifPresent(books::remove);
    }
    public void deleteCategoryById(Long id) {
        getCategoryById(id).ifPresent(categories::remove);
    }

}
