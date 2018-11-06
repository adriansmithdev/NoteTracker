package views;

import model.Notes;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public class NoteFactory {

    /**
     * @param noteType the type of note being generated
     * @return INote of noteType
     */
    public INoteCreator getNoteFor(Notes noteType) {
        switch (noteType) {
            case CODE_BLOCK:
                return new CodeBlockFactory();
            case QUOTATION:
                return new QuoteFactory();
            case TO_DO:
                return new ToDoFactory();
            case WEBLINK:
                return new WebLinkFactory();
            default:
                return new ErrorFactory();
        }
    }

}
