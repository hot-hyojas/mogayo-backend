package org.hothyojas.mogayobackend.repositories;

import java.util.List;
import java.util.Optional;
import org.hothyojas.mogayobackend.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionsRepository extends JpaRepository<Question, Integer> {

    List<Question> findByParentId(int parentId);

    @Query("select q from Question q left join fetch q.parent left join fetch q.deliveries d left join fetch d.child where q.id = :questionId order by q.createdAt desc")
    Optional<Question> findWithDeliveriesById(@Param("questionId") int questionId);

    @Query("select q from Question q where q.isDelivered = false and q.id > :cursorId order by q.id asc")
    Page<Question> findNotDelivered(@Param("cursorId") int cursorId, Pageable pageable);

    @Query("select q.id from Question q order by q.id desc")
    Page<Integer> getLast(Pageable pageable);
}