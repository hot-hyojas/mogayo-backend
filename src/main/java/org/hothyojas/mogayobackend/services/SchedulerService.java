package org.hothyojas.mogayobackend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.entities.Delivery;
import org.hothyojas.mogayobackend.repositories.ChildrenRepository;
import org.hothyojas.mogayobackend.repositories.DeliveryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchedulerService {

    private static final int INTERVAL_SECONDS = 3600; // 1시간
    private static final int FETCH_SIZE = 100;

    private final DeliveryRepository deliveryRepository;
    private final ChildrenRepository childrenRepository;

    @Scheduled(cron = "0 0/5 * * * *") // 5분 마다 스케줄러 실행
    public void scheduler() {
        checkNoResponseChildren(INTERVAL_SECONDS);
    }

    public void checkNoResponseChildren(int intervalSeconds) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.minusSeconds(intervalSeconds);
        log.info(now.toString());
        log.info(threshold.toString());

        PageRequest pageRequest = PageRequest.of(0, FETCH_SIZE);

        // 첫 페이지 가져오기
        Page<Delivery> deliveryPage = deliveryRepository.findByThreshold(threshold, pageRequest);
        int totalPages = deliveryPage.getTotalPages();

        for (int i = 0; i < totalPages; i++) {
            if (i != 0) {
                deliveryPage = deliveryRepository.findByThreshold(threshold, pageRequest);
            }
            log.info("update page " + i);
            Page<Child> children = deliveryPage.map(Delivery::getChild);
            children.forEach(this::updateNoResponseChild);
            deliveryPage.forEach(this::updateNoResponseDelivery);
            childrenRepository.saveAll(children);
            deliveryRepository.saveAll(deliveryPage);

            // TODO: 마음온도 하락했다고 FCM 보내기
            List<String> tokens = deliveryPage.stream()
                .map(Delivery::getChild)
                .map(Child::getToken)
                .filter(Objects::nonNull)
                .collect(
                    Collectors.toList());

        }
    }

    private void updateNoResponseChild(Child child) {
        // 마음온도 0.1도 하락
        child.setHeartTemperature(child.getHeartTemperature() - 0.1f);

        // available=true로 변경
        child.setAvailable(true);
    }

    private void updateNoResponseDelivery(Delivery delivery) {
        // isResponded=true로 변경
        delivery.setResponded(true);
    }
}
