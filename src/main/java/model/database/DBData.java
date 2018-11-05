package model.database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBData implements INoteCRUD {

    private Connection conn;

    private static Map<Notes, String> notesToTableMap = new HashMap<>();


    public DBData()
    {
        try {
            //get connected to database
            this.conn = DriverManager.getConnection("jdbc:sqlite:notesDB.sqlite");
            Class.forName("org.sqlite.JDBC"); // fix our project path

            System.out.println("Connected to notesDB.sqlite!");
        } catch (SQLException | ClassNotFoundException e) {
            //rethrow exception if cannot connect
            throw new IllegalStateException("Cannot connect to DB: " + e.getMessage());
        }

        notesToTableMap.put(Notes.CODE_BLOCK, "CodeBlocksTable");
        notesToTableMap.put(Notes.QUOTATION, "QuotesTable");
        notesToTableMap.put(Notes.WEBLINK, "WebLinksTable");
        notesToTableMap.put(Notes.TO_DO, "ToDoListTable");
    }

    @Override
    public void createNote(INote note)
    {
        if (note.getType() == Notes.QUOTATION)
            createQuote(note);
        else if (note.getType() == Notes.CODE_BLOCK)
            createCodeBlock(note);
        else if(note.getType() == Notes.WEBLINK)
            createWebLink(note);
        else if(note.getType() == Notes.TO_DO)
            createToDoList(note);
    }

    private void createWebLink(INote note)
    {
        WebLink link = (WebLink) note;

        try{
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO WebLinksTable VALUES(DATETIME('now'), '" +
                    link.getTitle() + "', '" +
                    link.getURL() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createToDoList(INote note)
    {
        ToDo toDo = (ToDo) note;

        try{
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO ToDoListTable VALUES(DATETIME('now'), '" +
                    toDo.getTitle() + "', null)");

            ResultSet listIDs = stmt.getGeneratedKeys();
            listIDs.next();
            int listID = listIDs.getInt(1);

            for(ToDoItem task : toDo.getToDoItems()) {
                stmt = conn.createStatement();
                stmt.execute("INSERT INTO ToDoItemsTable VALUES(" + listID + ", "+
                        task.getToDo() + ", " +
                        task.isCompleted() + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createCodeBlock(INote note)
    {
        CodeBlock quote = (CodeBlock) note;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO CodeBlocksTable VALUES(DATETIME('now'), '" +
                    quote.getTitle() + "', '" +
                    quote.getCode() + "')");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot insert code block note: " + e.getMessage());
        }
    }

    private void createQuote(INote note)
    {
        Quotation quote = (Quotation) note;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO QuotesTable VALUES(DATETIME('now'), '" +
                    quote.getTitle() + "', '" +
                    quote.getQuote() + "', '" +
                    quote.getAuthor() + "')");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot insert quote note: " + e.getMessage());
        }
    }

    @Override
    public boolean removeNote(INote note)
    {
        try {
            removeNoteFromTable(note, notesToTableMap.get(note.getType()));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void removeNoteFromTable(INote note, String table) throws SQLException
    {
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM " + table + " WHERE DateCreated = " + note.getDateCreated());
    }

    @Override
    public void updateNote(INote note)
    {
        try {
            if (note.getType() == Notes.QUOTATION) {
                updateQuote(note);
            } else if (note.getType() == Notes.CODE_BLOCK) {
                updateCodeBlock(note);
            } else if (note.getType() == Notes.TO_DO) {
                updateToDoList(note);
            } else if (note.getType() == Notes.WEBLINK) {
                updateWebLink(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateWebLink(INote note) throws SQLException
    {
        WebLink link = (WebLink) note;

        Statement stmt = conn.createStatement();

        stmt.execute("UPDATE WebLinksTable SET Title = " + link.getTitle() + ", " +
                "URL = " + link.getURL() +
                "WHERE DateCreated = " + link.getDateCreated());
    }

    private void updateToDoList(INote note) throws SQLException
    {
        ToDo toDo = (ToDo) note;

        Statement stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery("SELECT ListID FROM ToDoListTable " +
                "WHERE DateCreated = " + toDo.getDateCreated());

        while(results.next()){
            int listID = results.getInt("ListID");
            stmt.execute("DELETE FROM ToDoItemsTable WHERE ListID = " + listID);

            for(ToDoItem task : toDo.getToDoItems()){
                stmt.execute("INSERT INTO ToDoItemsTable VALUES(" + listID + ", " +
                        task.getToDo() + ", " +
                        task.isCompleted() + ")");
            }
        }
    }


    private void updateCodeBlock(INote note) throws SQLException
    {
        CodeBlock codeBlock = (CodeBlock) note;

        Statement stmt = conn.createStatement();

        stmt.execute("UPDATE CodeBlocksTable SET Title = " + codeBlock.getTitle() + ", " +
                "Code = " + codeBlock.getCode() +
                "WHERE DateCreated = " + codeBlock.getDateCreated());
    }

    private void updateQuote(INote note) throws SQLException
    {
        Quotation quote = (Quotation) note;

        Statement stmt = conn.createStatement();

        stmt.execute("UPDATE QuotesTable SET Title = " + quote.getTitle() + ", " +
                "Quote = " + quote.getQuote() + ", " +
                "Author = " + quote.getAuthor() + ", " +
                "WHERE DateCreated = " + quote.getDateCreated());
    }

    @Override
    public List<INote> getNotes()
    {
        List<INote> noteList = new ArrayList<>();

        try {
            addQuotesToList(noteList);
            addCodeBlocksToList(noteList);
            addWebLinksToList(noteList);
            addToDosToList(noteList);
            noteList.sort(new SortByDateTime()); //TODO verify that SortByDateTime is correct
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot retrieve note: " + e.getMessage());
        }

        return noteList;
    }

    public List<INote> getNotes(Notes typeOfNote)
    {
        List<INote> notes = new ArrayList<>();
        try {
            if (typeOfNote == Notes.QUOTATION)
                addQuotesToList(notes);
            else if (typeOfNote == Notes.CODE_BLOCK)
                addCodeBlocksToList(notes);
            else if (typeOfNote == Notes.TO_DO) {
                addToDosToList(notes);
            } else if (typeOfNote == Notes.WEBLINK) {
                addWebLinksToList(notes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    private void addCodeBlocksToList(List<INote> listOfNotes) throws SQLException
    {
        ResultSet results = conn.createStatement().executeQuery(
                "SELECT DateCreated, title, Code FROM CodeBlocksTable");

        while (results.next()) //move to the next row and return true if successful
        {
            CodeBlock note = new CodeBlock(
                    results.getString("title"),
                    results.getString("DateCreated"),
                    results.getString("Code")
            );
            listOfNotes.add(note);
        }
    }

    private void addQuotesToList(List<INote> listOfNotes) throws SQLException
    {
        ResultSet results = conn.createStatement().executeQuery(
                "SELECT DateCreated, title, Quote, Author FROM QuotesTable");

        while (results.next()) //move to the next row and return true if successful
        {
            Quotation note = new Quotation(
                    results.getString("title"),
                    results.getString("DateCreated"),
                    results.getString("Quote"),
                    results.getString("Author")
            );
            listOfNotes.add(note);
        }
    }

    private void addToDosToList(List<INote> listOfNotes) throws SQLException
    {
        ResultSet results = conn.createStatement().executeQuery(
                "SELECT DateCreated, Title, ListID FROM ToDoListTable");

        while (results.next()) //move to the next row and return true if successful
        {
            int id = results.getInt("ListID");
            ResultSet toDoResults = conn.createStatement().executeQuery(
                    "SELECT ToDo, isCompleted FROM ToDoItemsTable WHERE ListID = " + id + ";"
            );

            List<ToDoItem> individualToDoItems = new ArrayList<>();

            while (toDoResults.next()) {
                ToDoItem item = new ToDoItem(
                        toDoResults.getString("ToDo"),
                        toDoResults.getBoolean("isCompleted")
                );
                individualToDoItems.add(item);
            }

            ToDo toDoList = new ToDo(
                    results.getString("Title"),
                    results.getString("DateCreated"),
                    individualToDoItems
            );

            listOfNotes.add(toDoList);
        }
    }

    private void addWebLinksToList(List<INote> listOfNotes) throws SQLException
    {
        ResultSet results = conn.createStatement().executeQuery(
                "SELECT DateCreated, title, URL FROM WebLinksTable");

        while (results.next()) //move to the next row and return true if successful
        {
            WebLink note = new WebLink(
                    results.getString("title"),
                    results.getString("DateCreated"),
                    results.getString("url")
            );
            listOfNotes.add(note);
        }
    }
}