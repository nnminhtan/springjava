package Tuan2.NguyenNgocMinhTan.controller;

import Tuan2.NguyenNgocMinhTan.entities.Category;
import Tuan2.NguyenNgocMinhTan.entities.Nhanvien;
import Tuan2.NguyenNgocMinhTan.services.BookService;
import Tuan2.NguyenNgocMinhTan.services.NhanVienService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanviens")
@RequiredArgsConstructor

public class NhanVienController {
    private final NhanVienService nhanVienService;
    @GetMapping
    public String showAllCategory(@NotNull Model model) {
        model.addAttribute("nhanviens", nhanVienService.getAllNVs());
        return "nhanvien/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(@NotNull Model model) {
        model.addAttribute("nhanvien", new Nhanvien());
        return "nhanvien/add";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("nhanvien") Nhanvien nhanvien) {
        if(nhanVienService.getNVById(nhanvien.getId()).isEmpty())
            nhanVienService.addNhanVien(nhanvien);
        return "redirect:/nhanviens";
    }
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@NotNull Model model, @PathVariable long id)
    {
        var nhanvien = nhanVienService.getNVById(id).orElse(null);
        model.addAttribute("nhanvien", nhanvien != null ? nhanvien : new Nhanvien());
        return "nhanvien/edit";
    }
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute("nhanvien") Nhanvien nhanvien) {
        nhanVienService.updateCategory(nhanvien);
        return "redirect:/nhanviens";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        if (nhanVienService.getNVById(id).isPresent())
            nhanVienService.deleteNVById(id);
        return "redirect:/nhanviens";
    }
}
