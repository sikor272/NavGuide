package pl.umk.mat.locals.validators.implementations


import pl.umk.mat.locals.validators.annotations.ExampleValidator
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class ExampleValidator : ConstraintValidator<ExampleValidator, String> {
    override fun isValid(data: String?, context: ConstraintValidatorContext?): Boolean {

        if (data == null) {
            return false
        }

        if (data.length > 2048) {
            return false
        }
        return true
    }
}