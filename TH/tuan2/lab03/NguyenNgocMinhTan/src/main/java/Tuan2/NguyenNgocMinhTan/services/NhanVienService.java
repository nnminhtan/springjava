package Tuan2.NguyenNgocMinhTan.services;

import Tuan2.NguyenNgocMinhTan.entities.Book;
import Tuan2.NguyenNgocMinhTan.entities.Category;
import Tuan2.NguyenNgocMinhTan.entities.Nhanvien;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NhanVienService {
    private final List<Nhanvien> nhanviens;

    public List<Nhanvien> getAllNVs() {
        return nhanviens;
    }
    public Optional<Nhanvien> getNVById(Long id) {
        return nhanviens.stream()
                .filter(nhanvien -> nhanvien.getId().equals(id))
                .findFirst();
    }

    public void addNhanVien(Nhanvien nhanvien) {
        nhanviens.add(nhanvien);
    }
    public void updateCategory(Nhanvien nhanvien){
        var NhanVienOptional = getNVById(nhanvien.getId());
        if(NhanVienOptional.isPresent()){
            Nhanvien NhanVienUpdate = NhanVienOptional.get();
            NhanVienUpdate.setName(nhanvien.getName());
        }

    }
    public void deleteNVById(Long id) {
        getNVById(id).ifPresent(nhanviens::remove);
    }
}
