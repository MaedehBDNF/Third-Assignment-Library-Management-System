import java.util.ArrayList;
import java.util.*;

public class Library {

    private ArrayList<Book> listOfBooks= new ArrayList<>();
    private ArrayList<User> listOfUsers= new ArrayList<>();
    private ArrayList<Librarian> listOfLibrarians= new ArrayList<>();
    private Map<String,Integer> bookCounter = new HashMap<String, Integer>();

    // book related functions

    public void addBook(Book book){
        if (this.listOfBooks.contains(book)){
            increaseBook(book.getIsbn());
        } else {
            listOfBooks.add(book);
            bookCounter.put(book.getIsbn(), 1);
        }
    }

    public boolean removeBook(Book book){
        if (listOfBooks.contains(book)) {
            decreaseBook(book.getIsbn());
            return listOfBooks.remove(book);
        }
        return false;
    }

    public Book updateBook(Book book, BookEntity bookEntity){
        if (this.listOfBooks.contains(book)) {
            int index = listOfBooks.indexOf(book);
            if (!bookEntity.isbn.equals("") && !book.getIsbn().equals(bookEntity.isbn)) {
                if (!this.bookCounter.containsKey(bookEntity.isbn)) {
                    int bookCounter = this.bookCounter.get(book.getIsbn());
                    this.bookCounter.remove(book.getIsbn());
                    this.bookCounter.put(bookEntity.isbn, bookCounter);
                } else {
                    return null;
                }
            }
            listOfBooks.set(index, book.update(bookEntity));
            return listOfBooks.get(index);
        }
        return null;
    }

    public Book[] searchBook(BookEntity bookEntity){
        ArrayList<Book> result = new ArrayList<>();
        for (Book book: listOfBooks) {
            if (
                    book.getName().equals(bookEntity.name) ||
                    book.getAuthor().equals(bookEntity.author) ||
                    book.getYearOfPublish() == bookEntity.yearOfPublish ||
                    book.getIsbn().equals(bookEntity.isbn)
            ) {
                result.add(book);
            }
        }
        Book[] resultArray = new Book[result.size()];
        return result.toArray(resultArray);
    }

    public boolean doesBookExist(String isbn){
        return this.bookCounter.containsKey(isbn);
    }

    public boolean borrowBook(User user, Book book) {
        int bookIndex = this.listOfBooks.indexOf(book);
        if (bookIndex == -1 || book.getIsInRent()) {
            return false;
        }
        user.rentBook(book);
        book.rentBook();
        this.listOfBooks.set(bookIndex, book);
        return true;
    }

    public boolean returnBook(User user, Book book) {
        int bookIndex = this.listOfBooks.indexOf(book);
        if (bookIndex == -1 || !book.getIsInRent()) {
            return false;
        }
        user.returnBook(book);
        book.returnBook();
        this.listOfBooks.set(bookIndex, book);
        return true;
    }

    private void increaseBook(String isbn){
        int numOfBook = bookCounter.get(isbn) + 1;
        bookCounter.replace(isbn, numOfBook);
    }

    private void decreaseBook(String isbn){
        int numOfBook = bookCounter.get(isbn) - 1;
        if (numOfBook == 0) {
            bookCounter.remove(isbn);
        } else {
            bookCounter.replace(isbn, numOfBook);
        }
    }

    public String[] getBookStatistic(String bookName){
        BookEntity bookEntity = new BookEntity();
        bookEntity.name = bookName;
        Book[] foundBooks = searchBook(bookEntity);
        if (foundBooks.length == 0) {
            return null;
        }
        String[] result = new String[foundBooks.length];
        for (int i = 0; i < foundBooks.length; i++) {
            Book book = foundBooks[i];
            result[i] = book.getName() + "(ISBN: " + book.getIsbn() + ") -> " + bookCounter.get(book.getIsbn());
        }
        return result;
    }

    //user related functions

    public void addUser(User user){
        listOfUsers.add(user);
    }

    public boolean removeUser(User user) {
        if (this.listOfUsers.contains(user)) {
            return listOfUsers.remove(user);
        }
        return false;
    }

    public User updateUser(User user, AccountEntity accountEntity) {
        if (this.listOfUsers.contains(user)) {
            int index = listOfUsers.indexOf(user);
            if (!accountEntity.username.equals("") && !user.getUsername().equals(accountEntity.username)) {
                if (doesUserExist(accountEntity.username)) {
                    return null;
                }
            listOfUsers.set(index, user.update(accountEntity));
            return listOfUsers.get(index);
            }
            listOfUsers.set(index, user.update(accountEntity));
            return listOfUsers.get(index);
        }
        return null;
    }

    public User searchUser(String username){
        for (User user: listOfUsers){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }


    public boolean doesUserExist(String username){
        return searchUser(username) != null;
    }

    //librarian related functions

    public void addLibrarian(Librarian librarian){
        listOfLibrarians.add(librarian);
    }

    public boolean removeLibrarian(Librarian librarian){
        if (this.listOfLibrarians.contains(librarian)) {
            return listOfLibrarians.remove(librarian);
        }
        return false;
    }

    public Librarian updateLibrarian(Librarian librarian, AccountEntity accountEntity){
        if (this.listOfLibrarians.contains(librarian)){
            int index = listOfLibrarians.indexOf(librarian);
            if (!accountEntity.username.equals("") && !librarian.getUsername().equals(accountEntity.username)){
                if (this.doesLibrarianExist(accountEntity.username)) {
                    return null;
                }
            }
            listOfLibrarians.set(index, librarian.update(accountEntity));
            return librarian.update(accountEntity);
        }
        return null;
    }

    public Librarian searchLibrarian(String username){
        for (Librarian librarian: listOfLibrarians){
            if (librarian.getUsername().equals(username)){
                return librarian;
            }
        }
        return null;
    }

    public boolean doesLibrarianExist(String username){ return searchLibrarian(username) != null; }
}
