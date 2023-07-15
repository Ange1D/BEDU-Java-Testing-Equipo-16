package org.bedu;

import java.util.ArrayList;

public class Interview {

    static ArrayList<Interview> data;

    int id;
    Candidate candidate;
    Interviewer Interviewer;
    Technology technology;
    Discipline discipline;

    public Interview(
            Candidate candidate,
            Interviewer Interviewer,
            Technology technology,
            Discipline discipline
    ) {
        this.id = data.size() + 1;
        this.candidate = candidate;
        this.Interviewer = Interviewer;
        this.technology = technology;
        this.discipline = discipline;
    }
}
