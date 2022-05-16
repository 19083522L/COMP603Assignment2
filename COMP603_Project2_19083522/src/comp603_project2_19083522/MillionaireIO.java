/*
 * Liam Carter 19083522
 * COMP603
 * This class will handle of the needed IO for the program, this will read the users text file at the start of the game and writes it at the end
 */
package comp603_project2_19083522;

import java.io.*;
import java.util.ArrayList;

public class MillionaireIO {

    private String user;
    public int score = 0;

    private BufferedReader reader;
    private PrintWriter writer;

    private ArrayList<String> users;
    private ArrayList<Integer> scores;
    private boolean matchFound =  false;
    
    public MillionaireIO(String user)
    {
        this.user = user;
        this.users = new ArrayList();
        this.scores = new ArrayList();

        try
        {
            this.reader = new BufferedReader(new FileReader("./resources/Carter_19083522_COMP603_USERS.txt"));
            this.read();
            this.writer = new PrintWriter(new FileOutputStream("./resources/Carter_19083522_COMP603_USERS.txt"));
        }

        catch(IOException e)
        {
            System.out.println("No USERS file found! creating one now!");
            try
            {
                this.writer = new PrintWriter(new FileOutputStream("./resources/Carter_19083522_COMP603_USERS.txt"));
                this.reader = new BufferedReader(new FileReader("./resources/Carter_19083522_COMP603_USERS.txt"));  
                this.read();     
            }
            catch(Exception i)
            {
            }
        }   
    }   

    //Reads the file and saves the values into arrays
 
    public void read()
    {
        try
        {
            String line = null;
            while((line = this.reader.readLine()) != null)
            {   
                String[] splitLine = line.split("\\s+");

                this.scores.add(Integer.parseInt(splitLine[1]));
                this.users.add(splitLine[0]);
            }

            for(String user : this.users)    
            {
                if(Millionaire.compareStrings(user, this.user))
                {
                    this.matchFound = true;    
                }
            }

            reader.close();
        }
        catch(Exception e)
        {
        }
    }

    //used for writing the current arrays into the values

    public void write()
    {
        try
        {
            if(!this.matchFound)
            {
                this.users.add(this.user);
                this.scores.add(score);
            }
            else
            {
                for(int count = 0; count < this.users.size(); count++)
                {
                    if(Millionaire.compareStrings(this.users.get(count), this.user))
                    {
                        if(this.scores.get(count) < this.score)
                        {
                            this.scores.set(count, score);
                        }    
                    }
                }
            }

            for(int counter = 0; counter < this.users.size(); counter++)
            {
                writer.println(this.users.get(counter) + " " + this.scores.get(counter));
            }

            writer.close();
        }

        catch(Exception ex)
        {
            
        }   
    }

    //To String
    @Override
    public String toString()
    {
        String output = "Scores:\n";

        for(int counter = 0; counter < this.users.size() ;counter++)
        {
            output += (counter + 1) + ": " + this.users.get(counter) + " " + this.scores.get(counter).toString() + "\n";
        }

        output += "\n-------";

        return output;   
    }
}
