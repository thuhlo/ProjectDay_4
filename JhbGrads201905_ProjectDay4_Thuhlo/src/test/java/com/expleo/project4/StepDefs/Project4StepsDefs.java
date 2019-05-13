package com.expleo.project4.StepDefs;

import com.expleo.project4.Albums;
import com.expleo.project4.Artist;
import com.expleo.project4.DatabaseConnection;
import com.expleo.project4.Steps.Project4Steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;

public class Project4StepsDefs {

    //Name of Database
    String databaseName = "chinook";
    //Name of JSON database
    String jsonDatabaseName = "db";
    Artist objArtist;
    String artistName = "Black Label Society";
    ArrayList<Albums> arAlbums = new ArrayList<>();
    //JSON Server URL
    String REST_url = "http://localhost:3000";
    //Expected http return code
    int expectedReturnCode = 201;

    //Steps Library
    @Steps
    Project4Steps steps;


    @Given("^The JSON db and Chinook db$")
    public void theJSONDbAndChinookDb()
    {
        //Checking if database is found
        steps.reportMessage(steps.checkDBExist(databaseName));
        steps.reportMessage(steps.checkJSONDBexist(jsonDatabaseName));

        //Checking if tables are readable
        steps.reportMessage(steps.canReadArtistTable());
        steps.reportMessage(steps.canReadAlbumsTable());
    }

    @When("^I read Artist data from Chinook$")
    public void iReadArtistDataFromChinook()
    {
        ///Get artist data from chinook into object
        objArtist = steps.getArtistDataFromDB(artistName);
    }

    @And("^extract the albums for the artist$")
    public void extractTheAlbumsForTheArtist() throws Exception {
        //Get all artist albums from database into array-list
       arAlbums = steps.getAlbums(objArtist.getArtistId());
       steps.reportMessage2(arAlbums);

    }

    @And("^transfer data from chinook to JSON db$")
    public void transferDataFromChinookToJSONDb()
    {
        //Create connection to JSON server
        steps.setUpRESTservice(REST_url);
        //Send all albums found into json db(array-list)
        steps.reportMessage(steps.submitNewComment(arAlbums,expectedReturnCode));
        //Verify the http return code(expected return code)
        //steps.reportMessage(steps.verifyReturnCode(expectedReturnCode));
    }

    @And("^Delete data from Chinook$")
    public void deleteDataFromChinook()
    {
        //Delete album from db
        steps.reportMessage(steps.deleteAlbumFromDB(objArtist.getArtistId()));
        //Delete artist from db
        steps.reportMessage(steps.deleteArtistFromDB(objArtist.getArtistId()));

    }

    @Then("^the transfer and delete are both succesful$")
    public void theTransferAndDeleteAreBothSuccesful()
    {
        //Confirm that albums is deleted
        steps.reportMessage(steps.verifyAlbumDelete(objArtist.getArtistId()));
        //Confirm that artist is deleted
        steps.reportMessage(steps.verifyArtistDelete(objArtist.getArtistId()));
        //Confirm that connection to db is closed
        steps.reportMessage(steps.verifyConnectionsClosed());
    }
}
