package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Position")
@NamedQueries({
        @NamedQuery(name = "Position.listPositions",
                query = "SELECT p FROM Position p WHERE p.name LIKE :name AND p.salary >= :salaryFrom AND p.salary <= :salaryTo"),
        @NamedQuery(name = "Position.listYearsOfExperienceByPosition",
                query = "SELECT p, COUNT(e) FROM Position p JOIN p.experiences e WHERE e.candidate.id = :candidateID GROUP BY p"),
        @NamedQuery(name = "Experience.listYearsOfExperienceByPosition",
                query = "SELECT e.position, COUNT(e) FROM Experience e WHERE e.candidate.id = :candidateID GROUP BY e.position")
})
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "position_id")
    private String id;
    private String name;
    private String description;
    private double salary;
    private int number;

    @OneToMany(mappedBy = "position")
    private Set<Candidate> candidates;

    @OneToMany(mappedBy = "position")
    private Set<Experience> experiences;

    public Position() {
    }

    public Position(String id, String name, String description, double salary, int number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.number = number;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", number=" + number +
                '}';
    }
}