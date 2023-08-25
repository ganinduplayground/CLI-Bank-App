
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

    static final String CLEAR = "\033[H\033[2J";
    static final String COLOR_BLUE_BOLD = "\033[34;1m";
    static final String DASHBOARD = "Welcome to Smart Banking";
    static String screen = DASHBOARD;
    static final String CREATE_NEW_ACCOUNT = "Create New Account";
    static final String DEPOSIT = "Deposit";
    static final String WITHDRAWALS = "Withdrawals";
    static final String TRANSFER = "Transfer";
    static final String CHECK_ACC_BALANCE = "Check Account Balance";
    static final String DELETE_ACCOUNT = "Delete Account";
    static final String APP_TITLE = String.format("%s%s%s", COLOR_BLUE_BOLD, screen, RESET);

    public static void main(String[] args) throws InterruptedException {

        do {

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
                    toDeleteAccount();
                    screen = DASHBOARD;
                    break;

                default:
                    break;
            }

        } while (true);
    }

    private static void toDeleteAccount() {
        String response2 = "";
        loop1: do {
            System.out.print("\tEnter account number to delete: ");
            id = SCANNER.nextLine();

            valid = isValidAccNumber(id);
            if (!valid) {
                System.out.printf(ERROR_MSG, "Invalid Account Number");
                continue loop1;
            }
            for (int i = 0; i < accounts.length; i++) {
                if (id.equals(accounts[i][0])) {
                    index = i;
                    break;
                }
            }
            System.out.println("\tName: " + accounts[index][1]);
            System.out.println("\tCurrent Account Balance: " + accounts[index][2]);

            System.out.print("\tAre you sure to delete this account? ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            if (!response.equals("Y")) {
                System.out.println(CLEAR);
                System.out.println("\t" + APP_TITLE + "\n");
                continue loop1;

            } else {
                System.out.println(accounts[0][1]);
                System.out.println(accounts[1][1]);
                System.out.println(accounts[2][1]);
                
                String[][] newAccounts = new String[accounts.length - 1][3];

                for (int i = 0; i < index; i++) {
                    newAccounts[i] = accounts[i];
                }

                for (int i = index; i < newAccounts.length; i++) {
                    newAccounts[i] = accounts[i+1];
                }

                accounts = newAccounts;
                System.out.println(accounts[0][1]);
                System.out.println(accounts[1][1]);
                // System.out.println(accounts[2][1]);

            }
            System.out.printf(SUCCESS_MSG, "Successfully deleted.");
            System.out.println();

            System.out.print("\tDo you want to continue?(Y/N): ");
            response2 = SCANNER.nextLine().strip().toUpperCase();
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

        } while (response2.equals("Y"));
    }

    private static void toCheckAccountBalance() {

        loop1: do {
            valid = true;
            System.out.print("\tEnter Account Number: ");
            id = SCANNER.nextLine().strip();

            valid = isValidAccNumber(id);
            if (!valid) {
                System.out.printf(ERROR_MSG, "Invalid Account Number");
                continue loop1;
            }
            for (int i = 0; i < accounts.length; i++) {
                if (id.equals(accounts[i][0])) {
                    index = i;
                    break;
                }
            }
            System.out.println("\tName: " + accounts[index][1]);
            System.out.println("\tCurrent Account Balance: " + accounts[index][2]);

            System.out.print("\tDo you want to continue?(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

        } while (valid);
    }

    private static void transfer() {
        String idToTransfer;
        int toIndex = 0;
        loop1: do {
            System.out.print("\tEnter Account Number from Transfer: ");
            id = SCANNER.nextLine().strip();

            valid = isValidAccNumber(id);
            if (!valid) {
                System.out.printf(ERROR_MSG, "Invalid Account Number");
                continue loop1;
            }
            for (int i = 0; i < accounts.length; i++) {
                if (id.equals(accounts[i][0])) {
                    index = i;
                    break;
                }
            }
            System.out.println("\tName: " + accounts[index][1]);
            System.out.println("\tCurrent Account Balance: " + accounts[index][2]);

            loop2: do {
                System.out.print("\tEnter Account Number to Transfer:");
                idToTransfer = SCANNER.nextLine().strip();

                valid = isValidAccNumber(idToTransfer);
                if (!valid) {
                    System.out.printf(ERROR_MSG, "Invalid Account Number");
                    continue loop2;
                }
                for (int i = 0; i < accounts.length; i++) {
                    if (idToTransfer.equals(accounts[i][0])) {
                        toIndex = i;
                        break;
                    }
                }
                System.out.println("\tName: " + accounts[toIndex][1]);
                System.out.println("\tCurrent Account Balance: " + accounts[toIndex][2]);
            } while (!valid);

            System.out.print("\tEnter amount to Transfer: ");
            double transferAmount = SCANNER.nextDouble();
            double sender = Double.parseDouble(accounts[index][2]);
            sender -= (transferAmount + transferAmount * 0.02);
            double reciever = Double.parseDouble(accounts[toIndex][2]);
            reciever += transferAmount;
            accounts[index][2] = sender + "";
            accounts[toIndex][2] = reciever + "";
            System.out.println();

            System.out.printf("\tSender's Account Number: %s\n\tSender's Name: %s\n\tSender's current Account Balance: %s\n",
                    accounts[index][0], accounts[index][1], accounts[index][2]);
            System.out.println();
            System.out.printf(
                    "\tReciever's Account Number: %s\n\tReciever's Name: %s\n\tReciever's current Account Balance: %s\n\n",
                    accounts[toIndex][0], accounts[toIndex][1], accounts[toIndex][2]);

            System.out.printf(SUCCESS_MSG, "Successfully transfered.");
            System.out.println();

            System.out.print("\tDo you want to continue?(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

        } while (valid);
    }

    private static void toWithdraw() {
        double withdraw;
        do {
            loop1: do {
                System.out.print("\tEnter Account Number: ");
                id = SCANNER.nextLine().strip();

                valid = isValidAccNumber(id);
                if (!valid) {
                    System.out.printf(ERROR_MSG, "Invalid Account Number");
                    continue loop1;
                }
                for (int i = 0; i < accounts.length; i++) {
                    if (id.equals(accounts[i][0])) {
                        index = i;
                        break;
                    }
                }
                System.out.println("\tName: " + accounts[index][1]);
                System.out.println("\tCurrent Account Balance: " + accounts[index][2]);

            } while (!valid);

            do {
                valid = true;
                System.out.print("\tEnter Withdraw Amount: ");
                withdraw = SCANNER.nextDouble();
                SCANNER.nextLine();
                if (withdraw < 100) {
                    System.out.printf(ERROR_MSG, "Minimum withdrawal amount should be Rs.100");
                    valid = false;
                    continue;
                }
                if (Double.valueOf(accounts[index][2]) < 500) {
                    System.out.printf(ERROR_MSG, "Insuffcient account balance.");
                    valid = false;
                    continue;
                }
            } while (!valid);

            accounts[index][2] = (Double.valueOf(accounts[index][2]) - withdraw) + "";

            System.out.println("\tNew balance: " + accounts[index][2]);

            System.out.printf(SUCCESS_MSG, "Withdrawn successful.");
            System.out.println();

            System.out.print("\tDo you want to continue?(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");
        } while (valid);

    }

    public static void toDeposit() {

        do {
            loop1: do {
                System.out.print("\tEnter Account Number: ");
                id = SCANNER.nextLine().strip();

                valid = isValidAccNumber(id);
                if (!valid) {
                    System.out.printf(ERROR_MSG, "Invalid Account Number");
                    continue loop1;
                }
                for (int i = 0; i < accounts.length; i++) {
                    if (id.equals(accounts[i][0])) {
                        index = i;
                        break;
                    }
                }
                System.out.println("\tName: " + accounts[index][1]);
                System.out.println("\tCurrent Account Balance: " + accounts[index][2]);

            } while (!valid);
            do {
                valid = true;
                System.out.print("\tDeposit amount: ");
                deposit = SCANNER.nextDouble();
                SCANNER.nextLine();
                if (deposit < 500.00) {
                    System.out.printf(ERROR_MSG, "Insufficient amount. Minimum amount is 500.00");
                    valid = false;
                }

            } while (!valid);

            accounts[index][2] = (Double.parseDouble(accounts[index][2]) + deposit) + "";

            System.out.println("\tNew balance: " + accounts[index][2]);

            System.out.printf(SUCCESS_MSG, "Successfully deposited.");
            System.out.println();

            System.out.print("\tDo you want to continue(Y/N): ");
            String response = SCANNER.nextLine().strip().toUpperCase();
            valid = response.equals("Y");
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

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
            genId++;
            System.out.printf(SUCCESS_MSG,
                    String.format("%s account has been created successfully", id));

            System.out.print("\tDo you want to continue adding (Y/N)?");
            response = SCANNER.nextLine().strip();
            response = SCANNER.nextLine().strip().toUpperCase();
            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

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