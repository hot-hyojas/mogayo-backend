package org.hothyojas.mogayobackend.repositories;

import java.time.LocalDateTime;
import org.hothyojas.mogayobackend.entities.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomDeliveryRepository {

    Page<Delivery> findByThreshold(LocalDateTime threshold, Pageable pageable);


}
