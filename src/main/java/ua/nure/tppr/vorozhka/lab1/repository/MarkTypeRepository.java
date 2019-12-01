package ua.nure.tppr.vorozhka.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.tppr.vorozhka.lab1.model.MarkType;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
public interface MarkTypeRepository extends CrudRepository<MarkType, Integer> {

    MarkType getByName(String name);
}
