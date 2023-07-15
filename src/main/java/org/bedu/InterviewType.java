package org.bedu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InterviewType {

    static ArrayList<InterviewType> data;

    int id;
    String name;
    String slug;
    String description;

    public InterviewType(
            String name,
            String slug,
            String description
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public InterviewType add() {
        data.add(this);
        InterviewType.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        InterviewType interviewType = InterviewType.getByName(this.name);

        if (interviewType != null) {
            data.remove(this);
            InterviewType.saveDataToFile();
        }
        else
            throw new Exception("InterviewType not found");
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

    public static InterviewType getByName(String name) {
        for (InterviewType interviewType: data) {
            if (interviewType.name.equals(name))
                return interviewType;
        }

        return null;
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./InterviewTypes");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interviewer.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
