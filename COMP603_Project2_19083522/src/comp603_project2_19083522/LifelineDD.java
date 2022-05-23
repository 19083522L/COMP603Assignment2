/*
 * Liam Carter 19083522
 * COMP603
 * This is the class for the Double dip lifeline, this works by increasing the questions number of attempts
 */
package comp603_project2_19083522;

public class LifelineDD extends LifeLine{

    private String toString = "Double Dip";
    
    public LifelineDD(GUI gui)
    {
        super(gui);
    }

    @Override
    public void use(int num, QuestionClass q)
    {
        super.uses = super.uses - 1;

        q.attempts = q.attempts + 1;
    }

    @Override
    public String toString()
    {
        return this.toString;
    }
}
