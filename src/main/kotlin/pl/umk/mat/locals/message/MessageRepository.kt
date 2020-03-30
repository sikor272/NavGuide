package pl.umk.mat.locals.message

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface MessageRepository : CrudRepository<Message, Long>