package ua.nure.tppr.vorozhka.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ua.nure.tppr.vorozhka.lab1.analitic.ResolutionSolver;
import ua.nure.tppr.vorozhka.lab1.model.Alternative;
import ua.nure.tppr.vorozhka.lab1.model.ComparingType;
import ua.nure.tppr.vorozhka.lab1.model.Criterion;
import ua.nure.tppr.vorozhka.lab1.model.Mark;
import ua.nure.tppr.vorozhka.lab1.model.MarkType;
import ua.nure.tppr.vorozhka.lab1.model.ValueType;
import ua.nure.tppr.vorozhka.lab1.model.Vector;
import ua.nure.tppr.vorozhka.lab1.model.Vote;
import ua.nure.tppr.vorozhka.lab1.repository.AlternativeRepository;
import ua.nure.tppr.vorozhka.lab1.repository.ComparingTypeRepository;
import ua.nure.tppr.vorozhka.lab1.repository.CriterionRepository;
import ua.nure.tppr.vorozhka.lab1.repository.MarkRepository;
import ua.nure.tppr.vorozhka.lab1.repository.MarkTypeRepository;

@SpringBootApplication
public class Lab1Application implements CommandLineRunner {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Pattern COMMAND_PARSE_PATTERN = Pattern.compile("(-\\w+)\\s?('(.+)(?=')|\\w+)?");

    @Autowired
    private ComparingTypeRepository comparingTypeRepository;

    @Autowired
    private AlternativeRepository alternativeRepository;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private MarkTypeRepository markTypeRepository;

    @Autowired
    private CriterionRepository criterionRepository;

    @Autowired
    private ResolutionSolver resolutionSolver;

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Use -help command to see all available commands");
        boolean isRunning = true;
        while (isRunning) {
            try {
                String commandWithArgument = SCANNER.nextLine();
                Matcher commandMatcher = COMMAND_PARSE_PATTERN.matcher(commandWithArgument);
                String command = "";
                String argument = "";


                if (commandMatcher.find()) {
                    command = commandMatcher.group(1);
                    argument = commandMatcher.group(3);
                }

                switch (command) {
                    case "-help": {
                        System.out.println("*** HELP ***");
                        System.out.println("-addComparingType {comparingTypeName} - adds comparing type with name");
                        System.out.println("-getComparingTypes - gets names of all available comparing types");
                        System.out.println("-addMarkType - adds mark type");
                        System.out.println("-getMarkTypes - gets all mark types");
                        System.out.println("-addMark {markTypeName} - adds mark to mark type");
                        System.out.println("-getMarks {markTypeName} - gets marks by mark type");
                        System.out.println("-addCriterion {comparingTypeName} - adds criterion to comparing type group");
                        System.out.println("-getCriteria {comparingTypeName} - gets criteria by comparing type");
                        System.out.println("-addVector {comparingTypeName} - adds vector to comparing type group");
                        System.out.println("-prioritize {comparingTypeName} - prioritize vectors in comparing type by declared criteria");
                        System.out.println("-vote {comparingTypeName} - vote for alternatives in comparing type");
                        System.out.println("-help - shows all available commands");
                        System.out.println("-exit - close application");
                        break;
                    }
                    case "-getComparingTypes": {
                        System.out.println(comparingTypeRepository.findAll());
                        break;
                    }
                    case "-addComparingType": {
                        comparingTypeRepository.save(ComparingType.builder().name(argument).build());
                        System.out.println("Comparing type was added successfully");
                        break;
                    }
                    case "-addMarkType": {
                        System.out.print("\t\tPlease enter mark type name, it should be unique - ");
                        String markTypeName = SCANNER.nextLine();
                        System.out.print("\t\tPlease enter mark unit - ");
                        String markUnit = SCANNER.nextLine();
                        System.out.print("\t\tPlease choose one of value types: " + Arrays.toString(ValueType.values()) + " - ");
                        ValueType valueType = ValueType.valueOf(SCANNER.nextLine());
                        MarkType newMarkType = MarkType.builder().name(markTypeName).unit(markUnit).valueType(valueType).build();
                        markTypeRepository.save(newMarkType);
                        System.out.println("Mark type was added successfully");
                        break;
                    }
                    case "-getMarkTypes": {
                        System.out.println(((List<MarkType>) markTypeRepository.findAll()).stream()
                                .map(markType -> "\t\t{ " + markType.getName() + ", " + markType.getUnit() + ", " + markType.getValueType() + " };\n")
                                .collect(Collectors.toList()));
                        break;
                    }
                    case "-addMark": {
                        MarkType markType = markTypeRepository.getByName(argument);
                        System.out.print("\t\tPlease enter mark value, it should be unique - ");
                        String markValue = SCANNER.nextLine();
                        System.out.print("\t\tPlease enter mark numeric value - ");
                        int markNumericValue = Integer.parseInt(SCANNER.nextLine());
                        Mark newMark = Mark.builder().markTypeId(markType.getId()).value(markValue).numericValue(markNumericValue).build();
                        markRepository.save(newMark);
                        System.out.println("Mark was added successfully");
                        break;
                    }
                    case "-getMarks": {
                        MarkType markType = markTypeRepository.getByName(argument);
                        System.out.println(markRepository.getByMarkTypeId(markType.getId()).stream()
                                .map(mark -> "\t\t{ " + mark + " };\n").collect(Collectors.toList()));
                        break;
                    }
                    case "-addCriterion": {
                        ComparingType comparingType = comparingTypeRepository.getByName(argument);
                        System.out.print("\t\tPlease enter criterion name, it should be unique - ");
                        String criterionName = SCANNER.nextLine();
                        System.out.print("\t\tPlease enter criterion numeric weight - ");
                        int criterionWeight = Integer.parseInt(SCANNER.nextLine());
                        System.out.println("\t\tPlease choose one of mark types described below");
                        System.out.print("\t\t");
                        System.out.println(((List<MarkType>) markTypeRepository.findAll()).stream()
                                .map(MarkType::getName)
                                .collect(Collectors.toList()));
                        System.out.print("\t\t");
                        String markTypeName = SCANNER.nextLine();
                        MarkType markType = markTypeRepository.getByName(markTypeName);
                        Criterion criterion = Criterion.builder().comparingType(comparingType)
                                .name(criterionName)
                                .weight(criterionWeight)
                                .markType(markType).build();
                        criterionRepository.save(criterion);
                        System.out.println("Criterion was added successfully");
                        break;
                    }
                    case "-getCriteria": {
                        ComparingType comparingType = comparingTypeRepository.getByName(argument);
                        System.out.println(criterionRepository.getByComparingType(comparingType));
                        break;
                    }
                    case "-addVector": {
                        ComparingType comparingType = comparingTypeRepository.getByName(argument);
                        List<Criterion> criteria = criterionRepository.getByComparingType(comparingType);

                        if (CollectionUtils.isEmpty(criteria)) {
                            System.out.println("No criteria found, please add criteria, first");
                            break;
                        }

                        System.out.print("\t\tPlease enter alternative name, it should be unique - ");
                        String alternativeName = SCANNER.nextLine();
                        List<Vector> vectors = new ArrayList<>();
                        Alternative alternative = Alternative.builder()
                                .name(alternativeName)
                                .vectors(vectors)
                                .comparingType(comparingType)
                                .build();
                        System.out.printf("\t\tPlease mark %s criteria described below %s", criteria.size(), System.lineSeparator());

                        for (Criterion criterion : criteria) {
                            List<Mark> marks = markRepository.getByMarkTypeId(criterion.getMarkType().getId());
                            System.out.println("\t\tPlease choose one of mark described below");
                            marks.stream().map(mark -> "\t\t\t\t" + mark.getValue() + ", " + mark.getNumericValue())
                                    .forEach(System.out::println);
                            System.out.print("\t\t");
                            String markValue = SCANNER.nextLine();
                            Mark foundMark = markRepository.getByValueAndMarkTypeId(markValue, criterion.getMarkType().getId());

                            Vector newVector = Vector.builder()
                                    .alternative(alternative)
                                    .criterion(criterion)
                                    .mark(foundMark).build();

                            vectors.add(newVector);
                        }

                        alternativeRepository.save(alternative);
                        System.out.println("Vector was added successfully");
                        break;
                    }
                    case "-prioritize": {
                        System.out.println(resolutionSolver.prioritize(comparingTypeRepository.getByName(argument)));
                        break;
                    }
                    case "-vote": {
                        ComparingType comparingType = comparingTypeRepository.getByName(argument);
                        List<Alternative> alternatives = comparingType.getAlternatives();
                        System.out.println("Count of alternatives: " + alternatives.size());
                        System.out.println("Alternatives to vote: " + alternatives);
                        boolean isContinue = true;
                        List<Vote> votes = new ArrayList<>();

                        while (isContinue) {
                            System.out.print("\t\tPlease enter voter name, it should be unique - ");
                            String voterName = SCANNER.nextLine();
                            Alternative[] indexedAlternatives = new Alternative[alternatives.size()];

                            for (Alternative alternative : alternatives) {
                                System.out.print(
                                    MessageFormat.format(
                                        "\t\tPlease enter the score for alternative - {0}, from 0 to {1}." +
                                            " Where 0 is the highest score and {2} is the lowest. Each score should be unique. Score: ",
                                        alternative.getName(), alternatives.size() - 1, alternatives.size() - 1));
                                int score = Integer.parseInt(SCANNER.nextLine());
                                indexedAlternatives[score] = alternative;
                            }
                            Vote vote =
                                Vote.builder().voterName(voterName).indexedAlternative(Arrays.asList(indexedAlternatives)).build();
                            votes.add(vote);
                            System.out.print("\t\tWould you add one more voter? (true/false): ");
                            isContinue = Boolean.parseBoolean(SCANNER.nextLine());
                            System.out.println("===================================");
                        }
                        System.out.print("\t\tPlease choose decision making strategy (Conders/Copland): ");
                        String decisionMakingStrategy = SCANNER.nextLine();
                        if ("Conders".equals(decisionMakingStrategy.trim())) {
                            Alternative[] result = resolutionSolver.makeDecisionConders(votes);
                            System.out.print("Result sorted from the highest to the lowest: ");
                            System.out.println(Arrays.toString(result));
                        } else if ("Copland".equals(decisionMakingStrategy.trim())) {
                            Alternative[] result = resolutionSolver.makeDecisionCopland(votes);
                            System.out.print("Result sorted from the highest to the lowest: ");
                            System.out.println(Arrays.toString(result));
                        } else {
                            System.out.println("Entered strategy not found!");
                        }
                        break;
                    }
                    case "-exit": {
                        isRunning = false;
                        System.out.println("Bye!");
                        break;
                    }
                    default: {
                        System.out.println("Wrong command! Please use -help to see all available commands");
                    }
                }
            } catch (Throwable t) {
                System.out.println("Oops! " + t.getMessage() + ". Try again!");
            }
        }
    }
}
