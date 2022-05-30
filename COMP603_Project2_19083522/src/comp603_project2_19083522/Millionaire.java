/*
 * Liam Carter 19083522
 * COMP603
 * My program for the assignment is an attempt at a "Who Wants to Be a Millionaire?" game
 * This is the main class which stores the ten questions in an array as an object the CUI has been given it's own object
 */
package comp603_project2_19083522;

public class Millionaire {
    
    public QuestionClass[] questions = new QuestionClass[10];
    public MillionaireIO IO;
    public int score = 0;
    public String user;
    public int lives = 1;
    public GUI GUI;
    public int QNum = 0; //This number is used to keep track of the questions

    private Lifeline5050 line5050;
    private LifelineDD lineDD;
    public LifeLine[] lifeLines = new LifeLine[2];

    public Millionaire(String user, GUI gui, DataBase db)
    {
        this.user = user;
        this.GUI = gui;
        
        for(int counter = 0; counter < this.questions.length; counter++)
        {
            this.questions[counter] = new QuestionClass(db); 
        }

        for(QuestionClass question : this.questions)
        {
            for(QuestionClass comparison : this.questions)
            {
                if(compareStrings(question.getQuestion(), comparison.getQuestion()))
                {
                    question = new QuestionClass(db);
                }
            }
        }
        
        this.line5050 = new Lifeline5050(this.GUI);
        this.lineDD = new LifelineDD(this.GUI);

        this.lifeLines[0] = this.line5050;
        this.lifeLines[1] = this.lineDD;

        this.IO = new MillionaireIO(user);
    }

    //Allows for easy way to compare strings whenever it may be needed
    public static boolean compareStrings(String answer, String input)
    {
        if(answer.toUpperCase().equals(input.trim().toUpperCase()))
        {
            return true;
        }
        else
        {
            return false;
        } 
    }

    //Asks the user a question and handles wrong and correct answers (This whole method will need to be moved to the GUI)
    
    public String askQuestion()
    {
        return "Question : " + (this.QNum + 1) + " " + this.questions[this.QNum].getQuestion();       
    }
    
    public boolean AnswerQuestion(String input)
    {
        if(Millionaire.compareStrings(this.getCurrentQ().answer, input))
            return true;
        
        return false;
    }

    //following methods handle the game's scoring

    public void addScore()
    {
        this.score = this.score + 100000;
        this.IO.score = this.score;
    }
    
    //Getter
    public QuestionClass getCurrentQ() //Makes it easier to get the current question
    {
        return this.questions[this.QNum];
    }
}
