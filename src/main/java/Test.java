import java.util.Arrays;

public class Test {

    public static void main(String[] args){
        // create new object of classes and add to library
        Library myLibrary = new Library();
        Librarian librarian1 = new Librarian("Sam", "Sam@584");
        myLibrary.addLibrarian(librarian1);
        User user1 = new User("Katy", "K098_!@");
        myLibrary.addUser(user1);
        Book calculus = new Book("Calculus","James Stewart",1987, "978-1285740621");
        myLibrary.addBook(calculus);

        // updating a book
        BookEntity calculus1 = new BookEntity();
        calculus1.name = "Calculus I";
        myLibrary.updateBook(calculus, calculus1);
        System.out.println(calculus);

        // rent book and check that status of book "isInRent" and BookStatistics have changed correctly or not
        user1.rentBook(calculus);
        System.out.println(Arrays.toString(myLibrary.getBookStatistic("calculus I")));
        System.out.println(calculus);

        // remove user
        myLibrary.removeUser(user1);

    }


}
