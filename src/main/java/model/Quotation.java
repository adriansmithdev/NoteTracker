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

    @Override
    public String getContent()
    {
        return this.quote + "\n" + this.author;
        //return new String[] {this.quote, this.author};
    }

    public String getQuote()
    {
        return this.quote;
    }

    public String getAuthor()
    {
        return this.author;
    }
}
