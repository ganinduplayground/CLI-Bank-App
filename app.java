import java.util.Scanner;

public class app {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_NEW_ACCOUNT = "Create New Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        int genId = 1;

        loop1:
        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            String[][] accounts = new String[0][3];

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen) {
                case DASHBOARD:

                    System.out.println("\t[1] Create New Account");
                    System.out.println("\t[2] Deposits");
                    System.out.println("\t[3] Withdrawals");
                    System.out.println("\t[4] Transfer");
                    System.out.println("\t[5] Print Statement");
                    System.out.println("\t[6] Delete Account");
                    System.out.println("\t[7] Exit");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();
                    switch (option) {
                        case 1:
                            screen = CREATE_NEW_ACCOUNT;

                            break;

                        case 2:

                            break;

                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);

                        default:
                            continue;
                    }


                case CREATE_NEW_ACCOUNT:
                    String inputName;
                    Double deposit;
                    boolean valid;
                    String id;

                    do {
                        id = String.format("SDB-%05d", genId);
                        System.out.println("\tAccount Number:"+ id);
                        
                        valid = true;
                        System.out.print("\tEnter Name: ");
                        inputName = SCANNER.nextLine().strip();
                        if (inputName.isBlank()) {
                            System.out.printf(ERROR_MSG, "\tName can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < inputName.length(); i++) {
                            if (!(Character.isLetter(inputName.charAt(i)) ||
                                    Character.isSpaceChar(inputName.charAt(i)))) {
                                System.out.printf(ERROR_MSG, "\tInvalid name");
                                valid = false;
                                break;
                            }
                        }

                    } while (!valid);


                    do {
                        valid = true;
                        System.out.print("\tInitial Deposit: ");
                        deposit = SCANNER.nextDouble();
                        // if (deposit == 0.0) {
                        //     System.out.printf(ERROR_MSG, "Name can't be empty");
                        //     valid = false;
                        //     continue;
                        // }
                        if (deposit < 5000.00) {
                            System.out.printf(ERROR_MSG, "Minimum deposit should be Rs.5000.00");
                            valid = false;
                            continue;

                        }

                    } while (!valid);


                    String[][] newAccounts = new String[accounts.length+1][3];

                    for (int i = 0; i < accounts.length; i++) {
                        newAccounts[i]=accounts[i];
                    }

                    newAccounts[newAccounts.length-1][0] =  id;
                    newAccounts[newAccounts.length-1][1] =  inputName;
                    newAccounts[newAccounts.length-1][2] =  deposit +"";

                    accounts=newAccounts;
                    System.out.println();
                    genId++;
                    System.out.printf(SUCCESS_MSG,
                            String.format("%s account has been created successfully", id));
                    System.out.print("\tDo you want to continue adding (Y/N)?");
                    String response = SCANNER.nextLine().strip().toUpperCase();
                    if (!response.equals("Y"))  continue ;

                    screen = DASHBOARD;

                    
                    break;

                    default:
                    break;
            }

        } while (false);
    }
}