package ua.nure.tppr.vorozhka.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.tppr.vorozhka.lab1.model.Mark;

/**
 * Created on 28.11.2019.
 *
 * @author Stanislav Vorozhka
 */
public interface MarkRepository extends CrudRepository<Mark, Integer> {

    Mark getByValueAndMarkTypeId(String name, int markTypeId);
}
