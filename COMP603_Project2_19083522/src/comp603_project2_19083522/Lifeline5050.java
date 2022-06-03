/*
 * Liam Carter 19083522
 * COMP603
 * This class updates the text of the label with fewer options always including the correct answer
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

        gui.label.setText("<html>Question " + (gui.mill.QNum + 1) + ": "+ q.getQuestion() + "<br/><br/>" + q.answer + "<br?>" + q.falseAnswer + "</html>");
    } 

    @Override
    public String toString()
    {
        return this.toString;     
    }
}