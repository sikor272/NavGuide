package pl.umk.mat.locals.services.rabbit


import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import pl.umk.mat.locals.services.rabbit.dto.SampleDto

@Service
class RabbitTestService(
         private val rabbitTemplate: RabbitTemplate
) {
    fun send() {
        rabbitTemplate.convertAndSend("locals", "email.welcome",
                SampleDto("kbachon97@gmail.com", "Krzysztof", "Bachoń"))
    }
}


