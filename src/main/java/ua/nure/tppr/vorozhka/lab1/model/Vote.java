package ua.nure.tppr.vorozhka.lab1.model;

import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vote {

    private List<Alternative> indexedAlternative;

    private String voterName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vote vote = (Vote) o;

        return Objects.equals(voterName, vote.voterName);
    }

    @Override
    public int hashCode() {
        return voterName != null ? voterName.hashCode() : 0;
    }
}
