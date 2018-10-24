package model;

public class Quotation implements INote {
    private String title;
    private String quote;
    private String author;
    private String dateCreated;

    public Quotation(String title, String quote, String author, String dateCreated)
    {
        this.title = title;
        this.quote = quote;
        this.author = author;
        this.dateCreated = dateCreated;
    }

    @Override
    public String getTitle()
    {
        return this.title;
    }

    @Override
    public String getDateCreated()
    {
        return this.dateCreated;
    }

    public String getQuote()
    {
        return this.quote;
    }

    public String getAuthor()
    {
        return this.author;
    }

    @Override
    public String toString()
    {
        return "Quotation{" +
                "title='" + title + '\'' +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
