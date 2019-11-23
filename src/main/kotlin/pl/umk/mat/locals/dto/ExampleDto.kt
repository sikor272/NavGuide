package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.Example
import pl.umk.mat.locals.validators.annotations.ExampleValidator

data class ExampleDto(
        val id: Long,

        @ExampleValidator
        val exampleString: String
){
    constructor(example: Example):this(
            id = example.id,
            exampleString = example.exampleField
    )
}