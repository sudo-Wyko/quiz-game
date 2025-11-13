
enum Lesson {
    IntroToProgrammingParadigms,
    ProceduralProgramming,
    FunctionalProgramming,
    OOP,
    ImperativeVsDeclarative,
    EventDrivenProgramming,
    ComponentMappings
};

public class Question {
    private Lesson lesson;
    private String prompt;
    private String[] options;
    private char correct_answer;

    public Question(Lesson lesson, String prompt, String[] options, char correct_answer) {
        this.lesson = lesson;
        this.prompt = prompt;
        this.options = options;
        this.correct_answer = correct_answer;
    }

    public void Print() {
        System.out.println("Lesson: " + lesson.toString());
        System.out.println(prompt);
        System.out.println("----------------------------------");
        System.out.println(
                "A. " + options[0] + "\nB. " + options[1] + "\nC. " + options[2] + "\nD. " + options[3] + "\n");
    }

    public char GetAnswer() {
        return correct_answer;
    }

    public Lesson GetLesson() {
        return lesson;
    }

}
