package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Certificate")
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "certificate_id")
    private String id;
    private String name;
    private String organization;
    @Column(name = "issue_date")
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public Certificate() {
    }

    public Certificate(String id, String name, String organization, LocalDate issueDate) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.issueDate = issueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }
}