package dingus.co.nnminhtan.validators;

import dingus.co.nnminhtan.entities.Category;
import dingus.co.nnminhtan.validators.annotations.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category,
                           ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}
