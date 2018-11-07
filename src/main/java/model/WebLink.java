package model;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class WebLink implements INote {
    private String title;
    private String dateCreated;
    private String url;
    private Notes typeOfNote;

    /**
     * @param title       title of weblink
     * @param dateCreated date link note was created
     * @param url         url being stored
     */
    public WebLink(String title, String dateCreated, String url) {
        this.title = title;
        this.dateCreated = dateCreated;
        if (url.startsWith("https://") || url.startsWith("http://")) {
            this.url = url;
        } else {
            this.url = "https://" + url;
        }
        this.typeOfNote = Notes.WEBLINK;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public Notes getType() {
        return this.typeOfNote;
    }

    /**
     * @return the URL
     */
    public String getURL() {
        return this.url;
    }

    @Override
    public String toString() {
        return "WebLink{" +
                "title='" + title + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", url='" + url + '\'' +
                ", typeOfNote=" + typeOfNote +
                '}';
    }
}
