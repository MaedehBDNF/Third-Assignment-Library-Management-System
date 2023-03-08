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

    public static void librarianMenu(){
        System.out.print(
                "What do you want to do? (Enter only its number)" + "\n" +
                        "1. Search book." + "\n" +
                        "2. Add a new book." + "\n" +
                        "3. Get statistics report for books" + "\n" +
                        "4. Search user." + "\n" +
                        "5. Add a new user." + "\n" +
                        "6. Search librarian." + "\n" +
                        "7. Add a new librarian." + "\n" +
                        "0. Log out." + "\n" + "\n" +
                        "Enter your choice: "
        );

        short action = in.nextShort();
        in.nextLine();

        switch (action){
            case 0:
                startMenu();
            case 1: // search book
                librarianSearchBookMenu();
                break;
            case 2: // add new book
                addBookMenu();
                break;
            case 3: // get statistics report
                getBookStatistic("librarian");
                break;
            case 4: // search user
                searchUserMenu();
                break;
            case 5: // add new user
                addUserMenu();
                break;
            case 6: // search librarian
                searchLibrarianMenu();
                break;
            case 7: // add new librarian
                addLibrarianMenu();
                break;
            default:
                System.out.println("Wrong input. Please try again." + "\n");
                librarianMenu();
        }
    }

    public static void userMenu(){
        System.out.print(
                "What do you want to do? (Enter only its number)" + "\n" +
                        "1. Search book." + "\n" +
                        "2. Return book." + "\n" +
                        "3. Get statistics report for books" + "\n" +
                        "4. Update username." + "\n" +
                        "5. Update password." + "\n" +
                        "0. Log out." + "\n" + "\n" +
                        "Enter your choice: "
        );

        short action = in.nextShort();
        in.nextLine();

        switch (action){
            case 0:
                startMenu();
            case 1: // search book
                userSearchBookMenu();
                break;
            case 2: // return book
                returnBookMenu();
                break;
            case 3: // get statistics report
                getBookStatistic("user");
                break;
            case 4: // update username
                updateUsernameMenu();
                break;
            case 5: // update password
                updatePasswordMenu();
                break;
            default:
                System.out.println("Wrong input. Please try again." + "\n");
                librarianMenu();
        }
    }

    private static void librarianSearchBookMenu() {
        Book[] foundBooks = library.searchBook(getBookEntity());
        if (foundBooks.length == 0) {
            System.out.println();
            System.out.print(
                    "Sorry, there is not any result!" + "\n" +
                            "1. New search." + "\n" +
                            "2. Back to menu."
            );
            short action = in.nextShort();
            in.nextLine();
            if (action == 1) {
                librarianSearchBookMenu();
            } else {
                librarianMenu();
            }
        } else {
            System.out.println(
                    "Found books: (Enter the book number to edit or remove it)" + "\n" +
                            "-1: New Search." + "\n" +
                            "0: Back to menu."
            );
            for (int i = 1; i <= foundBooks.length; i++) {
                System.out.println(i + ". " + foundBooks[i - 1].toString());
            }
            System.out.println();
            System.out.print("Enter your choice: ");
            short action = in.nextShort();
            in.nextLine();
            if (action == -1) {
                librarianSearchBookMenu();
            } else if (action == 0) {
                librarianMenu();
            } else if (action > 0 && action <= foundBooks.length) {
                Book selectedBook = foundBooks[action - 1];
                System.out.println(selectedBook.toString());
                System.out.print(
                        "1. Update book." + "\n" +
                                "2. Remove book." + "\n" +
                                "3. Back to search menu." + "\n" +
                                "Enter your choice: "
                );
                action = in.nextShort();
                in.nextLine();
                if (action == 1) {
                    System.out.println("Enter updated fields: ");
                    BookEntity bookEntity = getBookEntity();
                    Book updatedBook = library.updateBook(selectedBook, bookEntity);
                    if (updatedBook != null) {
                        System.out.println("Book updated successfully!");
                        System.out.println(updatedBook.toString());
                        librarianMenu();
                    } else {
                        System.out.println("Something went wrong. Try again.");
                        librarianSearchBookMenu();
                    }
                } else if (action == 2) {
                    if (library.removeBook(selectedBook)) {
                        System.out.println("Book removed successfully!");
                        librarianMenu();
                    } else {
                        System.out.println("Something went wrong. Try again.");
                        librarianSearchBookMenu();
                    }
                } else if (action == 3) {
                    librarianSearchBookMenu();
                } else {
                    System.out.println("Wrong input. Try again.");
                    librarianSearchBookMenu();
                }
            } else {
                System.out.println("Wrong input. Try again.");
                librarianSearchBookMenu();
            }
        }
    }

    private static void userSearchBookMenu() {
        Book[] foundBooks = library.searchBook(getBookEntity());
        if (foundBooks.length == 0) {
            System.out.println();
            System.out.print(
                    "Sorry, there is not any result!" + "\n" +
                            "1. New search." + "\n" +
                            "2. Back to menu."
            );
            short action = in.nextShort();
            in.nextLine();
            if (action == 1) {
                userSearchBookMenu();
            } else {
                userMenu();
            }
        } else {
            System.out.println(
                    "Found books: (Enter the book number to borrow or return that)" + "\n" +
                            "-1: New Search." + "\n" +
                            "0: Back to menu."
            );
            for (int i = 1; i <= foundBooks.length; i++) {
                System.out.println(i + ". " + foundBooks[i - 1].toString());
            }
            System.out.println();
            System.out.print("Enter your choice: ");
            short action = in.nextShort();
            in.nextLine();
            if (action == -1) {
                userSearchBookMenu();
            } else if (action == 0) {
                userMenu();
            } else if (action > 0 && action <= foundBooks.length) {
                Book selectedBook = foundBooks[action - 1];
                System.out.println("Your choice is " + selectedBook.toString());
                System.out.print(
                        "1. Borrow book." + "\n" +
                                "2. Back to search menu." + "\n" +
                                "Enter your choice: "
                );
                action = in.nextShort();
                in.nextLine();
                if (action == 1) {
                    if (selectedBook.getIsInRent()) {
                        System.out.println("Sorry, book is in rent.");
                        userSearchBookMenu();
                    } else {
                        User user = library.searchUser(currentUserUsername);
                        if (library.borrowBook(user, selectedBook)) {
                            System.out.println("Book borrowed successfully!");
                            userMenu();
                        } else {
                            System.out.println("Wrong input. Try again.");
                            userSearchBookMenu();
                        }
                    }
                } else if (action == 2) {
                    userSearchBookMenu();
                } else {
                    System.out.println("Wrong input. Try again.");
                    userSearchBookMenu();
                }
            } else {
                System.out.println("Wrong input. Try again.");
                userSearchBookMenu();
            }
        }
    }

    private static BookEntity getBookEntity() {
        BookEntity bookEntity = new BookEntity();
        System.out.print("Please enter the book name: (Just enter if you don't know that) ");
        String input = in.nextLine();
        if (input.length() > 0) {
            bookEntity.name = input;
        }
        System.out.print("Please enter the author of the book: (Just enter if you don't know that) ");
        input = in.nextLine();
        if (input.length() > 0) {
            bookEntity.author = input;
        }
        System.out.print("Please enter the year which the book published in that: (Just enter if you don't know that) ");
        input = in.nextLine();
        if (input.length() > 0) {
            bookEntity.yearOfPublish = Integer.parseInt(input);
        }
        System.out.print("Please enter the ISBN number of the book name: (Just enter if you don't know that) ");
        input = in.nextLine();
        if (input.length() > 0) {
            bookEntity.isbn = input;
        }
        return bookEntity;
    }

    private static void addBookMenu() {
        System.out.print("Please enter the book name: ");
        String name = in.nextLine();
        if (name.length() == 0) {
            System.out.println("Wrong name. Try again.");
            addBookMenu();
        }
        System.out.print("Please enter the author of the book: ");
        String author = in.nextLine();
        if (author.length() == 0) {
            System.out.println("Wrong author. Try again.");
            addBookMenu();
        }
        System.out.print("Please enter the year which the book published in that: ");
        String yearOfPublish = in.nextLine();
        if (yearOfPublish.length() == 0) {
            System.out.println("Wrong year. Try again.");
            addBookMenu();
        }
        System.out.print("Please enter the ISBN number of the book name: ");
        String isbn = in.nextLine();
        if (isbn.length() == 0) {
            System.out.println("Wrong ISBN. Try again.");
            addBookMenu();
        }
        Book book = new Book(name, author, Integer.parseInt(yearOfPublish), isbn);
        if (library.doesBookExist(isbn)) {
            System.out.println("The ISBN is wrong. (Duplicate ISBN)");
            addBookMenu();
        }
        library.addBook(book);

        System.out.println();
        System.out.print(
                "1. New Book" + "\n" +
                        "2. Back to menu."
        );
        short action = in.nextShort();
        in.nextLine();
        if (action == 1) {
            addBookMenu();
        } else {
            librarianMenu();
        }
    }

    private static void returnBookMenu() {
        User currentUser = library.searchUser(currentUserUsername);
        System.out.println("List of books that you have reserved: ");
        Book[] userBooks = currentUser.getBooksInRentByUser();
        for (int i = 1; i <= userBooks.length; i++) {
            System.out.println(i + ". " + userBooks[i - 1].toString());
        }
        System.out.print(
                "Enter book number to return it." + "\n" +
                        "0. Back to menu." + "\n" +
                        "Enter your choice: "
        );
        short action = in.nextShort();
        in.nextLine();
        if (action == 0) {
            userMenu();
        } else if (action > 0 && action <= userBooks.length) {
            if (library.returnBook(currentUser, userBooks[action - 1])) {
                System.out.println("Book returned successfully.");
                userMenu();
            } else {
                System.out.println("Wrong input. Try again.");
                returnBookMenu();
            }
        } else {
            System.out.println("Wrong input. Try again.");
            returnBookMenu();
        }
    }

    private static void getBookStatistic(String role) {
        System.out.print("Enter the book name: ");
        String bookName = in.nextLine();
        String[] result = library.getBookStatistic(bookName);
        if (result.length == 0) {
            System.out.println("Sorry, there is not any result!");
        } else {
            for(String statisticResult: result) {
                System.out.println(statisticResult);
            }
        }
        System.out.println();
        System.out.print(
                "1. New search." + "\n" +
                        "2. Back to menu."
        );
        short action = in.nextShort();
        in.nextLine();
        if (action == 1) {
            getBookStatistic(role);
        } else {
            if (role.equals("librarian")) {
                librarianMenu();
            } else {
                userMenu();
            }
        }
    }

    private static void searchUserMenu() {
        System.out.print(
                "Search user:" + "\n" +
                        "Enter the user username: "
        );
        String username = in.nextLine();
        User user = library.searchUser(username);
        if (user == null) {
            System.out.println();
            System.out.print(
                    "Sorry, there is not any result!" + "\n" +
                            "1. New search." + "\n" +
                            "2. Back to menu."
            );
            int action = in.nextInt();
            in.nextLine();
            if (action == 1) {
                searchUserMenu();
            } else {
                librarianMenu();
            }
        } else {
            System.out.println(user.toString());
            System.out.print(
                    "1: New Search." + "\n" +
                            "2. Update user." + "\n" +
                            "3. Remove user." + "\n" +
                            "0: Back to menu." + "\n" +
                            "Enter your choose: "
            );
            short action = in.nextShort();
            in.nextLine();
            if (action == 1) {
                searchUserMenu();
            } else if (action == 0) {
                librarianMenu();
            } else if (action == 2) {
                System.out.println("Enter updated fields: ");
                AccountEntity accountEntity = getAccountEntity();
                User updatedUser = library.updateUser(user, accountEntity);
                if (updatedUser != null) {
                    System.out.println("User updated successfully!");
                    System.out.println(updatedUser.toString());
                    librarianMenu();
                } else {
                    System.out.println("Something went wrong. Try again.");
                    searchUserMenu();
                }
            } else if (action == 3) {
                if (library.removeUser(user)) {
                    System.out.println("User removed successfully!");
                    librarianMenu();
                } else {
                    System.out.println("Something went wrong. Try again.");
                    searchUserMenu();
                }
            } else {
                System.out.println("Wrong input. Try again");
                searchUserMenu();
            }
        }
    }

    private static void addUserMenu() {
        System.out.print("Please enter the user's username: ");
        String username = in.nextLine();
        if (username.length() == 0) {
            System.out.println("Wrong username. Try again.");
            addUserMenu();
        }
        System.out.print("Please enter the user password: ");
        String password = in.nextLine();
        if (password.length() == 0) {
            System.out.println("Wrong password. Try again.");
            addUserMenu();
        }
        User user = new User(username, password);
        if (library.doesUserExist(username)) {
            System.out.println("This username is taken. Try another one.");
            addUserMenu();
        }
        library.addUser(user);

        System.out.println();
        System.out.print(
                "1. New User" + "\n" +
                        "2. Back to menu."
        );
        short action = in.nextShort();
        in.nextLine();
        if (action == 1) {
            addUserMenu();
        } else {
            librarianMenu();
        }
    }

    private static AccountEntity getAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        System.out.print("Please enter the username: (Just enter if you don't know that) ");
        String input = in.nextLine();
        if (input.length() > 0) {
            accountEntity.username = input;
        }
        System.out.print("Please enter the password: (Just enter if you don't know that) ");
        input = in.nextLine();
        if (input.length() > 0) {
            accountEntity.password = input;
        }
        return accountEntity;
    }

    private static void searchLibrarianMenu() {
        System.out.print(
                "Search librarian:" + "\n" +
                        "Enter the librarian username: "
        );
        String username = in.nextLine();
        Librarian librarian = library.searchLibrarian(username);
        if (librarian == null) {
            System.out.println();
            System.out.print(
                    "Sorry, there is not any result!" + "\n" +
                            "1. New search." + "\n" +
                            "2. Back to menu."
            );
            short action = in.nextShort();
            in.nextLine();
            if (action == 1) {
                searchLibrarianMenu();
            } else {
                librarianMenu();
            }
        } else {
            System.out.println(librarian.toString());
            System.out.print(
                    "1: New Search." + "\n" +
                            "2. Update librarian." + "\n" +
                            "3. Remove librarian." + "\n" +
                            "0: Back to menu." + "\n" +
                            "Enter your choose: "
            );
            short action = in.nextShort();
            in.nextLine();
            if (action == 1) {
                searchLibrarianMenu();
            } else if (action == 0) {
                librarianMenu();
            } else if (action == 2) {
                System.out.println("Enter updated fields: ");
                AccountEntity accountEntity = getAccountEntity();
                Librarian updatedLibrarian = library.updateLibrarian(librarian, accountEntity);
                if (updatedLibrarian != null) {
                    System.out.println("Librarian updated successfully!");
                    System.out.println(librarian.toString());
                    librarianMenu();
                } else {
                    System.out.println("Something went wrong. Try again.");
                    searchLibrarianMenu();
                }
            } else if (action == 3) {
                if (library.removeLibrarian(librarian)) {
                    System.out.println("Librarian removed successfully!");
                    librarianMenu();
                } else {
                    System.out.println("Something went wrong. Try again.");
                    searchLibrarianMenu();
                }
            } else {
                System.out.println("Wrong input. Try again.");
                searchLibrarianMenu();
            }
        }
    }

    private static void addLibrarianMenu() {
        System.out.print("Please enter the librarian's username: ");
        String username = in.nextLine();
        if (username.length() == 0) {
            System.out.println("Wrong username. Try again.");
            addLibrarianMenu();
        }
        System.out.print("Please enter the librarian password: ");
        String password = in.nextLine();
        if (password.length() == 0) {
            System.out.println("Wrong password. Try again.");
            addLibrarianMenu();
        }
        Librarian librarian = new Librarian(username, password);
        if (library.doesUserExist(username)) {
            System.out.println("This username is taken. Try another one.");
            addLibrarianMenu();
        }
        library.addLibrarian(librarian);

        System.out.println();
        System.out.print(
                "1. New librarian" + "\n" +
                        "2. Back to menu."
        );
        short action = in.nextShort();
        in.nextLine();
        if (action == 1) {
            addLibrarianMenu();
        } else {
            librarianMenu();
        }
    }
