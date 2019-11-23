package pl.umk.mat.locals.validators.annotations

import pl.umk.mat.locals.validators.implementations.ExampleValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ExampleValidator::class])
annotation class ExampleValidator(
        val message: String = "invalid",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)