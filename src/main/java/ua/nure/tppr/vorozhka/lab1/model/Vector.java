package ua.nure.tppr.vorozhka.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created on 01.12.2019.
 *
 * @author Stanislav Vorozhka
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vectors")
public class Vector implements Comparable<Vector> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "criterion_id", nullable = false)
    private Criterion criterion;

    @ManyToOne
    @JoinColumn(name = "alternative_id", nullable = false)
    private Alternative alternative;

    @Override
    public int compareTo(Vector input) {
        Integer value = mark.getNumericValue();
        Integer inputValue = input.getMark().getNumericValue();
        int result = value.compareTo(inputValue);

        if (result > 0) {
            return criterion.getWeight() * -1;
        } else if (result < 0) {
            return input.getCriterion().getWeight();
        }

        return 0;
    }

    @Override
    public String toString() {
        return "\t\t\t\tVector { " + criterion.getName() + ", " + mark + ", " + criterion.getMarkType() + " };" + System.lineSeparator();
    }
}
