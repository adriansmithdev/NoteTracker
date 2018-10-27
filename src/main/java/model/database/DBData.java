package model.database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBData implements INoteCRUD {

    private Connection conn;

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
    }

    @Override
    public void addNote(INote note)
    {
        if (note.getType() == Notes.QUOTATION)
            addNoteToQuotesTable(note);
        else if (note.getType() == Notes.CODE_BLOCK)
            addNoteToCodeBlocksTable(note);
    }

    private void addNoteToCodeBlocksTable(INote note)
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

    private void addNoteToQuotesTable(INote note)
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

        return false;
    }


    @Override
    public void updateNote(INote note)
    {

    }

    @Override
    public List<INote> getNotes()
    {
        List<INote> notes = new ArrayList<>();

        try {
            addQuotesToList(notes);
            addCodeBlocksToList(notes);
            addWebLinksToList(notes);
            addToDosToList(notes);
            notes.sort(new SortByDateTime()); //TODO verify that SortByDateTime is correct
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot retrieve note: " + e.getMessage());
        }

        return notes;
    }

    public List<INote> getNotes(Notes typeOfNote)
    {
        List<INote> notes = new ArrayList<>();
        try {
            if(typeOfNote == Notes.QUOTATION)
                addQuotesToList(notes);
            else if(typeOfNote == Notes.CODE_BLOCK)
                addCodeBlocksToList(notes);
            else if(typeOfNote == Notes.TO_DO){
                addToDosToList(notes);
            }else if(typeOfNote == Notes.WEBLINK){
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

            while(toDoResults.next()){
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
