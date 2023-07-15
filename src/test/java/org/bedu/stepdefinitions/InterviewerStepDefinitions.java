package org.bedu.stepdefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bedu.Interviewer;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InterviewerStepDefinitions {


    Interviewer interviewer;

    @BeforeEach
    public void setUp() throws Exception {
         interviewer = new Interviewer("First","User","first@email.com",true,true);
    }



    @Given("a interviewer with name {string} Last name {string} Email {string} is Active {string} and is Admin {string}")
    public void createInterviewer(String name, String lastName,String email,String isActive,String isAdmin){
        assertNotNull(name);
        assertNotNull(lastName);
        assertNotNull(email);
        assertNotNull(isActive);
        assertNotNull(isAdmin);

        boolean isActiveValue = Boolean.parseBoolean(isActive);
        boolean isAdminValue = Boolean.parseBoolean(isAdmin);
        Interviewer.data = new ArrayList<>(1);

        interviewer = new Interviewer(name, lastName,email,isActiveValue,isAdminValue);

    }

    @When("the user save interviewer")
    public void AddInterviewer() {
        interviewer.add();

        int expectedId = Interviewer.data.size();
        assertEquals(expectedId, interviewer.id, "Interviewer ID should be the new List's size");

    }

    @Then("the system should save interviewer Email {string}")
    public void interviewerAdded(String email) {
        Interviewer result = Interviewer.getByEmail(email);
        assertNotNull(result);
    }

}
