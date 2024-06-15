package dingus.co.nnminhtan.viewmodels;

import dingus.co.nnminhtan.entities.Book;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookPostVm(String title, String author, Double price, Long categoryId) {
    public static BookPostVm from(@NotNull Book book) {
        return new BookPostVm(book.getTitle(), book.getAuthor(),
                book.getPrice(), book.getCategory().getId());
    }
}
