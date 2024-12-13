package com.starshop.services;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskService {

    private final TaskScheduler taskScheduler;

    public TaskService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.initialize();
        this.taskScheduler = scheduler;
    }

    public ScheduledFuture<?> executeWithDelay(Runnable task, long delayInMillis) {
        Instant startTime = Instant.now().plusMillis(delayInMillis);
        return taskScheduler.schedule(task, startTime);
    }
}
