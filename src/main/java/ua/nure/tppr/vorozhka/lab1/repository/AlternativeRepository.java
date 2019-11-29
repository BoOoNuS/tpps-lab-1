package ua.nure.tppr.vorozhka.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.tppr.vorozhka.lab1.model.Alternative;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
public interface AlternativeRepository extends CrudRepository<Alternative, Integer> {

    Alternative getByName(String name);
}
