/*
 * Liam Carter 19083522
 * COMP603
 * This is an abstract method for the lifelines to be built from
 */
package comp603_project2_19083522;

public abstract class LifeLine {
    
    private int uses = 1;

    public LifeLine()
    {
    }   

    //This is for activating the effect of the lifeline
    public abstract void use(int num, QuestionClass q);

    //This toString allows for printing each lifeline
    public abstract String toString();

    //setters
    public void setUses(int uses)
    {
        this.uses = uses;
    }

    //getters
    public int getUses()
    {
        return this.uses;
    }
}
