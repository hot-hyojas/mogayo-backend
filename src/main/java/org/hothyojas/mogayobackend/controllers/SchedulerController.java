package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.services.SchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("scheduler")
@RestController
public class SchedulerController {

    private final SchedulerService schedulerService;

    @GetMapping("/{intervalSeconds}")
    public void triggerScheduler(@PathVariable int intervalSeconds) {
        schedulerService.checkNoResponseChildren(intervalSeconds);
    }
}
