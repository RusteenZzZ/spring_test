package corporations.structure.ggwp.field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corporations.structure.ggwp.corporation.Corporation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Field {
    @Id
    @SequenceGenerator(
            name = "field_sequence",
            sequenceName = "field_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "field_sequence"
    )
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "coveredFields")
    private Set<Corporation> relatedCorporations = new HashSet<>();

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    public Field() {
    }

    public Field(String name) {
        this.name = name;
    }

    public Set<Corporation> getRelatedCorporations() {
        return relatedCorporations;
    }

    public void setRelatedCorporations(Set<Corporation> relatedCorporations) {
        this.relatedCorporations = relatedCorporations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
