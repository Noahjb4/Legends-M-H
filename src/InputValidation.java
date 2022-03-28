/**
 * TicTacToe --- Class that has static methods that obtain valid user input
 * @author Noah Jean-Baptiste
 */

import java.util.Arrays;
import java.util.Scanner;

public class InputValidation {


    /**
     * validInt method checks and obtains user input that is an int
     * @param lBound lower bound for a valid int
     * @param uBound upper bound for a valid int
     * @return int from user input
     */

    public static int validInt(int lBound, int uBound){
        Scanner console = new Scanner(System.in);
        int num;

        while (true) {
            try {
                num = console.nextInt();
                if (num < lBound || num > uBound) {
                    throw new Exception("");
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number: ");
                console.nextLine();
            }
        }

        return num;
    }


    /**
     * validInt char checks and obtains user input that is an char
     * @return char from user input
     */
    public static char validChar(){
        Scanner console = new Scanner(System.in);
        char c;

        while (true) {
            try {
                c = console.next().charAt(0);
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid input: ");
                console.nextLine();
            }
        }

        return c;
    }

    /**
     * validInput method checks if a user input is in input list
     * @param checks valid inputs that return
     * @return char of selected input
     */

    public static char validInput(char[] checks){
        char c;
        while(true) {
            c = validChar();
            for (char check : checks) {
                if (c == check) {
                    return c;
                }
            }
            System.out.println("Enter valid input:");

        }
    }

    /**
     * validInput method checks if a user input is in input list
     * @param checks valid inputs that return
     * @param ignore Boolean that determines if cases should be ignored in the comparison
     * @return char of selected input
     */
    public static char validInput(char[] checks, boolean ignore){
        char c;
        while(true) {
            c = validChar();
            for (char check : checks) {
                if(ignore) {
                    if (("" + c).equalsIgnoreCase("" + check)) {
                        return Character.toLowerCase(c);
                    }
                }else{
                    if (c == check) {
                        return c;
                    }
                }
            }
            System.out.println("Enter valid input:");

        }
    }

    public static char validIntOrChar(int[] intChecks, char[] charChecks, boolean ignore){
        Scanner console = new Scanner(System.in);
        int num;
        char c;
        boolean wasInt = false;

        while (true) {
            if (console.hasNextInt()) {
                num = console.nextInt();
                for (int i = 0; i < intChecks.length; i++) {
                    if (num == intChecks[i]) {
                        wasInt = true;
                    }
                }
                if (wasInt) {
                    break;
                } else {
                    System.out.println("Please enter a valid input: ");
                    continue;
                }
            } else {
                c = console.next().charAt(0);
                for (char check : charChecks) {
                    if (ignore) {
                        if (("" + c).equalsIgnoreCase("" + check)) {
                            c = Character.toLowerCase(c);
                            return c;
                        }
                    } else {
                        if (c == check) {
                            return c;
                        }
                    }
                }

                System.out.println("Please enter a valid input: ");
                continue;
            }


            }

        return Character.forDigit(num,Character.MAX_RADIX);
    }
}
