package io.javabrains.resume_portal.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String designation;
    private String company;
    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    private LocalDate endDate;
    private boolean isCurrentJob;
    @ElementCollection(targetClass = String.class)
    private List<String> responsibilities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String formattedStartDate(){
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }

    public String formattedEndDate(){
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", company='" + company + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
