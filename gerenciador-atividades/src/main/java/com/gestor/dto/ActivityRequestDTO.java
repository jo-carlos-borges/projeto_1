package com.gestor.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityRequestDTO {
    private String title;
    private LocalDateTime startDate;
    private List<Long> peopleIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public List<Long> getPeopleIds() {
        return peopleIds;
    }

    public void setPeopleIds(List<Long> peopleIds) {
        this.peopleIds = peopleIds;
    }
}