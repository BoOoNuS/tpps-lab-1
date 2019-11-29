package ua.nure.tppr.vorozhka.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "comparing_types")
public class ComparingType {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "comparingType")
    private List<Alternative> alternatives;

    @Override
    public String toString() {
        return "Comparing type { name - " + name + System.lineSeparator() + alternatives + " };";
    }
}
