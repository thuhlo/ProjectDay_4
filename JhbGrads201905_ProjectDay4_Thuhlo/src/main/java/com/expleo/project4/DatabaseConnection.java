package com.expleo.project4;

import net.thucydides.core.annotations.Step;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection
{

    //Database connection object
    static Connection connection;

    //Find if database exists in the project root folder, file name as parameter
    public static boolean databaseFound(String dbName)throws NullPointerException
    {
        File myFile;
        boolean found = false;
        myFile = new File(dbName + ".db");
        //Please ignore used for debugging
        //System.out.println(dbName+".db");

        try
        {
            if(myFile.exists())
            {
                found = true;
            }

        }
        catch (Exception e)
        {
            found =false;
        }

        return found;
    }


    //Check and close Connection to database
    public static boolean closeDatabaseConnection()
    {
        boolean closed = false;

        try
        {
            if(connection != null)
            {
                connection.close();
                closed = true;
            }
        }
        catch (SQLException e)
        {
            closed = false;
        }
        return closed;
    }

    //Create a connection to the database taking database name as parameter
    public static void createConnection(String dbName)
    {

        final String DB_URL = "jdbc:sqlite:"+ dbName + ".db";
        final String USER = "";
        final String PASS = "";

        try
        {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            //Please ignore used for debugging
            //System.out.println(connection.isClosed());
        }
        catch (SQLException e)
        {
            e.getMessage();
            System.out.println("DB file does not exist");
        }
        catch (NullPointerException e)
        {
            e.getMessage();
        }
    }


    //Check if artist table has any data(currently not used)
    public static ResultSet getData()
    {
        Statement stmnt;
        String sql = "Select * from artists";
        ResultSet rs = null;

        try
        {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);
        }
        catch (SQLException e)
        {
            rs = null;
            //e.getMessage();
        }
        catch (NullPointerException e)
        {
            rs = null;
            //e.getMessage();
        }
        return rs;
    }


    //Find if blog database exists - Check for Blog Database
    public static boolean jsonDatabaseFound(String jsonDbName)
    {
        File jSonDBfile;
        boolean found = false;

        try
        {
            jSonDBfile = new File(jsonDbName + ".json");
            //Please ignore used for debugging
            // System.out.println(jsonDbName+".json");
            if(jSonDBfile.exists())
            {
                found = true;
            }
        }
        catch (Exception e)
        {
            found = false;
        }

        return found;
    }

    //Check if Artist table is readable
    public static boolean canReadArtistTable()
    {
        Statement stmnt;
        boolean canRead = false;
        String sql = "Select * from artists";
        try
        {
            stmnt = connection.createStatement();
            canRead = stmnt.execute(sql);
        }
        catch (SQLException e)
        {
            canRead = false;
        }
        return canRead;
    }

    //Check if can read Album table
    public static boolean canReadAlbumTable()
    {
        String sql = "Select * from albums";
        boolean canRead = false;
        Statement stmnt;

        try
        {
            stmnt = connection.createStatement();
            canRead = stmnt.execute(sql);
        }
        catch (SQLException e)
        {
            canRead = false;
        }
        return canRead;
    }

    //Check if Artist Exists in database Returns Artist object
    public static Artist checkIfArtistExist(String artist)throws NullPointerException
    {
        String sql = "Select * from artists where Name ="+ "\"" +artist + "\"";
        Statement stmnt;
        ResultSet rs;
        int counter = 0;
        Artist objArtist = null;

        try
        {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);

            while(rs.next())
            {
                objArtist = new Artist(rs.getInt(1),rs.getString(2));
            }
        }
        catch (SQLException e)
        {
            objArtist = new Artist(0,"empty");
        }
        catch (NullPointerException e)
        {
            objArtist = new Artist(0,"empty");
        }

        return objArtist;
    }

    //Check if artist has Album stored in db and returns list of albums
    public static ArrayList<Albums> checkIfArtistHaveAlbum(int artistID)
    {
        Statement stmnt;
        String sql = "Select * from albums where artistid =" + artistID;
        ResultSet rs;
        int counter = 0;
        Albums objAlbums = null;
        ArrayList<Albums> arAlbums = new ArrayList<>();

        try
        {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);

            while(rs.next())
            {
                objAlbums = new Albums(rs.getInt(1),rs.getString(2),rs.getInt(3));
                arAlbums.add(objAlbums);
            }
        }
        catch (SQLException e)
        {
            objAlbums = new Albums(0,"empty",0);
            arAlbums.add(objAlbums);
           // e.getMessage();
        }
        catch (NullPointerException e)
        {
            objAlbums = new Albums(0,"empty",0);
            arAlbums.add(objAlbums);
            //e.getMessage();
        }
        return arAlbums;
    }


    //Get Artist details(Name) by artistID returns String
    public static String getArtistName(int artistId)
    {
        Statement stmnt;
        String sql = "Select * from artists where artistId =" + artistId;
        ResultSet rs;
        String artistName = null;

        try
        {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);
            artistName = rs.getString(2);
        }
        catch (SQLException e)
        {
            artistName = "No artist found in database";
        }
        return artistName;
    }

    //Delete Album from database by artistID returns boolean
    public static boolean deleteAlbumFromDB(int artistID)//Delete from Albums table
    {
        Statement stmnt;
        String sql = "Delete from albums where artistid=" + artistID;
        boolean deleted  = false;

        try
        {
            stmnt = connection.createStatement();
            deleted = stmnt.execute(sql);
        }
        catch (SQLException e)
        {
            deleted = false;
        }
        return deleted;
    }

    //Delete Artist from database by artistID returns boolean
    public static boolean deleteArtistFromDB(int artistsID)
    {
        Statement stmnt;
        boolean deleted = false;
        String sql = "Delete from artists where artistid = " + artistsID;

        try
        {
            stmnt = connection.createStatement();
            deleted = stmnt.execute(sql);
        }
        catch (SQLException e)
        {
            deleted = false;
        }
        return deleted;
    }

    //Search's for albums in database using artistID returns object of Album
    public static Albums verifyDeleteAlbums(int artistId)
    {
        Statement stmnt;
        String sql = "Select * from albums where artistId =" + artistId;
        ResultSet rs;
        Albums objAlbums = null;

        try
        {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);

            while (rs.next())
            {
                objAlbums = new Albums(rs.getInt(1),rs.getString(2),rs.getInt(3));
            }
        }
        catch (SQLException e)
        {
            objAlbums = new Albums(0,"empty",0);
        }
        return objAlbums;
    }

    //Search's for artist in database using artistID returns object of Artist
    public static Artist verifyDeleteArtist(int artistID)
    {
        Statement stmnt;
        String sql = "Select * from artists where artistId =" + artistID;
        ResultSet rs;
        Artist objArtist = null;

        try
        {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(sql);

            while (rs.next())
            {
              objArtist = new Artist(rs.getInt(1),rs.getString(2));
            }
        }
        catch (SQLException e)
        {
            objArtist = new Artist(0,"empty");
        }
        catch (NullPointerException e)
        {
            objArtist = new Artist(0,"empty");
        }
        return objArtist;
    }

}
