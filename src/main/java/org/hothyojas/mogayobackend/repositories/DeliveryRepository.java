package org.hothyojas.mogayobackend.repositories;

import org.hothyojas.mogayobackend.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryRepository extends CustomDeliveryRepository, JpaRepository<Delivery, Integer> {

    @Query("select count(d.id) from Delivery d where d.question.id = :questionId and d.isResponded = true and d.answer is not null")
    int getDeliveryCountByQuestionId(@Param("questionId") int questionId);
}
