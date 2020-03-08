package pl.umk.mat.locals.utils.validators.implementations

import pl.umk.mat.locals.utils.validators.annotations.Telephone
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TelephoneValidator : ConstraintValidator<Telephone, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        if ("^[0-9]{11}\$".toRegex().matches(value ?: return false)) {
            return true
        }

        return false
    }
}
