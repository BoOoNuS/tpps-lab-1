package ua.nure.tppr.vorozhka.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.tppr.vorozhka.lab1.model.ComparingType;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
public interface ComparingTypeRepository extends CrudRepository<ComparingType, Integer> {

    ComparingType getByName(String name);
}
