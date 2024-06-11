package Tuan2.NguyenNgocMinhTan.controller;

import Tuan2.NguyenNgocMinhTan.entities.Book;
import Tuan2.NguyenNgocMinhTan.entities.Category;
import Tuan2.NguyenNgocMinhTan.services.BookService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping
    public String showAllBooks(@NotNull Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(@NotNull Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        if(bookService.getBookById(book.getId()).isEmpty())
            bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@NotNull Model model, @PathVariable long id)
    {
        var book = bookService.getBookById(id).orElse(null);
        //       var categories = bookService.getAllCategories();
        model.addAttribute("book", book != null ? book : new Book());
//        model.addAttribute("categories", categories);
        return "book/edit";
    }
    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book,@ModelAttribute("category") Category category) {
        bookService.updateBook(book,category);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        if (bookService.getBookById(id).isPresent())
            bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String author, Model model) {
        Set<Book> foundBooks = new HashSet<>();

        if (title != null && !title.isEmpty()) {
            foundBooks.addAll(bookService.getBooksByTitle(title));
        }

        if (author != null && !author.isEmpty()) {
            foundBooks.addAll(bookService.getBooksByAuthor(author));
        }

        model.addAttribute("books", foundBooks.isEmpty() ? bookService : foundBooks);
        return "book/search";
    }
    @GetMapping("/autocomplete")
    @ResponseBody
    public List<String> autocomplete(@RequestParam String query, @RequestParam String type) {
        List<String> suggestions = new ArrayList<>();
        if (type.equals("title")) {
            suggestions = bookService.getBooksByTitleContains(query);
        } else if (type.equals("author")) {
            suggestions = bookService.getBooksByAuthorContains(query);
        }
        return suggestions;
    }
}
