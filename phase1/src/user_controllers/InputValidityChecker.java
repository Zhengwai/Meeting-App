package user_controllers;

import ScheduleSystem.EventPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A class that asks input from user and makes sure that the input in valid.
 */
public class InputValidityChecker {
    private Scanner scanner = new Scanner(System.in);
    private EventPresenter ep = new EventPresenter();

    /**
     * Checks if an input from user is a valid year. If not, keeps asking user until a valid year is given.
     * @param newInput the input user first given.
     * @return a valid input asked from the user.
     */
   public int isValidYearInput(int newInput) {
        while (newInput <= 1900) {
            ep.notValidInput();
            newInput = scanner.nextInt();
        }

        return newInput;
    }
    /**
     * Checks if an input from user is a valid month. If not, keeps asking user until a valid input is given.
     * @param newInput the input user first given.
     * @return a valid input asked from the user.
     */
    public int isValidMonthInput(int newInput) {
        while (newInput < 1 | newInput > 12){
            ep.notValidInput();
            newInput = scanner.nextInt();
        }


        return newInput;
    }
    /**
     * Checks if an input from user is a valid day for the month and year. If not, keeps asking user until a valid input is given.
     * @param newInput the input user first given.
     * @param month the month to check.
     * @param year the year to check.
     * @return a valid input asked from the user.
     */
    public int isValidDayInput(int newInput, int year, int month) {
        if (month == 1|month ==3|month == 5|month == 7|month==8|month==10|month==12) {
            while (newInput < 1 | newInput > 31) {
                ep.notValidInput();
                newInput = scanner.nextInt();
            }
        } else if (month != 2){
            while (newInput < 1 | newInput > 30) {
                ep.notValidInput();
                newInput = scanner.nextInt();
            }

        } else if (year % 4 != 0) {
            while (newInput < 1 | newInput > 28) {
                ep.notValidInput();
                newInput = scanner.nextInt();
            }
        } else {
            while (newInput < 1 | newInput > 29) {
                ep.notValidInput();
                newInput = scanner.nextInt();
            }
        }


        return newInput;
    }
    /**
     * Checks if an input from user is a valid hour. If not, keeps asking user until a valid input is given.
     * @param newInput the input user first given.
     * @return a valid input asked from the user.
     */
    public int isValidHourInput(int newInput) {
        while (newInput < 9 | newInput > 16){
            ep.notValidInput();
            newInput = scanner.nextInt();
        }


        return newInput;
    }
    /**
     * Checks if an input from user is a valid capacity. If not, keeps asking user until a valid input is given.
     * @param newInput the input user first given.
     * @return a valid input asked from the user.
     */
    public int isValidCapacityInput(int newInput) {
        while (newInput <= 1){
            ep.notValidInput();
            newInput = scanner.nextInt();
        }


        return newInput;
    }
    /**
     * Checks if an input from user is valid. If not, keeps asking user until a valid input is given.
     * Valid inputs are provided in the parameter.
     * @param validInputs an ArrayList of all the valid inputs.
     * @param newInput the input user first given.
     * @return a valid input asked from the user.
     */
    public String isValidInput(ArrayList<String> validInputs, String newInput) {
        String checkInput = newInput.toUpperCase();

        ArrayList<String> capitalInputs = new ArrayList<>();
        for(String i: validInputs){
            capitalInputs.add(i.toUpperCase());
        }

        while (!capitalInputs.contains(checkInput)) {
            ep.notValidInput();
            checkInput = scanner.nextLine();
        }

        return checkInput.toLowerCase();
    }

    /**
     * Returns an ArrayList form of an array of Strings.
     * @param allValid the array of Strings.
     * @return an ArrayList form of <code>allValid</code>s
     */
    public ArrayList<String> validList(String[] allValid) {
        return new ArrayList<>(Arrays.asList(allValid));
    }

}
