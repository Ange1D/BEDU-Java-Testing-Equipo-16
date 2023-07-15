package org.bedu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Technology {

    static ArrayList<Technology> data;

    int id;
    String name;
    String slug;
    String description;

    public Technology(
            String name,
            String slug,
            String description
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }
    public Technology add() {
        data.add(this);
        Technology.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Technology technology = Technology.getByName(this.name);

        if (technology != null) {
            data.remove(this);
            Technology.saveDataToFile();
        }
        else
            throw new Exception("Technology not found");
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

    public static Technology getByName(String name) {
        for (Technology technology: data) {
            if (technology.name.equals(name))
                return technology;
        }

        return null;
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./Technologies");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interviewer.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
