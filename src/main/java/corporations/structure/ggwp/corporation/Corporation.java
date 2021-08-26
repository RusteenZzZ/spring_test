package corporations.structure.ggwp.corporation;

import corporations.structure.ggwp.field.Field;
import corporations.structure.ggwp.worker.Worker;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Corporation {
    @Id
    @SequenceGenerator(
            name = "corporation_sequence",
            sequenceName = "corporation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "corporation_sequence"
    )
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "covered_fields",
            joinColumns = @JoinColumn(name = "corporation_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private Set<Field> coveredFields = new HashSet<>();

    @OneToMany(mappedBy = "corporation", cascade = CascadeType.ALL)
    private Set<Worker> workers = new HashSet<>();

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    public Corporation() {
    }

    public Corporation(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Set<Field> getCoveredFields() {
        return coveredFields;
    }

    public void setCoveredFields(Set<Field> coveredFields) {
        this.coveredFields = coveredFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public void assignField(Field field) {
        coveredFields.add(field);
    }
}
