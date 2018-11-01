package views;

public class NoteFactory {

    public INoteCreator getNoteFor(String noteType) {
        switch (noteType.toLowerCase()) {
            case "codeblock":
                return new CodeBlockView();
            case "quote":
                return new QuoteView();
            default:
                return new ErrorView();
        }
    }

}
