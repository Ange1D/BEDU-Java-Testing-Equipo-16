package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DisciplineTest {

    static String existingDisciplineName = "Type name";
    static String existingDisciplineSlug = "Slug";
    static String existingDisciplineDescription = "Description";

    @BeforeEach
    public void setUp() throws Exception {
        Discipline.data = new ArrayList<>();

        Discipline.data.add(new Discipline(
                existingDisciplineName,
                existingDisciplineSlug,
                existingDisciplineDescription
        ));
    }

    @Test
    public void add() {
        Discipline discipline = new Discipline(
                "Test",
                "slug",
                "description"
        );

        discipline.add();

        int expectedId = Discipline.data.size();
        assertEquals(
                expectedId,
                discipline.id,
                "Discipline ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Discipline.data.size();
        String expectedSlug = "New";
        Discipline existingDiscipline = Discipline.data.get(0);
        System.out.println(Discipline.data.size());
        existingDiscipline.save("", expectedSlug, "");

        int newListSize = Discipline.data.size();
        System.out.println(Discipline.data.size());
        int lastDisciplineIndex = newListSize - 1;
        Discipline latestDisciplineType = Discipline.data.get(lastDisciplineIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedSlug,
                latestDisciplineType.slug,
                "Slug should have been updated"
        );
        assertEquals(
                existingDiscipline.name,
                latestDisciplineType.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByName() {
        Discipline result = Discipline.getByName(existingDisciplineName);

        assertNotNull(result, "Interviewer should be found");
        assertEquals(
                existingDisciplineSlug,
                result.slug,
                "Unexpected interviewType slug"
        );
        assertEquals(
                existingDisciplineDescription,
                result.description,
                "Unexpected interviewType description"
        );
    }

    @Test
    public void getByNonExistingName() {
        String nonExistingName = "Non existing";

        Discipline result = Discipline.getByName(nonExistingName);

        assertNull(result, "Discipline should not be found");
    }

    @Test
    public void delete() {
        Discipline existingDiscipline = Discipline.data.get(0);

        int expectedSize = Discipline.data.size() - 1;

        try {
            existingDiscipline.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = Discipline.data.size();

        assertEquals(
                expectedSize,
                actualSize,
                "List should be smaller"
        );
    }
}