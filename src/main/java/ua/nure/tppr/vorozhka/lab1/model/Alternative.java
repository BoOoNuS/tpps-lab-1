package ua.nure.tppr.vorozhka.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
@Table(name = "alternatives")
public class Alternative implements Comparable<Alternative> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "comparing_type_id", nullable = false)
    private ComparingType comparingType;

    @OneToMany(mappedBy = "alternative", cascade = CascadeType.ALL)
    private List<Vector> vectors;

    @Override
    public int compareTo(Alternative input) {
        int result = 0;
        Map<Integer, Vector> nameToCriteria = input.getVectors().stream()
                .collect(Collectors.toMap(Vector::getId, Function.identity()));

        for (Vector vectorToCompare : vectors) {
            result += vectorToCompare.compareTo(nameToCriteria.get(vectorToCompare.getId()));
        }
        return result;
    }

    @Override
    public String toString() {
        return "\t\tAlternative { " + name + System.lineSeparator() + vectors + " };" + System.lineSeparator();
    }
}
