package pl.umk.mat.locals.message


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.purchase.PurchaseRequestRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.util.*
import javax.security.auth.message.AuthException

@Service
class MessageService(
        private val messageRepository: MessageRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository
) {
    fun addNewMessage(user: User, newMessage: NewMessage, purchaseId: Long) {
        val purchaseRequest = purchaseRequestRepository.findByIdOrThrow(purchaseId)
        if(purchaseRequest.traveler.id == user.id || purchaseRequest.offer.owner.user.id == user.id)
            messageRepository.save(
                    Message(
                            author = user,
                            description = newMessage.description,
                            date = Date(),
                            purchaseRequest = purchaseRequest
                    )
            )
        throw AuthException("You don't have permission to add messages!")
    }
    fun getAllMessagesForPurchaseRequest(user: User, id: Long): List<MessageDto>{
        val purchaseRequest = purchaseRequestRepository.findByIdOrThrow(id)
        if(purchaseRequest.traveler.id == user.id || purchaseRequest.offer.owner.user.id == user.id)
            return purchaseRequest.chatMessage.map { MessageDto(it) }
        throw AuthException("You don't have permission to display messages!")
    }
}