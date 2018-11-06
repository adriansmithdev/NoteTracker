package model;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public class CodeBlock implements INote {
    private String title;
    private String dateCreated;
    private String code;
    private Notes typeOfNote;

    /**
     * @param title Title of note
     * @param dateCreated Date the note was created for sorting purposes
     * @param code Code block being stored
     */
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

    /**
     * @return code block being stored
     */
    public String getCode()
    {
        return this.code;
    }

    @Override
    public String toString()
    {
        return "CodeBlock{" +
                "title='" + title + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", code='" + code + '\'' +
                ", typeOfNote=" + typeOfNote +
                '}';
    }
}
