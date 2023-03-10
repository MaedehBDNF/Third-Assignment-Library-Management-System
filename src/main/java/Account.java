public class Account {

    private String username;
    private String password;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account update(AccountEntity accountEntity) {
        if (!accountEntity.username.equals("")) {
            setUsername(accountEntity.username);
        }
        if (!accountEntity.password.equals("")) {
            setPassword(accountEntity.password);
        }
        return this;
    }

    public String toString() {
        return "Account {" +
                "username: '" + username + '\'' +
                ", password: '" + password + '\'' +
                '}';
    }
}
