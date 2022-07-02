package BaseMaxNoRepeats;

import java.util.*;

/**
 * Class for checking two cases of based number situations.
 * Case A: Given two numbers uses the first as a base and the
 * second as a max. From this finds the longest run of numbers in the
 * given base up to the max which all contain repeated digits.
 * Case B: Given two bases finds the first number which has repeated
 * digits in both bases.
 *
 * @author Matthew Jennings
 */
public class App {

    static boolean caseB;
    static String input;

    static int firstNumber = -1;
    static int secondNumber = -1;

    static boolean error = false;

    /**
     * Main method, takes input from stdin and prints
     * results to stdout.
     *
     * Input format: "A/B/Q value value"
     *
     * A for case A. B for case B. Q to exit.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = "";

            parseInput(scanner.nextLine());

            if (error) {
                error = false;
                continue;
            }

            if (!caseB) {
                perfCaseA();
            } else {
                perfCaseB();
            }
        }
    }

    /**
     * Parses a given input String and from it assigns values
     * to static variables. Performs input validation.
     *
     * @param input String to parse.
     */
    public static void parseInput(String input) {

        Scanner inscan = new Scanner(input);

        String setcase = inscan.next();

        if (setcase.length() != 1) {

            System.err.println("Bad line:  " + input);
            error = true;
            inscan.close();
            return;
        }

        if (setcase.charAt(0) == 'A') {
            caseB = false;
        } else if (setcase.charAt(0) == 'B') {
            caseB = true;
        } else {
            System.err.println("Bad line:  " + input);
            error = true;
            inscan.close();
            return;
        }

        try {
            firstNumber = Integer.parseInt(inscan.next());
            secondNumber = Integer.parseInt(inscan.next());
        } catch (Exception e) {
            System.err.println("Bad line:  " + input);
            error = true;
            inscan.close();
            return;
        }

        if (inscan.hasNext()) {
            System.err.println("Bad line:  " + input);
            error = true;
            inscan.close();
            return;
        } else {
            inscan.close();
            return;
        }

    }

    /**
     * Performs the check for case A. Prints result to stdout.
     */
    static void perfCaseA() {
        BasedNumber number = new BasedNumber(0, firstNumber);
        int max = secondNumber;
        int highest = -1;
        int highestrun = -1;
        int candidate = -1;
        int run = 0;

        int i = 0;
        while (i < max) {
            number.addOne();
            i++;
            if (number.hasRepeats()) {
                candidate = i;
                run = 0;

                while (number.hasRepeats() && i < max) {
                    number.addOne();
                    i++;
                    run++;
                }
                if (run > highestrun) {
                    highest = candidate;
                    highestrun = run;
                }
            }
        }
        if (highest != -1 && highestrun != -1) {
            System.out.println(highest + " " + highestrun);
        } else {
            System.out.println("No solution exists");
            return;
        }
        return;
    }

    /**
     * Performs the check for case B. Prints results to stdout.
     */
    static void perfCaseB() {
        BasedNumber first = new BasedNumber(0, firstNumber);
        BasedNumber second = new BasedNumber(0, secondNumber);

        long maxA = first.maxValue();
        long maxB = second.maxValue();

        long max = maxA < maxB ? maxA : maxB;

        for (int i = 1; i < max; i++) {
            first.addOne();
            second.addOne();
            if (first.hasRepeats() && second.hasRepeats()) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("No number exists");
        return;
    }
}