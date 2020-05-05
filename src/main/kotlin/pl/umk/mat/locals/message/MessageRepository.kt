package pl.umk.mat.locals.message

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.offer.purchase.PurchaseRequest


@Repository
interface MessageRepository : CrudRepository<Message, Long> {
    fun getAllByPurchaseRequest(purchaseRequest: PurchaseRequest): List<Message>
}