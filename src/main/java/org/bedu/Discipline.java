package org.bedu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Discipline {

    static ArrayList<Discipline> data;

    int id;
    String name;
    String slug;
    String description;

    public Discipline(
            String name,
            String slug,
            String description
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public Discipline add() {
        data.add(this);
        Discipline.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Discipline discipline = Discipline.getByName(this.name);

        if (discipline != null) {
            data.remove(this);
            Discipline.saveDataToFile();
        }
        else
            throw new Exception("Discipline not found");
    }

    public void save(
            String name,
            String slug,
            String description
    ) {
        try {
            this.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!name.equals(""))
            this.name = name;

        if (!slug.equals(""))
            this.slug = slug;

        if (!description.equals(""))
            this.description = description;

        data.add(this);
    }

    public static Discipline getByName(String name) {
        for (Discipline discipline: data) {
            if (discipline.name.equals(name))
                return discipline;
        }

        return null;
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./Disciplines");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interviewer.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}