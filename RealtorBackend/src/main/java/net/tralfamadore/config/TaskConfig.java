package net.tralfamadore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class: TaskConfig
 * Created by billreh on 12/20/16.
 */
@Configuration
@EnableScheduling
public class TaskConfig implements SchedulingConfigurer {
    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(5);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }
}
