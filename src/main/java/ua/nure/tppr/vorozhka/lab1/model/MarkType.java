package ua.nure.tppr.vorozhka.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Table(name = "mark_types")
public class MarkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String unit;

    @Enumerated(value = EnumType.STRING)
    private ValueType valueType;

    @Override
    public String toString() {
        return unit + ", " + valueType.getValue();
    }
}
