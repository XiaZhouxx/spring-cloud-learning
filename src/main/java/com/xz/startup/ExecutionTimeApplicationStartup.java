package com.xz.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.function.Supplier;

/**
 * @author xz
 * @since 2024/7/5 14:41
 */
public class ExecutionTimeApplicationStartup implements ApplicationStartup {
    Logger log = LoggerFactory.getLogger(ExecutionTimeApplicationStartup.class);
    @Override
    public StartupStep start(String name) {
        return new ExecutionTimeStartup(name);
    }

    class ExecutionTimeStartup implements StartupStep {
        private String name;

        private Instant time;

        public ExecutionTimeStartup(String name) {
            this.time = Instant.now();
            this.name = name;
            log.info("execution start: {}, startTime: {}", name, time.toString());
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public long getId() {
            return 0;
        }

        @Override
        public Long getParentId() {
            return null;
        }

        @Override
        public StartupStep tag(String key, String value) {
            return this;
        }

        @Override
        public StartupStep tag(String key, Supplier<String> value) {
            return this;
        }

        @Override
        public Tags getTags() {
            return null;
        }

        @Override
        public void end() {
            Instant cur = Instant.now();
            log.info("execution end: {}, endTime: {}, totalTime: {}", name, cur.toString(), (cur.toEpochMilli() - time.toEpochMilli()) / 1000.0);
        }
    }
}
