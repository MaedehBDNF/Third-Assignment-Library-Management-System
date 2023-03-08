public class Book {

    private String name;
    private String author;
    private int yearOfPublish;
    private String isbn;
    private boolean isInRent;

    public Book(String name, String author, int yearOfPublish, String isbn){
        this.name = name;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
        this.isbn = isbn;
        this.isInRent = false;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public Integer getYearOfPublish(){
        return this.yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish){
        this.yearOfPublish = yearOfPublish;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public boolean getIsInRent() {
        return this.isInRent;
    }

    public void setIsInRent(boolean isInRent) { this.isInRent = isInRent; }

    public Book rentBook(){
        this.isInRent = true;
        return this;
    }

    public void returnBook(){
        this.isInRent = false;
    }

    public Book update(BookEntity bookEntity) {
        if (!bookEntity.name.equals("")) {
            this.setName(bookEntity.name);
        }
        if (!bookEntity.author.equals("")) {
            this.setAuthor(bookEntity.author);
        }
        if (bookEntity.yearOfPublish != 0) {
            this.setYearOfPublish(bookEntity.yearOfPublish);
        }
        if (!bookEntity.isbn.equals("")) {
            this.setIsbn(bookEntity.isbn);
        }
        return this;
    }

    public String toString() {
        return "Book {" +
                "Name: '" + name + '\'' +
                ", Author: '" + author + '\'' +
                ", Year of Publish: " + yearOfPublish +
                ", ISBN: '" + isbn + '\'' +
                ", Is in rent? " + isInRent +
                '}';
    }
}
