package model;

public class CodeBlock implements INote {
    private String title;
    private String dateCreated;
    private String code;


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

    public String getCode()
    {
        return this.code;
    }
}
