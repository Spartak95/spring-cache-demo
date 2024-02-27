package com.xcoder.service;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import com.xcoder.model.MyRecord;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "recordsCache") // երբ այստեղ նշվում է cacheNames, ապա կարիք
public class HighLoadService {            // չկա @Cacheable աննոտացիայում cacheNames ցույց տալ

    @Cacheable(cacheNames = "recordsCache", key = "#recordId")
    public MyRecord getOrCreateRecord(int recordId) {
        try {
            TimeUnit.SECONDS.sleep(3L);

            return new MyRecord(recordId, LocalTime.now());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return null;
    }

    @CachePut(cacheNames = "recordsCache", key = "#recordId")
    public MyRecord createOrUpdate(int recordId) {
        return new MyRecord(recordId, LocalTime.now());
    }

    @CacheEvict(cacheNames = "recordsCache", key = "#recordId")
    public void delete(int recordId) {
        // remove the record from cache
    }

    @CacheEvict(allEntries = true)
    public void deleteAll() {
        // remove all record from cache
    }
}
