package com.example.form;

import jakarta.validation.constraints.Pattern;

public class FindForm {
    private String name;
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "入社日はyyyy-MM-ddの形式で入力してください。（例：1996-02-13）")
    private String startDate = "0001-01-01";
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "入社日はyyyy-MM-ddの形式で入力してください。（例：1996-02-13）")
    private String endDate = "9999-12-31";
    private String dependentsCounts;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getDependentsCounts() {
        return dependentsCounts;
    }
    public void setDependentsCounts(String dependentsCounts) {
        this.dependentsCounts = dependentsCounts;
    }
    public FindForm() {
    }
    public FindForm(String name, String startDate, String endDate, String dependentsCounts) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dependentsCounts = dependentsCounts;
    }
    @Override
    public String toString() {
        return "Findform [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", dependentsCounts="
                + dependentsCounts + "]";
    }

    
}
