/*
 * Liam Carter 19083522
 * COMP603
 * This works by printing the answer and the selected false answer
 */
package comp603_project2_19083522;

public class Lifeline5050 extends LifeLine{

    private String toString = "50/50";

    public Lifeline5050(GUI gui)
    {
        super(gui);  
    }

    @Override
    public void use(int num, QuestionClass q)
    {
        super.uses = super.uses - 1;
        
        String options = "";
        
        for(int counter = 0; counter < gui.mill.getCurrentQ().options.length; counter++)
        {
            options += gui.mill.getCurrentQ().options[counter] + "\n";
        }
        
        gui.label.setText("Question " + (gui.mill.QNum + 1) + ": "+ gui.mill.getCurrentQ() + "\n\n " + gui.mill.getCurrentQ().answer + "\n" + gui.mill.getCurrentQ().falseAnswer);
        
        gui.printLifeLines();
    } 

    @Override
    public String toString()
    {
        return this.toString;     
    }
}
