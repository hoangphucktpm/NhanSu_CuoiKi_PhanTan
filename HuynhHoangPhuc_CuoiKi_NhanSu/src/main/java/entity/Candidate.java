package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Candidate")
@NamedQueries({
        @NamedQuery(name = "Candidate.listCadidatesByCompanies",
                query = "SELECT c, COUNT(e.companyName) FROM Candidate c JOIN c.experiences e GROUP BY c"),
        @NamedQuery(name = "Candidate.listCadidatesWithLongestWorking",
                query = "SELECT c, SUM(e.toDate - e.fromDate) FROM Candidate c JOIN c.experiences e GROUP BY c"),
        @NamedQuery(name = "Candidate.listCadidatesAndCertificates",
                query = "SELECT c, COUNT(cer) FROM Candidate c JOIN c.certificates cer GROUP BY c")
})

public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "candidate_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String gender;
    private String email;
    private String phone;
    private String description;


    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "candidate")
    private Set<Certificate> certificates;

    @OneToMany(mappedBy = "candidate")
    private Set<Experience> experiences;

    public Candidate() {
    }

    public Candidate(String id, String fullName, int yearOfBirth, String gender, String email, String phone, String description) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}