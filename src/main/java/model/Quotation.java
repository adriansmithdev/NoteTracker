package model;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public class Quotation implements INote {
    private String title;
    private String quote;
    private String author;
    private String dateCreated;
    private Notes noteType;

    /**
     * @param title title of note
     * @param quote quote being stored
     * @param author person who said quote
     * @param dateCreated date note was created
     */
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

    /**
     * @return quote being stored
     */
    public String getQuote()
    {
        return this.quote;
    }

    /**
     * @return author of quote being stored
     */
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
