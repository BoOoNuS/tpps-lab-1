package ua.nure.tppr.vorozhka.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.nure.tppr.vorozhka.lab1.analitic.ResolutionSolver;
import ua.nure.tppr.vorozhka.lab1.repository.ComparingTypeRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class Lab1Application implements CommandLineRunner {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Pattern COMMAND_PARSE_PATTERN = Pattern.compile("(-\\w+)\\s?('(.+)(?=')|\\w+)?");

    @Autowired
    private ComparingTypeRepository comparingTypeRepository;

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
                    System.out.println("-getComparingTypes - get names of all available comparing types");
                    System.out.println("-prioritize {comparingTypeName} - prioritize vectors in comparing type by declared criteria");
                    System.out.println("-help - shows all available commands");
                    System.out.println("-exit - close application");
                    break;
                }
                case "-getComparingTypes": {
                    System.out.println(comparingTypeRepository.findAll());
                    break;
                }
                case "-prioritize" : {
                    System.out.println(resolutionSolver.prioritize(comparingTypeRepository.getByName(argument)));
                    break;
                }
                case "-exit" : {
                    isRunning = false;
                    System.out.println("Bye!");
                    break;
                }
                default : {
                    System.out.println("Wrong command! Please use -help to see all available commands");
                }
            }
        }
    }
}
