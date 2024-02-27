package com.xcoder.controller;

import com.xcoder.model.MyRecord;
import com.xcoder.service.HighLoadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HighLoadController {
    private final HighLoadService highLoadService;

    public HighLoadController(HighLoadService highLoadService) {
        this.highLoadService = highLoadService;
    }


    @GetMapping("/{id}")
    public MyRecord getOrCreate(@PathVariable("id") int recordId) {
        return highLoadService.getOrCreateRecord(recordId);
    }

    @PutMapping("/{id}")
    public MyRecord createOrUpdate(@PathVariable("id") int recordId) {
        return highLoadService.createOrUpdate(recordId);
    }

    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable("id") int recordId) {
        highLoadService.delete(recordId);
        return "Record removed";
    }
}
