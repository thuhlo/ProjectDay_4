package com.expleo.project4;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Toest {
    public static void main(String[] args) throws SQLException {
        boolean found = false;
        boolean found2 = false;


        found = DatabaseConnection.databaseFound("chinook");

        System.out.println(found);

        //found2 = DatabaseConnection.jsonDatabaseFound("db");
        System.out.println("json :: " + found2);

        //Remember Error returned is bcos of no db connection
        DatabaseConnection.createConnection("chinook");

        System.out.println(DatabaseConnection.canReadArtistTable());
        System.out.println(DatabaseConnection.canReadAlbumTable());
        System.out.println(DatabaseConnection.checkIfArtistExist("Black Label Society"));
    }
}
