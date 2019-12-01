package ua.nure.tppr.vorozhka.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import ua.nure.tppr.vorozhka.lab1.analitic.ResolutionSolver;
import ua.nure.tppr.vorozhka.lab1.model.*;
import ua.nure.tppr.vorozhka.lab1.repository.AlternativeRepository;
import ua.nure.tppr.vorozhka.lab1.repository.ComparingTypeRepository;
import ua.nure.tppr.vorozhka.lab1.repository.MarkRepository;
import ua.nure.tppr.vorozhka.lab1.repository.MarkTypeRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                        System.out.println("-getComparingTypes - gets names of all available comparing types");
                        System.out.println("-addComparingType {comparingTypeName} - adds comparing type with name");
                        System.out.println("-addMarkType - adds mark type");
                        System.out.println("-addMark {markTypeName} - adds mark to mark type");
                        System.out.println("-addVector {comparingTypeName} - adds vector to comparing type group");
                        System.out.println("-prioritize {comparingTypeName} - prioritize vectors in comparing type by declared criteria");
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
                    case "-addMark": {
                        MarkType markType = markTypeRepository.getByName(argument);
                        System.out.print("\t\tPlease enter mark value, it should be unique - ");
                        String markValue = SCANNER.nextLine();
                        System.out.print("\t\tPlease enter mark numeric value - ");
                        int markNumericValue = Integer.valueOf(SCANNER.nextLine());
                        Mark newMark = Mark.builder().markTypeId(markType.getId()).value(markValue).numericValue(markNumericValue).build();
                        markRepository.save(newMark);
                        System.out.println("Mark was added successfully");
                    }
                    case "-addVector": {
                        ComparingType comparingType = comparingTypeRepository.getByName(argument);
                        List<Alternative> alternatives = comparingType.getAlternatives();
                        List<Criterion> criteria = alternatives.stream().findAny()
                                .orElseThrow(() -> new RuntimeException("Oops! Something went wrong in our calculation"))
                                .getCriteria();

                        if (CollectionUtils.isEmpty(alternatives) || CollectionUtils.isEmpty(criteria)) {
                            System.out.println("No criteria found, please add criteria, first");
                            break;
                        }

                        System.out.print("\t\tPlease enter alternative name, it should be unique - ");
                        String alternativeName = SCANNER.nextLine();
                        List<Criterion> newVectorCriteria = new ArrayList<>();
                        Alternative alternative = Alternative.builder()
                                .name(alternativeName)
                                .criteria(newVectorCriteria)
                                .comparingType(comparingType)
                                .build();
                        System.out.printf("\t\tPlease mark %s criteria described below %s", criteria.size(), System.lineSeparator());

                        for (Criterion criterion : criteria) {
                            String criterionName = criterion.getName();

                            List<Mark> marks = markRepository.getByMarkTypeId(criterion.getMarkType().getId());
                            System.out.println("\t\tPlease choose one of mark described below");
                            marks.stream().map(mark -> "\t\t\t\t" + mark.getValue() + ", " + mark.getNumericValue())
                                    .forEach(System.out::println);
                            System.out.print("\t\t");
                            String markValue = SCANNER.nextLine();
                            Mark foundMark = markRepository.getByValueAndMarkTypeId(markValue, criterion.getMarkType().getId());

                            Criterion newVectorCriterion = Criterion.builder().name(criterionName)
                                    .weight(criterion.getWeight())
                                    .markType(criterion.getMarkType())
                                    .alternatives(Collections.singletonList(alternative))
                                    .mark(foundMark).build();
                            newVectorCriteria.add(newVectorCriterion);
                        }

                        alternativeRepository.save(alternative);
                        System.out.println("Vector was added successfully");
                        break;
                    }
                    case "-prioritize": {
                        System.out.println(resolutionSolver.prioritize(comparingTypeRepository.getByName(argument)));
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
