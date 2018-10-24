package model.database;

import model.INote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBData implements INoteCRUD {

    private Connection conn;

    public DBData()
    {
        try {
            //get connected to database
            this.conn = DriverManager.getConnection("jdbc:sqlite:notesDB.sqlite");
            Class.forName("org.sqlite.JDBC"); // fix our project path

            System.out.println("Connected to notesDB!");
        } catch (SQLException | ClassNotFoundException e) {
            //rethrow exception if cannot connect
            throw new IllegalStateException("Cannot connect to DB: " + e.getMessage());
        }
    }

    @Override
    public void addNote(INote note)
    {

    }

    @Override
    public void removeNote(INote note)
    {

    }

    @Override
    public void updateNote(INote note)
    {

    }

    @Override
    public List<INote> getNotes()
    {
        return null;
    }
}
