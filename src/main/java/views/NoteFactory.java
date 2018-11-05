package views;

import model.Notes;

public class NoteFactory {

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
                return new ErrorView();
        }
    }

}
