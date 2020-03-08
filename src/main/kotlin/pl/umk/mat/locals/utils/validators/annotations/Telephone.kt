package pl.umk.mat.locals.utils.validators.annotations

import pl.umk.mat.locals.utils.validators.implementations.TelephoneValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [TelephoneValidator::class])
annotation class Telephone(
        val message: String = "Invalid telephone",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)