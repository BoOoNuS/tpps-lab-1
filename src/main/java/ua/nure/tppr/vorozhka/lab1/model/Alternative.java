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

    @ManyToMany(mappedBy = "alternatives", cascade = CascadeType.ALL)
    private List<Criterion> criteria;

    @Override
    public int compareTo(Alternative input) {
        int result = 0;
        Map<String, Criterion> nameToCriteria = input.getCriteria().stream()
                .collect(Collectors.toMap(Criterion::getName, Function.identity()));

        for (Criterion criteriaToCompare : criteria) {
            result += criteriaToCompare.compareTo(nameToCriteria.get(criteriaToCompare.getName()));
        }
        return result;
    }

    @Override
    public String toString() {
        return "\t\tAlternative { " + name + System.lineSeparator() + criteria + " };" + System.lineSeparator();
    }
}
