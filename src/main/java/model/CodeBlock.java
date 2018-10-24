package model;

public class CodeBlock implements INote {
    private String title;
    private String dateCreated;
    private String code;
    private Notes typeOfNote;

    public CodeBlock(String title, String dateCreated, String code)
    {
        this.title = title;
        this.dateCreated = dateCreated;
        this.code = code;
        this.typeOfNote = Notes.CODE_BLOCK;
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
        return this.typeOfNote;
    }

    public String getCode()
    {
        return this.code;
    }
}
