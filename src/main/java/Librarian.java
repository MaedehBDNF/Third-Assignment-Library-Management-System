public class Librarian extends Account {

    public Librarian(String username, String password) {
        super(username, password);
    }

    @Override
    public Librarian update(AccountEntity accountEntity) {
        super.update(accountEntity);
        return this;
    }
}
