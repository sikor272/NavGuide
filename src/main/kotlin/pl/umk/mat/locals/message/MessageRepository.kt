package pl.umk.mat.locals.message

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.offer.purchase.PurchaseRequest
import pl.umk.mat.locals.user.User


@Repository
interface MessageRepository : CrudRepository<Message, Long>