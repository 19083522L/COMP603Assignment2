/*
 * Liam Carter 19083522
 * COMP603
 * This works by printing the answer and the selected false answer
 */
package comp603_project2_19083522;

public class Lifeline5050 extends LifeLine{

    private String toString = "50/50";

    public Lifeline5050()
    {
        super();  
    }

    @Override
    public void use(int num, QuestionClass q)
    {
        super.setUses(super.getUses() - 1);
        
        System.out.println("Question " + (num + 1) + ": ");
        System.out.println("");
        System.out.println(q.getQuestion());
                
        System.out.println(q.getAnswer());
        System.out.println(q.getFalseOption());

        System.out.println("");
    } 

    @Override
    public String toString()
    {
        return this.toString;     
    }
}
