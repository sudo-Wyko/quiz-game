import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public Question[] GetQuestions(String filepath) {
        Question[] theoretical_questions = new Question[70];

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String rline;

            int question_index = 0;
            while ((rline = reader.readLine()) != null) {
                String[] fields = rline.split("@");

                Lesson lesson = Lesson.valueOf(fields[1]);
                String prompt = fields[3];
                String[] choices = { fields[4], fields[5], fields[6], fields[7] };
                char correct_ans = fields[8].charAt(0);

                theoretical_questions[question_index] = new Question(lesson, prompt, choices, correct_ans);
                question_index++;
            }
        } catch (IOException e) {
            System.out.println("Parsing error.");
            e.printStackTrace();
        }

        return theoretical_questions;
    }

}
