package Tuan2.NguyenNgocMinhTan.controller;

import Tuan2.NguyenNgocMinhTan.entities.Book;
import Tuan2.NguyenNgocMinhTan.entities.Category;
import Tuan2.NguyenNgocMinhTan.services.BookService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final BookService bookService;
    @GetMapping
    public String showAllCategory(@NotNull Model model) {
        model.addAttribute("categories", bookService.getAllCategories());
        return "category/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(@NotNull Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        if(bookService.getCategoryById(category.getId()).isEmpty())
            bookService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@NotNull Model model, @PathVariable long id)
    {
        var category = bookService.getCategoryById(id).orElse(null);
        model.addAttribute("category", category != null ? category : new Category());
        return "category/edit";
    }
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute("category") Category category) {
        bookService.updateCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        if (bookService.getCategoryById(id).isPresent())
            bookService.deleteCategoryById(id);
        return "redirect:/categories";
    }

}
