package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SpringForm;
import com.example.demo.service.SpringService;

@Controller
@RequestMapping("/tasks")
public class SpringController {

    @Autowired
    private SpringService service;

    // ① 取得（一覧表示）
    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "task/list";
    }

    // ② 登録画面の表示
    @GetMapping("/add")
    public String addForm(@ModelAttribute SpringForm springForm) {
        return "task/add";
    }

    // ② 登録の実行
    @PostMapping("/add")
    public String create(@Validated @ModelAttribute SpringForm springForm, BindingResult result) {
        checkDateRange(springForm, result);
        if (result.hasErrors()) return "task/add";
        
        service.save(springForm);
        return "redirect:/tasks";
    }

    // ③ 変更（更新）画面の表示
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        SpringForm springForm = service.findById(id);
        model.addAttribute("springForm", springForm);
        return "task/edit";
    }

    // ③ 変更（更新）の実行
    @PostMapping("/edit")
    public String update(@Validated @ModelAttribute SpringForm springForm, BindingResult result) {
        checkDateRange(springForm, result);
        if (result.hasErrors()) return "task/edit";
        
        service.save(springForm);
        return "redirect:/tasks";
    }
    
    // ④ 削除の実行
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/tasks";
    }

    private void checkDateRange(SpringForm form, BindingResult result) {
        if (form.getStartDate() != null && form.getEndDate() != null) {
            if (form.getEndDate().isBefore(form.getStartDate())) {
                result.rejectValue("endDate", "error.dateRange", "完了日は作成日以降の日付を入力してください");
            }
        }
    }
}