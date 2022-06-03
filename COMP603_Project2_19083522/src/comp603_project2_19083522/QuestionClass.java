/*
 * Liam Carter 19083522
 * COMP603
 * This class will store the question's answers and question string
 * The quesion will have 4 options for answers and those options will be orderd randomly
 */
package comp603_project2_19083522;
import java.sql.SQLException;
import java.sql.*;
import java.util.Random;

public class QuestionClass {
    
    public String question;
    public String[] options = new String[4];
    public String answer;
    public String falseAnswer; //This is for the 50/50 lifeline
    public int attempts = 1; //This is for the double dip lifeline
    private DataBase db;

    public QuestionClass(DataBase db)
    {
        this.db = db;
        this.makeQuestion();
        this.orderOptions();
        this.pickFalseOption(options);
    }   

    //This method is used for deciding which out of the preset questions is used for this object
    //This class also needed significant changes to accomodate the new database, which stores all of the questions

    public void makeQuestion()
    {
        Random rand = new Random();
        int num = rand.nextInt((20));
        
        try
        {
            Statement stm = this.db.conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM QUESTIONS WHERE QNUM = " + num);

            if(rs.next())
            {
                this.question = rs.getString("QUESTION");
                this.answer = rs.getString("CANSWER");
                this.options[0] = rs.getString("ANSWER1");
                this.options[1] = rs.getString("ANSWER2");
                this.options[2] = rs.getString("ANSWER3");
                this.options[3] = rs.getString("ANSWER4");
            }
        }   
        catch(SQLException e)
        {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    //Next method is used for randomizing the array order
    
    public void orderOptions()
    {
        Random rand = new Random();
        for(int counter = 0; counter < this.options.length; counter++)
        {
            int newIndex = rand.nextInt(this.options.length);
            String temp = this.options[newIndex];
            this.options[newIndex] = this.options[counter];
            this.options[counter] = temp;
        }   
    }        

    //This is used for picking the second false answer for the question 
    public void pickFalseOption(String[] options)
    {
        boolean stop = false;

        while(!stop)
        {
            Random rand = new Random();
            int num = rand.nextInt(4);
            
            if(!options[num].equals(this.answer))
            {
                this.falseAnswer = options[num];
                stop = true;
            }
        }
    }
    
    //getters

    public int getAttempts()
    {
        return this.attempts;
    }   

    public String getQuestion()
    {
        return this.question;
    }

    public String getOption(int num)
    {
        if(num > 3)
        {
            num = 3;
        }
        else if(num < 0)
        {
            num = 0;
        }

        return this.options[num];
    }
}
