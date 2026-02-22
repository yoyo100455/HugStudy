package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SpringEntity;
import com.example.demo.form.SpringForm;
import com.example.demo.repository.SpringRepository;

@Service
@Transactional
public class SpringService {

    @Autowired
    private SpringRepository repository;

    public List<SpringEntity> findAll() {
        return repository.findAll();
    }

    public SpringForm findById(Integer id) {
        SpringEntity entity = repository.findById(id).orElse(null);
        if (entity == null) return null;
        
        SpringForm form = new SpringForm();
        form.setTaskId(entity.getTaskId());
        form.setTaskName(entity.getTaskName());
        form.setTaskStatus(entity.getTaskStatus());
        form.setStartDate(entity.getStartDate());
        form.setEndDate(entity.getEndDate());
        return form;
    }

    public void save(SpringForm form) {
        SpringEntity entity = new SpringEntity();
        
        entity.setTaskId(form.getTaskId());
        entity.setTaskName(form.getTaskName());
        entity.setTaskStatus(form.getTaskStatus());
        entity.setStartDate(form.getStartDate());
        entity.setEndDate(form.getEndDate());
        
        repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}