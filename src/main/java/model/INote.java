package model;


/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public interface INote {
    /**
     * @return title of note
     */
    String getTitle();

    /**
     * @return date note was created
     */
    String getDateCreated();

    /**
     * @return type of note
     */
    Notes getType();

}
