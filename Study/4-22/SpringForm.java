package com.example.demo.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SpringForm {

    private Integer taskId;

    @NotBlank(message = "タスク名は必須です")
    @Size(max = 20, message = "タスク名は20文字以内で入力してください")
    private String taskName;

    @NotBlank(message = "状況は必須です")
    private String taskStatus;

    @NotNull(message = "作成日は必須です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "完了日は必須です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public boolean isNewTask() {
        return taskId == null;
    }
}