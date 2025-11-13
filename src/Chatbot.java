import java.util.ArrayList;
import java.util.List;

public class Chatbot {

    private class Command {
        String name;
        boolean isAvailable;

        public Command(String name) {
            this.name = name;
            this.isAvailable = true;
        }

        public String GetName() {
            return name;
        }

        public boolean GetAvailability() {
            return isAvailable;
        }

        public void SetAvailability(boolean bool) {
            isAvailable = bool;
        }
    };

    private String name;
    private Lesson strong_subject;
    private Lesson weak_subject;
    private char answer;
    private Command[] commands = { new Command("Ask"), new Command("CopyPaste"), new Command("Save") };

    public Chatbot(String name, Lesson strong_subject, Lesson weak_subject) {
        this.name = name;
        this.strong_subject = strong_subject;
        this.weak_subject = weak_subject;
        this.answer = ' ';
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
            answer = question.GetAnswer();
        }

        else {
            char correct_answer = question.GetAnswer();
            ArrayList<Character> options = new ArrayList<>(List.of('A', 'B', 'C', 'D'));
            options.remove((Character) correct_answer);
            answer = options.get((int) (Math.random() * options.size()));
        }

    }

    public void CommandAsk() {
        if (commands[0].GetAvailability() == false)
            return;

        System.out.println(name + ": My answer is " + answer);
        return;
    }

    public void UseCommand(Command command) {
        if (command.GetAvailability() == false)
            return;

        if (command.GetName() == "Ask") {
            System.out.println(name + ": My answer is " + answer);
            return;
        }

        else if (command.GetName() == "CopyPaste") {

        }

        else if (command.GetName() == "Save") {

        }

        command.SetAvailability(false);
    }

}
