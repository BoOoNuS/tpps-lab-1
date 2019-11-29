package ua.nure.tppr.vorozhka.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.tppr.vorozhka.lab1.model.Criteria;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
public interface CriteriaRepositpry extends CrudRepository<Criteria, Integer> {

    Criteria getByName(String name);
}
