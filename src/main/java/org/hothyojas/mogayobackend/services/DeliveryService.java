package org.hothyojas.mogayobackend.services;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.entities.Delivery;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.repositories.ChildrenRepository;
import org.hothyojas.mogayobackend.repositories.DeliveryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final int CHILD_SELECT_COUNT = 5;

    private final DeliveryRepository deliveryRepository;
    private final ChildrenRepository childrenRepository;

    public void createDelivery(Question question) {

        List<Delivery> deliveries = new ArrayList<Delivery>();
        List<Child> children = childrenRepository.findTargetChildren(PageRequest.of(0, CHILD_SELECT_COUNT));

        for (Delivery delivery : deliveries) {
            delivery.setQuestion(question);

        }
    }
}
