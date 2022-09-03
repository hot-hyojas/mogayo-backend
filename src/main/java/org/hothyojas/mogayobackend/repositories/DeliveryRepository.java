package org.hothyojas.mogayobackend.repositories;

import org.hothyojas.mogayobackend.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends CustomDeliveryRepository, JpaRepository<Delivery, Integer> {}
