package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Experience")
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    private String description;

    @Id
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Id
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public Experience() {
    }

    public Experience(String companyName, LocalDate fromDate, LocalDate toDate, String description) {
        this.companyName = companyName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "companyName='" + companyName + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", description='" + description + '\'' +
                '}';
    }
}