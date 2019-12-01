package ua.nure.tppr.vorozhka.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.tppr.vorozhka.lab1.model.ComparingType;
import ua.nure.tppr.vorozhka.lab1.model.Criterion;

import java.util.List;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
public interface CriterionRepository extends CrudRepository<Criterion, Integer> {

    List<Criterion> getByComparingType(ComparingType comparingType);
}
