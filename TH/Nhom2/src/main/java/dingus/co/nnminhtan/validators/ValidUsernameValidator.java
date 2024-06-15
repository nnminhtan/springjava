package dingus.co.nnminhtan.validators;

import dingus.co.nnminhtan.services.UserService;
import dingus.co.nnminhtan.validators.annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String>{
    private final UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext
            context) {
        return userService.findByUsername(username).isEmpty();
    }
}
