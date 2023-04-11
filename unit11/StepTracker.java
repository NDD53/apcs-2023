package unit11;

// 2019 FRQ #2
// https://apcentral.collegeboard.org/media/pdf/ap19-frq-computer-science-a.pdf#page=7
public class StepTracker {

    private int minStep;
    private int activeDays;
    private int totalSteps;
    private int days;

    public StepTracker(int a){
        minStep = a;
        activeDays = 0;
        totalSteps = 0;
        days = 0;
    }

    public void addDailySteps(int a){
        days++;
        totalSteps+=a;
        if(a>=minStep){
            activeDays++;
        }
    }

    public int activeDays(){
        return activeDays;
    }

    public double averageSteps(){
        if(days==0){
            return 0.0;
        }
        return (double) 1.0*totalSteps/days;
    }

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("bad panda");
    }

    public static void main(String[] args) {

         StepTracker tr = new StepTracker(10000);
         check(tr.activeDays() == 0);
         check(tr.averageSteps() == 0.0);
         tr.addDailySteps(9000);
         tr.addDailySteps(5000);
         check(tr.activeDays() == 0);
         check(tr.averageSteps() == 7000.0);
         tr.addDailySteps(13000);
         check(tr.activeDays() == 1);
         check(tr.averageSteps() == 9000.0);
         tr.addDailySteps(23000);
         tr.addDailySteps(1111);
         check(tr.activeDays() == 2);
         check(tr.averageSteps() == 10222.2);
         System.out.println("Happy Panda! \uD83D\uDC3C");
    }
}
