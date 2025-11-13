import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Question> GetQuestions(String filepath) {

        List<Question> theoretical_questions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String rline;

            while ((rline = reader.readLine()) != null) {
                String[] fields = rline.split("@");

                Lesson lesson = Lesson.valueOf(fields[1]);
                String prompt = fields[3];
                String[] choices = { fields[4], fields[5], fields[6], fields[7] };
                char correct_ans = fields[8].charAt(0);

                theoretical_questions.add(new Question(lesson, prompt, choices, correct_ans));
            }
        } catch (IOException e) {
            System.out.println("Parsing error.");
            e.printStackTrace();
        }

        return theoretical_questions;
    }
}
