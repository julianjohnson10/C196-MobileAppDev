package com.example.mobileappdev.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termName;
    private LocalDate startDate;
    private LocalDate endDate;

    public Term(int termID, String termName, LocalDate startDate, LocalDate endDate) {
        this.termID = termID;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTermID() {
        return termID;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termID=" + termID +
                ", termName='" + termName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
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
}
