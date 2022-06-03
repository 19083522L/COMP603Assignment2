
package comp603_project2_19083522;

import java.sql.*;

//Liam Carter 19083522 COMP603
//This class manages everything to do with the database such as reading and writing

public class DataBase {

    private String table;

    public Connection conn;

    public DataBase() throws ClassNotFoundException 
    {
        this.table = "CREATE  TABLE QUESTIONS  (QNUM  INT,   QUESTION   VARCHAR(300),   ANSWER1   VARCHAR(20), ANSWER2   VARCHAR(20), ANSWER3   VARCHAR(20), ANSWER4   VARCHAR(20), CANSWER VARCHAR(20))"; 

        this.getConnection();
        this.createTable();
    }

    public ResultSet queryDB(String sql) //Takes in a statment and queries the database, made this so wouldn't need to repeat code
    {
        Statement smt;
        ResultSet rs = null;

        try{
            smt = this.conn.createStatement();
            rs = smt.executeQuery(sql); 
        } 
        catch(SQLException e)
        {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return rs;
    }

    public void createTable() //The table uses many prepared statements to make construction faster 
    {
        try
        {
            Statement statement = this.conn.createStatement();
            
            if(this.tableExists("QUESTIONS"))//This is just here to prevent any with creating a table
            {
                statement.executeUpdate("DROP TABLE QUESTIONS");
            }

            if(!this.tableExists("QUESTIONS"))
            {
                Statement stm = this.conn.createStatement();
                
                stm.executeUpdate(this.table);
                PreparedStatement prep = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep.setInt(1, 0); //ID
                prep.setString(2, "What is the capital city of Germany? "); //Question
                prep.setString(3, "Berlin"); //Answer 1
                prep.setString(4, "Hamburg"); //Answer 2
                prep.setString(5, "Munich"); //Answer 3
                prep.setString(6, "Dresden"); //Answer 4
                prep.setString(7, "Berlin"); //Correct Answer
                prep.execute();

                PreparedStatement prep2 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep2.setInt(1, 1); //ID
                prep2.setString(2, "What is the pH level of pure water? "); //Question
                prep2.setString(3, "7"); //Answer 1
                prep2.setString(4, "4"); //Answer 2
                prep2.setString(5, "8"); //Answer 3
                prep2.setString(6, "6"); //Answer 4
                prep2.setString(7, "7"); //Correct Answer
                prep2.execute();

                PreparedStatement prep3 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep3.setInt(1, 2); //ID
                prep3.setString(2, "What is the largest planet in our solar system? "); //Question
                prep3.setString(3, "Jupiter"); //Answer 1
                prep3.setString(4, "Saturn"); //Answer 2
                prep3.setString(5, "Neptune"); //Answer 3
                prep3.setString(6, "Uranus"); //Answer 4
                prep3.setString(7, "Jupiter"); //Correct Answer
                prep3.execute();

                PreparedStatement prep4 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep4.setInt(1, 3); //ID
                prep4.setString(2, "What is the colour of emerald? "); //Question
                prep4.setString(3, "Green"); //Answer 1
                prep4.setString(4, "Blue"); //Answer 2
                prep4.setString(5, "Yellow"); //Answer 3
                prep4.setString(6, "Red"); //Answer 4
                prep4.setString(7, "Green"); //Correct Answer
                prep4.execute();

                PreparedStatement prep5 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep5.setInt(1, 4); //ID
                prep5.setString(2, "How many legs does a lobster have? "); //Question
                prep5.setString(3, "10"); //Answer 1
                prep5.setString(4, "8"); //Answer 2
                prep5.setString(5, "6"); //Answer 3
                prep5.setString(6, "12"); //Answer 4
                prep5.setString(7, "10"); //Correct Answer
                prep5.execute();

                PreparedStatement prep6 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep6.setInt(1, 5); //ID
                prep6.setString(2, "Which type of fish is Nemo? "); //Question
                prep6.setString(3, "Clownfish"); //Answer 1
                prep6.setString(4, "Goldfish"); //Answer 2
                prep6.setString(5, "Guppy"); //Answer 3
                prep6.setString(6, "Oscar"); //Answer 4
                prep6.setString(7, "Clownfish"); //Correct Answer
                prep6.execute();

                PreparedStatement prep7 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep7.setInt(1, 6); //ID
                prep7.setString(2, "Which is the worlds largest ocean? "); //Question
                prep7.setString(3, "Pacific ocean"); //Answer 1
                prep7.setString(4, "Atlantic ocean"); //Answer 2
                prep7.setString(5, "Indian ocean"); //Answer 3
                prep7.setString(6, "Southern ocean"); //Answer 4
                prep7.setString(7, "Pacific ocean"); //Correct Answer
                prep7.execute();

                PreparedStatement prep8 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep8.setInt(1, 7); //ID
                prep8.setString(2, "How many rings are in the Olympics logo? "); //Question
                prep8.setString(3, "5"); //Answer 1
                prep8.setString(4, "4"); //Answer 2
                prep8.setString(5, "6"); //Answer 3
                prep8.setString(6, "3"); //Answer 4
                prep8.setString(7, "5"); //Correct Answer
                prep8.execute();

                PreparedStatement prep9 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep9.setInt(1, 8); //ID
                prep9.setString(2, "What is the longest river int the world? "); //Question
                prep9.setString(3, "The Nile"); //Answer 1
                prep9.setString(4, "The Amazon River"); //Answer 2
                prep9.setString(5, "The Yangtze River"); //Answer 3
                prep9.setString(6, "The Yellow River"); //Answer 4
                prep9.setString(7, "The Nile"); //Correct Answer
                prep9.execute();

                PreparedStatement prep10 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep10.setInt(1, 9); //ID
                prep10.setString(2, "What colour are a zebra's strips when they are born? "); //Question
                prep10.setString(3, "Brown"); //Answer 1
                prep10.setString(4, "White"); //Answer 2
                prep10.setString(5, "Black"); //Answer 3
                prep10.setString(6, "Grey"); //Answer 4
                prep10.setString(7, "Brown"); //Correct Answer
                prep10.execute();

                PreparedStatement prep11 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep11.setInt(1, 10); //ID
                prep11.setString(2, "How many edges does a cube have? "); //Question
                prep11.setString(3, "12"); //Answer 1
                prep11.setString(4, "8"); //Answer 2
                prep11.setString(5, "14"); //Answer 3
                prep11.setString(6, "6"); //Answer 4
                prep11.setString(7, "12"); //Correct Answer
                prep11.execute();

                PreparedStatement prep12 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep12.setInt(1, 11); //ID
                prep12.setString(2, "What species of bird has the largest population? "); //Question
                prep12.setString(3, "Chicken"); //Answer 1
                prep12.setString(4, "Duck"); //Answer 2
                prep12.setString(5, "Pigeon"); //Answer 3
                prep12.setString(6, "Dove"); //Answer 4
                prep12.setString(7, "Chicken"); //Correct Answer
                prep12.execute();

                PreparedStatement prep13 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep13.setInt(1, 12); //ID
                prep13.setString(2, "What is the largest animal in the world? "); //Question
                prep13.setString(3, "Blue Whale"); //Answer 1
                prep13.setString(4, "Sperm Whale"); //Answer 2
                prep13.setString(5, "Humpback Whale"); //Answer 3
                prep13.setString(6, "Gray Whale"); //Answer 4
                prep13.setString(7, "Blue Whale"); //Correct Answer
                prep13.execute();

                PreparedStatement prep14 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep14.setInt(1, 13); //ID
                prep14.setString(2, "How many kilometres are between the Earth and the Moon? "); //Question
                prep14.setString(3, "384400"); //Answer 1
                prep14.setString(4, "284400"); //Answer 2
                prep14.setString(5, "484400"); //Answer 3
                prep14.setString(6, "184400"); //Answer 4
                prep14.setString(7, "384400"); //Correct Answer
                prep14.execute();

                PreparedStatement prep15 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep15.setInt(1, 14); //ID
                prep15.setString(2, "How many bones in a human body? "); //Question
                prep15.setString(3, "206"); //Answer 1
                prep15.setString(4, "192"); //Answer 2
                prep15.setString(5, "210"); //Answer 3
                prep15.setString(6, "223"); //Answer 4
                prep15.setString(7, "206"); //Correct Answer
                prep15.execute();

                PreparedStatement prep16 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep16.setInt(1, 15); //ID
                prep16.setString(2, "How many bits are in a byte? "); //Question
                prep16.setString(3, "8"); //Answer 1
                prep16.setString(4, "4"); //Answer 2
                prep16.setString(5, "6"); //Answer 3
                prep16.setString(6, "16"); //Answer 4
                prep16.setString(7, "8"); //Correct Answer
                prep16.execute();

                PreparedStatement prep17 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep17.setInt(1, 16); //ID
                prep17.setString(2, "Which country has the most sheep per capita? "); //Question
                prep17.setString(3, "Falkland Islands"); //Answer 1
                prep17.setString(4, "New Zealand"); //Answer 2
                prep17.setString(5, "Whales"); //Answer 3
                prep17.setString(6, "Australia"); //Answer 4
                prep17.setString(7, "Falkland Islands"); //Correct Answer
                prep17.execute();       

                PreparedStatement prep18 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep18.setInt(1, 17); //ID
                prep18.setString(2, "What was the first AI programming language called? "); //Question
                prep18.setString(3, "Lisp"); //Answer 1
                prep18.setString(4, "Java"); //Answer 2
                prep18.setString(5, "Julia"); //Answer 3
                prep18.setString(6, "R"); //Answer 4
                prep18.setString(7, "Lisp"); //Correct Answer
                prep18.execute();  

                PreparedStatement prep19 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep19.setInt(1, 18); //ID
                prep19.setString(2, "When was the Moon landing? "); //Question
                prep19.setString(3, "July 16 1969"); //Answer 1
                prep19.setString(4, "June 16 1969"); //Answer 2
                prep19.setString(5, "July 16 1970"); //Answer 3
                prep19.setString(6, "June 16 1970"); //Answer 4
                prep19.setString(7, "July 16 1969"); //Correct Answer
                prep19.execute();

                PreparedStatement prep20 = this.conn.prepareStatement("INSERT INTO QUESTIONS values(?, ?, ?, ?, ?, ?, ?)");
                prep20.setInt(1, 19); //ID
                prep20.setString(2, "What metal has the lowest melting point? "); //Question
                prep20.setString(3, "Mercury"); //Answer 1
                prep20.setString(4, "Francium"); //Answer 2
                prep20.setString(5, "Cesium"); //Answer 3
                prep20.setString(6, "Gallium"); //Answer 4
                prep20.setString(7, "Mercury"); //Correct Answer
                prep20.execute();    
                
                this.queryDB("SELECT * FROM QUESTIONS");
                ResultSet rs = stm.executeQuery("SELECT * FROM QUESTIONS");

                while(rs.next())//This was made so I could monitor the question creation process     
                {
                    int num = rs.getInt("QNUM");
                    String question = rs.getString("QUESTION");
                    String a1 = rs.getString("ANSWER1");
                    String a2 = rs.getString("ANSWER2");
                    String a3 = rs.getString("ANSWER3");
                    String a4 = rs.getString("ANSWER4");
                    String ca = rs.getString("CANSWER");

                    System.out.println("num: " + num + " question: " + question + " a1: " + a1 + " a2: " + a2 + " a3: " + a3 + " a4: " + a4 + " ca: " + ca);
                }
            }
            else
            {
                Statement stm = this.conn.createStatement();

                ResultSet rs = stm.executeQuery("SELECT * FROM QUESTIONS"); 

                while(rs.next())//This was made so I could monitor the question creation process      
                {
                    int num = rs.getInt("QNUM");
                    String question = rs.getString("QUESTION");
                    String a1 = rs.getString("ANSWER1");
                    String a2 = rs.getString("ANSWER2");
                    String a3 = rs.getString("ANSWER3");
                    String a4 = rs.getString("ANSWER4");
                    String ca = rs.getString("CANSWER");

                    System.out.println("num: " + num + " question: " + question + " a1: " + a1 + " a2: " + a2 + " a3: " + a3 + " a4: " + a4 + " ca: " + ca);
                }
            }
        }
        catch(SQLException e)
        {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean tableExists(String tableName)//Used for testing the table creation
    {
        try
        {
            DatabaseMetaData meta = this.conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, tableName, new String[] {"TABLE"});

            return rs.next();
        }
        catch(SQLException e)
        {
            System.err.println("Exception: " + e.getMessage());
        }
        
        return false;   
    }   

    private void getConnection()//Most important piece of this class, used establishing a connection between the embedded database or creating a new one
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.conn = DriverManager.getConnection("jdbc:derby:Assignment2DB_Ebd;create=true");
        }
        catch(SQLException e)
        {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }   
    }
}
