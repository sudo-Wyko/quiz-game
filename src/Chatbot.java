import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {

    private String name;
    private Lesson strong_subject;
    private Lesson weak_subject;
    private char bot_answer;
    private boolean isAvailable;

    Scanner scanner = new Scanner(System.in);

    public Chatbot(String name) {
        this.name = name;
        this.bot_answer = ' ';
        this.isAvailable = true;
        AssignStrengthAndWeakness();
    }

    public String GetName() {
        return name;
    }

    public Lesson GetStrength() {
        return strong_subject;
    }

    public Lesson GetWeakness() {
        return weak_subject;
    }

    public boolean GetAvailability() {
        return isAvailable;
    }

    public void SetAvailability(boolean bool) {
        isAvailable = bool;
    }

    public void AssignStrengthAndWeakness() {
        int strong_pick = (int) (Math.random() * Lesson.values().length);
        strong_subject = Lesson.values()[strong_pick];

        int weak_pick;
        do {
            weak_pick = (int) (Math.random() * Lesson.values().length);
        } while (weak_pick == strong_pick);

        weak_subject = Lesson.values()[weak_pick];
    }

    public void PrintStats() {
        System.out.println("-------------- Chatbot Stats ----------------");
        System.out.println("Name:" + name);
        System.out.println("Specialization: " + strong_subject);
        System.out.println("Weak in: " + weak_subject);
    }

    public void Answer(Lesson lesson, Question question) {
        // this dictates the range wherein the answer will fail, so a 90% success rate
        // will have a threshold of 10.
        int failure_threshold = 50;

        if (lesson == strong_subject) {
            failure_threshold = 10;
        } else if (lesson == weak_subject) {
            failure_threshold = 90;
        }

        int probability = (int) (Math.random() * 100);

        if (probability >= failure_threshold) {
            bot_answer = question.GetAnswer();
        }

        else {
            char correct_answer = question.GetAnswer();
            ArrayList<Character> options = new ArrayList<>(List.of('A', 'B', 'C', 'D'));
            options.remove((Character) correct_answer);
            bot_answer = options.get((int) (Math.random() * options.size()));
        }

    }

    public char CommandAsk() {
        System.out.println(name + ": My answer is " + bot_answer);

        System.out.print("Do you accept their answer? (y/n): ");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("y"))
            return bot_answer;
        else {
            System.out.print("What is your answer? ");
            return scanner.nextLine().toUpperCase().charAt(0);
        }
    }

    public char CommandCopyPaste() {
        return bot_answer;
    }

    public char CommandSave() {
        System.out.println(name + ": My answer is " + bot_answer);
        return bot_answer;
    }

}
