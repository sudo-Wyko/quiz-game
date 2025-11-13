public class Quiz {
    public static void main(String[] args) throws Exception {
        Question questions[];
        Parser parser = new Parser();

        questions = parser.GetQuestions("res/questions/questions.txt");

        int random_index = (int) (Math.random() * 69);
        System.out.println("Question #" + random_index);
        questions[random_index].Print();

    }
}
