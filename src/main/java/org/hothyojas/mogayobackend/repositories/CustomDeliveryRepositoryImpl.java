package org.hothyojas.mogayobackend.repositories;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomDeliveryRepositoryImpl implements CustomDeliveryRepository {

    private final EntityManager em;

    @Override
    public Page<Delivery> findByThreshold(LocalDateTime threshold, Pageable pageable) {
        TypedQuery<Delivery> query = em.createQuery(
                                     "select d from Delivery d left join fetch d.child where d.isResponded = false and d.createdAt <= :threshold", Delivery.class);
        query.setParameter("threshold", threshold);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        List<Delivery> deliveryList = query.getResultList();

        Query countQuery = em.createQuery(
            "select count(d.id) from Delivery d where d.isResponded = false and d.createdAt <= :threshold");
        countQuery.setParameter("threshold", threshold);
        int totalCount = (int)(long)countQuery.getSingleResult();
        return new PageImpl<>(deliveryList, pageable, totalCount);
    }
}
