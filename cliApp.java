
import java.util.Scanner;

public class cliApp {
    private final static Scanner SCANNER = new Scanner(System.in);
    static final String COLOR_RED_BOLD = "\033[31;1m";
    static final String COLOR_GREEN_BOLD = "\033[33;1m";
    static final String RESET = "\033[0m";
    static final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
    static final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);
    static String[][] accounts = new String[0][3];
    static int genId = 1;
    static String id;
    static boolean valid;
    static int index;
    static double deposit;

    public static void main(String[] args) throws InterruptedException {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_NEW_ACCOUNT = "Create New Account";
        final String DEPOSIT = "Deposit";
        final String WITHDRAWALS = "Withdrawals";
        final String TRANSFER = "Transfer";
        final String CHECK_ACC_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete Account";

        String screen = DASHBOARD;

        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen) {
                case DASHBOARD:
                    int option = getDashboard();
                    switch (option) {
                        case 1:
                            screen = CREATE_NEW_ACCOUNT;

                            break;
                        case 2:
                            screen = DEPOSIT;
                            break;
                        case 3:
                            screen = WITHDRAWALS;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = CHECK_ACC_BALANCE;
                            break;
                        case 6:
                            screen = DELETE_ACCOUNT;
                            break;
                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);
                            break;

                        default:
                            break;
                    }

                    break;
                case CREATE_NEW_ACCOUNT:
                    createAccount();
                    screen = DASHBOARD;
                    break;

                case DEPOSIT:
                    toDeposit();
                    screen = DASHBOARD;
                    break;

                case WITHDRAWALS:
                    toWithdraw();
                    screen = DASHBOARD;
                    break;

                case TRANSFER:
                    transfer();
                    screen = DASHBOARD;
                    break;

                case CHECK_ACC_BALANCE:
                    toCheckAccountBalance();
                    screen = DASHBOARD;
                    break;

                case DELETE_ACCOUNT:
                    break;

                default:
                    break;
            }

        } while (true);
    }

    private static void toCheckAccountBalance() {

        loop1: do {
            valid = false;
            System.out.print("Enter Account Number: ");
            id = SCANNER.nextLine().strip();

            valid = isValidAccNumber(id);
            if (!valid)
                continue loop1;
            for (int i = 0; i < accounts.length; i++) {
                if (id.equals(accounts[i][0])) {
                    index = i;
                    break;
                }
            }
            System.out.println("Name: " + accounts[index][1]);
            System.out.println("Current Account Balance: " + accounts[index][2]);

            System.out.print("Do you want to go DashBoard?(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");

        } while (!valid);
    }

    



    private static void transfer() {
        String idToTransfer;
        int toIndex=0;
        loop1:
        do{
             System.out.print("Enter Account Number from Transfer: ");
            id = SCANNER.nextLine().strip();

            valid = isValidAccNumber(id);
            if (!valid)
                continue loop1;
            for (int i = 0; i < accounts.length; i++) {
                if (id.equals(accounts[i][0])) {
                    index = i;
                    break;
                }
            }
            System.out.println("Name: " + accounts[index][1]);
            System.out.println("Current Account Balance: " + accounts[index][2]);

            loop2:
            do{
            System.out.print("Enter Account Number to Transfer:");
            idToTransfer = SCANNER.nextLine().strip();

            valid = isValidAccNumber(idToTransfer);
            if (!valid)
                continue loop2;
            for (int i = 0; i < accounts.length; i++) {
                if (idToTransfer.equals(accounts[i][0]) ) {
                    toIndex = i;
                    break;
                }
            }
            System.out.println("Name: " + accounts[toIndex][1]);
            System.out.println("Current Account Balance: " + accounts[toIndex][2]);
            }while(!valid);

            System.out.print("Enter amount to Transfer: ");
            double transferAmount = SCANNER.nextDouble();
            double sender = Double.parseDouble(accounts[index][2]);
            sender-= (transferAmount+transferAmount*0.02);
            double reciever = Double.parseDouble(accounts[toIndex][2]);
            reciever+=transferAmount;
            accounts[index][2]= sender+"";
            accounts[toIndex][2]=reciever+"";

            System.out.printf("Sender's Account Number: %s\nSender's Name: %s\nSender's current Account Balance: %s\n", accounts[index][0],accounts[index][1],accounts[index][2]);
            System.out.println();
            System.out.printf("Reciever's Account Number: %s\nReciever's Name: %s\nReciever's current Account Balance: %s\n", accounts[toIndex][0],accounts[toIndex][1],accounts[toIndex][2]);
            
            System.out.print("Do you want to continue(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");



        }while(!valid);
    }

    private static void toWithdraw() {
        double withdraw;
        do {
            loop1: do {
                System.out.print("Enter Account Number: ");
                id = SCANNER.nextLine().strip();

                valid = isValidAccNumber(id);
                if (!valid)
                    continue loop1;
                for (int i = 0; i < accounts.length; i++) {
                    if (id.equals(accounts[i][0])) {
                        index = i;
                        break;
                    }
                }
                System.out.println("Name: " + accounts[index][1]);
                System.out.println("Current Account Balance: " + accounts[index][2]);

            } while (!valid);

            do {
                valid = true;
                System.out.print("Enter Withdraw Amount: ");
                withdraw = SCANNER.nextDouble();
                SCANNER.nextLine();
                if (withdraw < 100) {
                    System.out.println("Minimum withdrawal amount should be Rs.100.");
                    valid = false;
                    continue;
                }
                if (Double.valueOf(accounts[index][2]) < 500) {
                    System.out.println("Insuffcient account balance.");
                    valid = false;
                    continue;
                }
            } while (!valid);

            accounts[index][2] = (Double.valueOf(accounts[index][2]) - withdraw) + "";

            System.out.println("New balance: " + accounts[index][2]);

            System.out.print("Do you want to continue(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");
        } while (valid);

    }

    public static void toDeposit() {

        do {
            loop1: do {
                System.out.print("Enter Account Number: ");
                id = SCANNER.nextLine().strip();

                valid = isValidAccNumber(id);
                if (!valid)
                    continue loop1;
                for (int i = 0; i < accounts.length; i++) {
                    if (id.equals(accounts[i][0])) {
                        index = i;
                        break;
                    }
                }
                System.out.println("Name: " + accounts[index][1]);
                System.out.println("Current Account Balance: " + accounts[index][2]);

            } while (!valid);
            do {
                valid = true;
                System.out.print("Deposit amount: ");
                deposit = SCANNER.nextDouble();
                SCANNER.nextLine();
                if (deposit < 500.00) {
                    System.out.println("Insufficient amount. Minimum amount is 500.00.");
                    valid = false;
                }

            } while (!valid);

            accounts[index][2] = (Double.parseDouble(accounts[index][2]) + deposit) + "";

            System.out.println("New balance: " + accounts[index][2]);

            System.out.print("Do you want to continue(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");

        } while (valid);

    }

    private static boolean isValidAccNumber(String input) {
        valid = true;
        do {
            if (input.isEmpty()) {
                System.out.printf(ERROR_MSG, "Can't be blank");
                valid = false;
                break;
            } else if (!input.startsWith("SDB-") || input.length() != 9) {
                System.out.printf(ERROR_MSG, "Invalid format");
                valid = false;
                break;
            } else {
                for (int i = 0; i < accounts.length; i++) {
                   
                    if (input.equals(accounts[i][0])) {
                        valid = true;
                        break;

                    } else
                        valid = false;
                }

            }
        } while (false);

        return valid;

    }

    public static void createAccount() throws InterruptedException {

        String inputName;
        Double deposit;

        String response;

        do {

            do {
                id = String.format("SDB-%05d", genId);
                System.out.println("\tAccount Number:" + id);

                System.out.print("\tEnter Name: ");
                inputName = SCANNER.nextLine().strip();

                valid = isValid(inputName);

            } while (!valid);

            do {
                valid = true;
                System.out.print("\tInitial Deposit: ");
                deposit = SCANNER.nextDouble();
                // if (deposit == 0.0) {
                // System.out.printf(ERROR_MSG, "Name can't be empty");
                // valid = false;
                // continue;
                // }
                if (deposit < 5000.00) {
                    System.out.printf(ERROR_MSG, "Minimum deposit should be Rs.5000.00");
                    valid = false;
                    continue;

                }

            } while (!valid);

            String[][] newAccounts = new String[accounts.length + 1][3];

            for (int i = 0; i < accounts.length; i++) {
                newAccounts[i] = accounts[i];
            }

            newAccounts[newAccounts.length - 1][0] = id;
            newAccounts[newAccounts.length - 1][1] = inputName;
            newAccounts[newAccounts.length - 1][2] = deposit + "";

            accounts = newAccounts;
            // System.out.println(Arrays.toString(accounts[0]));
            genId++;
            System.out.printf(SUCCESS_MSG,
                    String.format("%s account has been created successfully", id));

            System.out.print("\tDo you want to continue adding (Y/N)?");
            response = SCANNER.nextLine().strip();
            response = SCANNER.nextLine().strip().toUpperCase();
            // response="Y";

            // Thread.sleep(5000);

        } while (response.equals("Y"));

    }

    public static int getDashboard() {

        System.out.println("\t[1] Create New Account");
        System.out.println("\t[2] Deposits");
        System.out.println("\t[3] Withdrawals");
        System.out.println("\t[4] Transfer");
        System.out.println("\t[5] Check Account Balance");
        System.out.println("\t[6] Delete Account");
        System.out.println("\t[7] Exit");
        System.out.print("\tEnter an option to continue: ");
        int option = SCANNER.nextInt();
        SCANNER.nextLine();

        return option;

    }

    public static boolean isValid(String input) {
        boolean valid = true;
        loop1: {
            if (input.isBlank()) {
                System.out.printf(ERROR_MSG, "\tName can't be empty");
                valid = false;
                break loop1;
            }
            for (int i = 0; i < input.length(); i++) {
                if (!(Character.isLetter(input.charAt(i)) ||
                        Character.isSpaceChar(input.charAt(i)))) {
                    System.out.printf(ERROR_MSG, "\tInvalid name");
                    valid = false;
                    break;
                }
            }
        }

        return valid;
    }

}