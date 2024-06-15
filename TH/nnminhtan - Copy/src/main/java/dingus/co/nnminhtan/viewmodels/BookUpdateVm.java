package dingus.co.nnminhtan.viewmodels;

import dingus.co.nnminhtan.entities.Book;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookUpdateVm(Long id, String title, String author, Double
        price, String category) {
    public static BookUpdateVm from(@NotNull Book book) {
        return BookUpdateVm.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .category(book.getCategory().getName())
                .build();
    }
}
