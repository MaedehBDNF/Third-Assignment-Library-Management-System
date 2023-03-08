import java.util.Scanner;

public class Main {

    private static Library library = new Library();
    private static String currentUserUsername;
    private static Scanner in;

    public static void main(String[] args) {
        Librarian headLibrarian = new Librarian("Head_of_librarians", "001122");
        library.addLibrarian(headLibrarian);
        currentUserUsername = headLibrarian.getUsername();
        in = new Scanner(System.in);
        startMenu();
    }

    private static void startMenu() {
        System.out.print(
                "Welcome to our library!" + "\n\n" +
                        "1. Login" + "\n" +
                        "2. Sign Up" + "\n" +
                        "3. Exit" + "\n" +
                        "Enter: "
        );
        short action = in.nextShort();
        in.nextLine();

        if (action == 1) {
            loginMenu();
        } else if (action == 2) {
            signUpMenu();
        } else if (action == 3) {
            System.exit(0);
        } else {
            System.out.println("Wrong input. Try again.");
            startMenu();
        }
    }

    private static void loginMenu() {
        System.out.print("Enter '0' for back (or just enter): ");
        if (in.nextLine().equals("0")) {
            startMenu();
        }
        System.out.print("Please Enter your username: ");
        String username = in.nextLine();
        System.out.print("Please Enter your password: ");
        String password = in.nextLine();
        System.out.print("Please specify your role (1. Librarian, 2. User): ");
        short role = in.nextShort();
        in.nextLine();

        if (role == 1) {
            if (library.doesLibrarianExist(username)) {
                Librarian librarian = library.searchLibrarian(username);
                if (librarian.getPassword().equals(password)) {
                    currentUserUsername = username;
                    System.out.println("Successful login!" + "\n");
                    librarianMenu();
                } else {
                    System.out.println("Your password is wrong!" + "\n");
                    loginMenu();
                }
            }
        } else if (role == 2) {
            if (library.doesUserExist(username)) {
                User user = library.searchUser(username);
                if (user.getPassword().equals(password)) {
                    currentUserUsername = username;
                    System.out.println("Successful login!" + "\n");
                    userMenu();
                } else {
                    System.out.println("Your password is wrong!" + "\n");
                    loginMenu();
                }
            }
        }
    }

    private static void signUpMenu() {
        System.out.print("Enter '0' for back (or just enter): ");
        if (in.nextLine().equals("0")) {
            startMenu();
        }
        System.out.print("Please Enter your username: ");
        String username = in.nextLine();
        System.out.print("Please Enter your password: ");
        String password = in.nextLine();
        System.out.print("Please specify your role (1. Librarian, 2. User): ");
        short role = in.nextShort();
        in.nextLine();

        if (role == 1) {
            if (library.doesLibrarianExist(username)) {
                System.out.println("The username already used, try with another username.\n");
                signUpMenu();
            }
            Librarian librarian = new Librarian(username, password);
            library.addLibrarian(librarian);
            currentUserUsername = username;
            System.out.println("Successful Sign Up!" + "\n");
            librarianMenu();
        } else if (role == 2) {
            if (library.doesUserExist(username)) {
                System.out.println("The username already used, try with another username.\n");
                signUpMenu();
            }
            User user = new User(username, password);
            library.addUser(user);
            currentUserUsername = username;
            System.out.println("Successful Sign Up!" + "\n");
            userMenu();
        }
    }

    public static void runMenu(){
        //TODO:
    }
}
