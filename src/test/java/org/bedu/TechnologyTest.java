package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyTest {
    static String existingTechnologyName = "Type name";
    static String existingTechnologySlug = "Slug";
    static String existingTechnologyDescription = "Description";

    @BeforeEach
    public void setUp() throws Exception {
        Technology.data = new ArrayList<>();

        Technology.data.add(new Technology(
                existingTechnologyName,
                existingTechnologySlug,
                existingTechnologyDescription
        ));
    }

    @Test
    public void add() {
        Technology technology = new Technology(
                "Test",
                "slug",
                "description"
        );

        technology.add();

        int expectedId = Technology.data.size();
        assertEquals(
                expectedId,
                technology.id,
                "Technology ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Technology.data.size();
        String expectedSlug = "New";
        Technology existingTechnology = Technology.data.get(0);
        System.out.println(Technology.data.size());
        existingTechnology.save("", expectedSlug, "");

        int newListSize = Technology.data.size();
        System.out.println(Technology.data.size());
        int lastTechnologyIndex = newListSize - 1;
        Technology latestTechnology = Technology.data.get(lastTechnologyIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedSlug,
                latestTechnology.slug,
                "Slug should have been updated"
        );
        assertEquals(
                existingTechnology.name,
                latestTechnology.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByName() {
        Technology result = Technology.getByName(existingTechnologyName);

        assertNotNull(result, "Technology should be found");
        assertEquals(
                existingTechnologySlug,
                result.slug,
                "Unexpected Technology slug"
        );
        assertEquals(
                existingTechnologyDescription,
                result.description,
                "Unexpected Technology description"
        );
    }

    @Test
    public void getByNonExistingName() {
        String nonExistingName = "Non existing";

        Technology result = Technology.getByName(nonExistingName);

        assertNull(result, "Technology should not be found");
    }

    @Test
    public void delete() {
        Technology existingTechnology = Technology.data.get(0);

        int expectedSize = Technology.data.size() - 1;

        try {
            existingTechnology.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = Technology.data.size();

        assertEquals(
                expectedSize,
                actualSize,
                "List should be smaller"
        );
    }
}