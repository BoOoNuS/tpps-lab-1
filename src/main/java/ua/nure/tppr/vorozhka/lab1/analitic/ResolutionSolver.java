package ua.nure.tppr.vorozhka.lab1.analitic;

import org.springframework.stereotype.Component;
import ua.nure.tppr.vorozhka.lab1.model.Alternative;
import ua.nure.tppr.vorozhka.lab1.model.ComparingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
@Component
public class ResolutionSolver {

    public List<Alternative> prioritize(ComparingType comparingType) {
        if (comparingType == null) {
            return Collections.emptyList();
        }

        List<Alternative> alternatives = new ArrayList<>(comparingType.getAlternatives());
        Collections.sort(alternatives);

        return alternatives;
    }
}
