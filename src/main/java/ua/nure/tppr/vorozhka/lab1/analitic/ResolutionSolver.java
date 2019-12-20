package ua.nure.tppr.vorozhka.lab1.analitic;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

import ua.nure.tppr.vorozhka.lab1.model.Alternative;
import ua.nure.tppr.vorozhka.lab1.model.ComparingType;
import ua.nure.tppr.vorozhka.lab1.model.Vote;

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

    public Alternative[] makeDecisionConders(List<Vote> votes) {
        BinaryOperator<Integer> markFunction = (n, f) -> n - f;
        return makeDecision(votes, markFunction);
    }

    public Alternative[] makeDecisionCopland(List<Vote> votes) {
        BinaryOperator<Integer> markFunction = (n, f) -> n + 1 - 2 * f;
        return makeDecision(votes, markFunction);
    }

    private Alternative[] makeDecision(List<Vote> votes, BinaryOperator<Integer> markFunction) {
        for (Vote vote : votes) {
            int n = vote.getIndexedAlternative().size();

            for (int i = 0; i < n; i++) {
                int f = i + 1;
                Alternative alternative = vote.getIndexedAlternative().get(i);
                Integer mark = markFunction.apply(n, f);
                alternative.setVoteMark(alternative.getVoteMark() + mark);
            }
        }

        return votes.stream()
            .map(Vote::getIndexedAlternative)
            .flatMap(Collection::stream)
            .distinct()
            .sorted(Comparator.comparing(Alternative::getVoteMark).reversed())
            .toArray(Alternative[]::new);
    }
}
