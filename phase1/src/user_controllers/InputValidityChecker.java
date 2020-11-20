package user_controllers;

import ScheduleSystem.EventPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputValidityChecker {
    private Scanner scanner = new Scanner(System.in);
    private EventPresenter ep = new EventPresenter();

   public int isValidYearInput(int newInput) {
        while (newInput <= 1900) {
            ep.notValidInput();
            newInput = scanner.nextInt();
        }

        return newInput;
    }

    public int isValidMonthInput(int newInput) {
        while (newInput < 1 | newInput > 12){
            ep.notValidInput();
            newInput = scanner.nextInt();
        }


        return newInput;
    }

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

    public int isValidHourInput(int newInput) {
        while (newInput < 9 | newInput > 16){
            ep.notValidInput();
            newInput = scanner.nextInt();
        }


        return newInput;
    }

    public int isValidCapacityInput(int newInput) {
        while (newInput <= 1){
            ep.notValidInput();
            newInput = scanner.nextInt();
        }


        return newInput;
    }

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

    public ArrayList<String> validList(String[] allValid) {
        return new ArrayList<>(Arrays.asList(allValid));
    }

}
