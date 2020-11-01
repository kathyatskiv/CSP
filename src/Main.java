public class Main {
    public static void main(String[] args) {
        new Schedule();
        Schedule.days = 3;
        Schedule.spots = 2;

        Backtracking bt = new Backtracking(Schedule.lessons, Schedule.rooms);
        bt.backtracking(0);



    }


}
