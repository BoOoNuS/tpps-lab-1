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
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    private int numericValue;

    private int markTypeId;

    @Override
    public String toString() {
        return value + ", " + numericValue;
    }
}
