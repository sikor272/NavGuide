package pl.umk.mat.locals.services

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class EmailService(
        private val rabbitTemplate: RabbitTemplate
) {

    fun sendPasswordResetRequestMail(email: String, code: String) {

    }

    fun sendNewPassword(email: String, newPassword: String) {

    }

    fun sendEmailConfirmation(email: String, code: String) {

    }
}