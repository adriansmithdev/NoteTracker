package model;

public class WebLink implements INote {
    private String title;
    private String dateCreated;
    private String url;
    private Notes typeOfNote;

    public WebLink(String title, String dateCreated, String url)
    {
        this.title = title;
        this.dateCreated = dateCreated;
        this.url = url;
        this.typeOfNote = Notes.TO_DO;
    }

    @Override
    public String getTitle()
    {
        return this.title;
    }

    @Override
    public String getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public Notes getType()
    {
        return this.typeOfNote;
    }

    public String getURL()
    {
        return this.url;
    }
}
