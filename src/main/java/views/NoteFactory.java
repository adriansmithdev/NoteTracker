package views;

public class NoteFactory {

    public INoteCreator getNoteFor(String noteType) {
        switch (noteType.toUpperCase()) {
            case "CODE_BLOCK":
                return new CodeBlockFactory();
            case "QUOTATION":
                return new QuoteFactory();
            default:
                return new ErrorView();
        }
    }

}
