package org.alvee;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public class Assignment {

    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;

    private static int nextId = 1;

    // Constructor
    public Assignment(String assignmentName, double weight) {
        this.assignmentName = assignmentName;
        this.weight = weight;

        this.assignmentId = String.format("A%03d", nextId);
        nextId++;

        this.scores = new ArrayList<>();
    }

    // calculate average score
    public void calcAssignmentAvg() {
        if (scores.size() == 0) {
            System.out.println("No scores available for " + assignmentName);
            return;
        }

        double sum = 0;
        for (int s : scores) {
            sum += s;
        }

        double avg = sum / scores.size();
        System.out.println("Average score for " + assignmentName + ": " + avg);
    }

    // generate random scores for all students
    public void generateRandomScore(int numberOfStudents) {
        Random rand = new Random();

        for (int i = 0; i < numberOfStudents; i++) {

            int bucket = rand.nextInt(11); // 0–10
            int score;

            if (bucket == 0) {
                score = rand.nextInt(60); // [0, 60)
            } else if (bucket == 1 || bucket == 2) {
                score = 60 + rand.nextInt(10); // [60, 70)
            } else if (bucket == 3 || bucket == 4) {
                score = 70 + rand.nextInt(10); // [70, 80)
            } else if (bucket >= 5 && bucket <= 8) {
                score = 80 + rand.nextInt(10); // [80, 90)
            } else {
                score = 90 + rand.nextInt(11); // [90, 100]
            }

            scores.add(score);
        }
    }

    // toString
    @Override
    public String toString() {
        return "Assignment{"
                + "assignmentId='" + assignmentId + '\''
                + ", assignmentName='" + assignmentName + '\''
                + ", weight=" + weight
                + '}';
    }
}