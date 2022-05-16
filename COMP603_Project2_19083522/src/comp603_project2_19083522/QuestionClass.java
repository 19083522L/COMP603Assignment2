/*
 * Liam Carter 19083522
 * COMP603
 * This class will store the question's answers and question string
 * The quesion will have 4 options for answers and those options will be orderd randomly
 */
package comp603_project2_19083522;
import java.util.Random;

public class QuestionClass {
    
    public String question;
    public String[] options = new String[4];
    public String answer;
    public String falseAnswer; //This is for the 50/50 lifeline
    public int attempts = 1; //This is for the double dip lifeline

    public QuestionClass()
    {
        this.makeQuestion();
        this.orderOptions();
        this.pickFalseOption(options);
    }   

    //This method is used for deciding which out of the preset questions is used for this object

    public void makeQuestion()
    {
        Random rand = new Random();
        int num = rand.nextInt((20));
        
        if(num == 0)
        {
            this.question = "What is the capital city of Germany? ";
            this.answer = "Berlin";      
            this.options[0] = "Berlin";
            this.options[1] = "Hamburg";
            this.options[2] = "Munich";   
            this.options[3] = "Dresden";
        }   
        else if(num == 1)
        {
            this.question = "What is the pH level of pure water? ";
            this.answer = "7";
            this.options[0] = "7";
            this.options[1] = "4";
            this.options[2] = "8";   
            this.options[3] = "6";
        } 
        else if(num == 2)
        {
            this.question = "What is the largest planet in our solar system? ";
            this.answer = "Jupiter";
            this.options[0] = "Saturn";
            this.options[1] = "Jupiter";
            this.options[2] = "Neptune";   
            this.options[3] = "Uranus";        
        }
        else if(num == 3)
        {
            this.question = "What is the colour of emerald? ";
            this.answer = "Green";
            this.options[0] = "Green";
            this.options[1] = "Blue";
            this.options[2] = "Yellow";   
            this.options[3] = "Red";        
        } 
        else if(num == 4)
        {
            this.question = "How many legs does a lobster have? ";
            this.answer = "10";
            this.options[0] = "10";
            this.options[1] = "8";
            this.options[2] = "6";   
            this.options[3] = "12";        
        } 
        else if(num == 5)
        {
            this.question = "Which type of fish is Nemo? ";
            this.answer = "Clownfish";
            this.options[0] = "Clownfish";
            this.options[1] = "Goldfish";
            this.options[2] = "Guppy";   
            this.options[3] = "Oscar";        
        } 
        else if(num == 6)
        {
            this.question = "Which is the worlds largest ocean? ";
            this.answer = "Pacific ocean";
            this.options[0] = "Pacific ocean";
            this.options[1] = "Atlantic ocean";
            this.options[2] = "Indian ocean";   
            this.options[3] = "Southern ocean";        
        } 
        else if(num == 7)
        {
            this.question =  "How many rings are in the Olympics logo? ";
            this.answer = "5";
            this.options[0] = "5";
            this.options[1] = "4";
            this.options[2] = "6";   
            this.options[3] = "3";  
        }
        else if(num == 8)
        {
            this.question = "What is the longest river int the world? ";
            this.answer = "The Nile";
            this.options[0] = "The Nile";
            this.options[1] = "The Amazon River";
            this.options[2] = "The Yangtze River";   
            this.options[3] = "The Yellow River";
        }
        else if(num == 9)
        {
            this.question = "What colour are a zebra's strips when they are born? ";
            this.answer = "Brown";
            this.options[0] = "Brown";
            this.options[1] = "Black";
            this.options[2] = "White";   
            this.options[3] = "Grey";
        }
        else if(num == 10)
        {
            this.question = "How many edges does a cube have? ";
            this.answer = "12";
            this.options[0] = "12";
            this.options[1] = "8";
            this.options[2] = "14";   
            this.options[3] = "6";
        }   
        else if(num == 11)
        {
            this.question = "What species of bird has the largest population? ";
            this.answer = "Chicken";
            this.options[0] = "Chicken";
            this.options[1] = "Duck";
            this.options[2] = "Pigeon";   
            this.options[3] = "Dove";
        }
        else if(num == 12)
        {
            this.question = "What is the largest animal in the world? ";
            this.answer = "Blue Whale";
            this.options[0] = "Blue Whale";
            this.options[1] = "Sperm Whale";
            this.options[2] = "Humpback Whale";   
            this.options[3] = "Gray Whale";
        }   
        else if(num == 13)
        {
            this.question = "How many kilometres are between the Earth and the Moon? ";
            this.answer = "384400";
            this.options[0] = "384400";
            this.options[1] = "284400";
            this.options[2] = "484400";   
            this.options[3] = "184400";
        }
        else if(num == 14)
        {
            this.question = "How many bones in a human body? ";
            this.answer = "206";
            this.options[0] = "206";
            this.options[1] = "192";
            this.options[2] = "210";   
            this.options[3] = "223";
        }
        else if(num == 15)
        {
            this.question = "How many bits are in a byte? ";
            this.answer = "8";
            this.options[0] = "8";
            this.options[1] = "4";
            this.options[2] = "6";   
            this.options[3] = "16";
        }   
        else if(num == 16)
        {
            this.question = "Which country has the most sheep per capita? ";
            this.answer = "Falkland Islands";
            this.options[0] = "New Zealand";
            this.options[1] = "Falkland Islands";
            this.options[2] = "Whales";   
            this.options[3] = "Australia";
        }
        else if(num == 17)
        {
            this.question = "What was the first AI programming language called? ";
            this.answer = "Lisp";
            this.options[0] = "Lisp";
            this.options[1] = "Java";
            this.options[2] = "Julia";   
            this.options[3] = "R";
        }   
        else if(num == 18)
        {
            this.question = "When was the Moon landing? ";
            this.answer = "July 16 1969";
            this.options[0] = "July 16 1969";
            this.options[1] = "June 16 1969";
            this.options[2] = "July 16 1970";   
            this.options[3] = "June 16 1970";
        }
        else if(num == 19)
        {
            this.question = "What metal has the lowest melting point? ";
            this.answer = "Mercury";
            this.options[0] = "Mercury";
            this.options[1] = "Francium";
            this.options[2] = "Cesium";   
            this.options[3] = "Gallium";
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
            
            if(!options[num].equals(this.getAnswer()))
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

    public String getAnswer()
    {
        return this.answer;
    }

    public String getFalseOption()
    {
        return this.falseAnswer;
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
