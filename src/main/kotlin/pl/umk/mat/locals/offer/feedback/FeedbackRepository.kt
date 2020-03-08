package pl.umk.mat.locals.offer.feedback

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedbackRepository : CrudRepository<Feedback, Long>