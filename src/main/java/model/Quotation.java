package model;

public class Quotation implements INote {
    private String title;
    private String quote;
    private String author;
    private String dateCreated;
    private Notes noteType;

    public Quotation(String title, String quote, String author, String dateCreated)
    {
        this.title = title;
        this.quote = quote;
        this.author = author;
        this.dateCreated = dateCreated;
        this.noteType = Notes.QUOTATION;
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

    @Override
    public Notes getType()
    {
        return this.noteType;
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
