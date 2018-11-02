package views;

public class NoteFactory {

    public INoteCreator getNoteFor(String noteType) {
        switch (noteType.toLowerCase()) {
            case "codeblock":
                return new CodeBlockFactory();
            case "quote":
                return new QuoteFactory();
            default:
                return new ErrorView();
        }
    }

}
