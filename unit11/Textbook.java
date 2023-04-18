package unit11;

// 2022 FRQ #2
// https://apcentral.collegeboard.org/media/pdf/ap22-frq-computer-science-a.pdf#page=8
class Book {
    /** The title of the book */
    private String title;
    /** The price of the book */
    private double price;

    /** Creates a new Book with given title and price */
    public Book(String bookTitle, double bookPrice) {
        title = bookTitle;
        price = bookPrice;
        /* implementation not shown */ }

    /** Returns the title of the book */
    public String getTitle() {
        return title;
    }

    /** Returns a string containing the title and price of the Book */
    public String getBookInfo() {
        return title + "-" + price;
    }
    // There may be instance variables, constructors, and methods that are not
    // shown.
}

class Textbook extends Book {
    private int edition;

    public Textbook(String a, double b, int c) {
        super(a, b);
        edition = c;
    }

    public String getBookInfo() {
        String a = super.getBookInfo();
        a += "-" + edition;
        return a;
    }

    public int getEdition() {
        return edition;
    }

    public boolean canSubstituteFor(Textbook a) {
        if (getTitle().equals(a.getTitle())) {
            if (getEdition() >= a.getEdition()) {
                return true;
            }
        }
        return false;
    }

}

class TextbookRun {

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
        Textbook bio2015 = new Textbook("Biology", 49.75, 2);
        Textbook bio2019 = new Textbook("Biology", 39.75, 3);
        check(bio2019.getEdition() == 3);
        check(bio2019.getBookInfo().equals("Biology-39.75-3"));
        check(bio2019.canSubstituteFor(bio2015));
        check(!bio2015.canSubstituteFor(bio2019));
        Textbook math = new Textbook("Calculus", 45.25, 1);
        check(!bio2015.canSubstituteFor(math));
        System.out.println("Happy Panda! \uD83D\uDC3C");

    }
}