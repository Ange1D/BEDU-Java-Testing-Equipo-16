package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InterviewTypeTest {

    static String existingInterviewTypeName = "Type name";
    static String existingInterviewTypeSlug = "Slug";
    static String existingInterviewTypeDescription = "Description";

    @BeforeEach
    public void setUp() throws Exception {
        InterviewType.data = new ArrayList<>();

        InterviewType.data.add(new InterviewType(
                existingInterviewTypeName,
                existingInterviewTypeSlug,
                existingInterviewTypeDescription
        ));
    }

    @Test
    public void add() {
        InterviewType interviewType = new InterviewType(
                "Test",
                "slug",
                "description"
        );

        interviewType.add();

        int expectedId = InterviewType.data.size();
        assertEquals(
                expectedId,
                interviewType.id,
                "interviewType ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = InterviewType.data.size();
        String expectedSlug = "New";
        InterviewType existingInterviewerType = InterviewType.data.get(0);
        System.out.println(InterviewType.data.size());
        existingInterviewerType.save("", expectedSlug, "");

        int newListSize = InterviewType.data.size();
        System.out.println(InterviewType.data.size());
        int lastInterviewerIndex = newListSize - 1;
        InterviewType latestInterviewType = InterviewType.data.get(lastInterviewerIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedSlug,
                latestInterviewType.slug,
                "Slug should have been updated"
        );
        assertEquals(
                existingInterviewerType.name,
                latestInterviewType.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByName() {
        InterviewType result = InterviewType.getByName(existingInterviewTypeName);

        assertNotNull(result, "Interviewer should be found");
        assertEquals(
                existingInterviewTypeSlug,
                result.slug,
                "Unexpected interviewType slug"
        );
        assertEquals(
                existingInterviewTypeDescription,
                result.description,
                "Unexpected interviewType description"
        );
    }

    @Test
    public void getByNonExistingName() {
        String nonExistingName = "Non existing";

        InterviewType result = InterviewType.getByName(nonExistingName);

        assertNull(result, "interviewType should not be found");
    }

    @Test
    public void delete() {
        InterviewType existingInterviewType = InterviewType.data.get(0);

        int expectedSize = InterviewType.data.size() - 1;

        try {
            existingInterviewType.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = InterviewType.data.size();

        assertEquals(
                expectedSize,
                actualSize,
                "List should be smaller"
        );
    }
}