package ua.nure.tppr.vorozhka.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
@Getter
@AllArgsConstructor
public enum ValueType {

    QUALITATIVE("Qualitative"), QUANTITATIVE("Quantitative");

    private String value;
}
