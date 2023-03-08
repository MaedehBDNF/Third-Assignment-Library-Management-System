import java.util.ArrayList;

public class User extends Account {

    private ArrayList<Book> booksInRentByUser = new ArrayList<>();

    public User(String username, String password){
        super(username, password);
    }

    public Book[] getBooksInRentByUser() {
        Book[] result = new Book[booksInRentByUser.size()];
        return this.booksInRentByUser.toArray(result);
    }

    public void rentBook(Book book) {
        book.rentBook();
        this.booksInRentByUser.add(book);
    }

    public void returnBook(Book book){
        if (this.doesRentBook(book)) {
            book.returnBook();
            booksInRentByUser.remove(book);
        }
    }
    @Override
    public User update(AccountEntity accountEntity) {
        super.update(accountEntity);
        return this;
    }

    private boolean doesRentBook(Book book) {
        return this.booksInRentByUser.contains(book);
    }
}
