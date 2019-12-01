package ua.nure.tppr.vorozhka.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "criteria")
public class Criterion implements Comparable<Criterion> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int weight;

    @ManyToOne
    @JoinColumn(name = "mark_type_id", nullable = false)
    private MarkType markType;

    @ManyToOne
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "vectors",
            joinColumns = { @JoinColumn(name = "criteria_id") },
            inverseJoinColumns = { @JoinColumn(name = "alternative_id") }
    )
    private List<Alternative> alternatives;

    @Override
    public int compareTo(Criterion input) {
        Integer value = mark.getNumericValue();
        Integer inputValue = input.getMark().getNumericValue();
        int result = value.compareTo(inputValue);

        if (result > 0) {
            return weight * -1;
        } else if (result < 0) {
            return input.getWeight();
        }

        return 0;
    }

    @Override
    public String toString() {
        return "\t\t\t\tVector { " + name + ", " + weight + ", " + mark + ", " + markType + " };" + System.lineSeparator();
    }
}
