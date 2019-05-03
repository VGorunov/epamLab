package by.gsu.epamlab.model.beans;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Task {

    private int taskId;
    private String description;
    private LocalDate dateCompletion;
    private String fileName;
    private String actualFileName;

    public Task() {
    }

    public Task(int taskId, String description, LocalDate dateCompletion, String fileName) {
        this.taskId = taskId;
        this.description = description;
        this.dateCompletion = dateCompletion;
        this.fileName = fileName;
        this.actualFileName = "";
    }

    public Task(int taskId, String description, LocalDate dateCompletion, String fileName, String actualFileName) {
        this(taskId, description, dateCompletion, fileName);
        this.actualFileName = actualFileName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(LocalDate dateCompletion) {
        this.dateCompletion = dateCompletion;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getActualFileName() {
        return actualFileName;
    }

    public void setActualFileName(String actualFileName) {
        this.actualFileName = actualFileName;
    }
}
