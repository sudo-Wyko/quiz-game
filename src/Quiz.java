public class Quiz {
    public static void main(String[] args) throws Exception {
        Question questions[];
        Parser parser = new Parser();
        Chatbot chatgpt = new Chatbot("ChatGPT");
        char answer = ' ';

        questions = parser.GetQuestions("res/questions/questions.txt");

        int random_index = (int) (Math.random() * 69);
        System.out.println("Question #" + random_index);

        chatgpt.Answer(questions[random_index].GetLesson(), questions[random_index]);
        questions[random_index].Print();
        answer = chatgpt.CommandAsk();
        System.out.println();
        System.out.println("You answered: " + answer);
        System.out.println("The correct answer is " + questions[random_index].GetAnswer());
        chatgpt.PrintStats();
    }
}
