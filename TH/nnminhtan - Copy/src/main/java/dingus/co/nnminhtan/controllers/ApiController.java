package dingus.co.nnminhtan.controllers;

import dingus.co.nnminhtan.daos.Item;
import dingus.co.nnminhtan.entities.Book;
import dingus.co.nnminhtan.services.BookService;
import dingus.co.nnminhtan.services.CategoryService;
import dingus.co.nnminhtan.services.CartService;
import dingus.co.nnminhtan.viewmodels.BookGetVm;
import dingus.co.nnminhtan.viewmodels.BookUpdateVm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ApiController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @GetMapping("/books")
    public ResponseEntity<List<BookGetVm>> getAllBooks(Integer pageNo,
                                                       Integer pageSize, String sortBy) {
        return ResponseEntity.ok(bookService.getAllBooks(
                        pageNo == null ? 0 : pageNo,
                        pageSize == null ? 20 : pageSize,
                        sortBy == null ? "id" : sortBy)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }

    @GetMapping("/books/id/{id}")
    public ResponseEntity<BookGetVm> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getBookById(id)
                .map(BookGetVm::from)
                .orElse(null));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<BookGetVm>> searchBooks(String keyword)
    {
        return ResponseEntity.ok(bookService.searchBook(keyword)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        updatedBook.setId(id);
        bookService.updateBook(updatedBook);
        return ResponseEntity.ok(updatedBook);
    }
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book addedBook) {
        bookService.addBook(addedBook);
        return ResponseEntity.ok(addedBook);
    }
    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/books";
    }
}
