package io.javabrains.resume_portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String college;
    private String qualification;
    @DateTimeFormat(pattern =  "yyyy-mm-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern =  "yyyy-mm-dd")
    private LocalDate endDate;
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String formattedStartDate(){
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }

    public String formattedEndDate(){
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
}
