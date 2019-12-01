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
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int weight;

    @ManyToOne
    @JoinColumn(name = "mark_type_id", nullable = false)
    private MarkType markType;

    @ManyToOne
    @JoinColumn(name = "comparing_type_id", nullable = false)
    private ComparingType comparingType;

    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL)
    private List<Vector> vectors;

    @Override
    public String toString() {
        return "\t\tCriterion { " + name + ", " + weight + ", " + markType + " };" + System.lineSeparator();
    }
}
