public class Main {
    public static void main(String[] args) {
        new Schedule();
        Schedule.days = 3;
        Schedule.spots = 2;

        Backtracking bt = new Backtracking(Schedule.lessons, Schedule.rooms);
        //0 - general backtraking, 1 - mrv, 2 - degree, 3 - lsv
        //fc = true - forward checking is on
        int heuristic = 1;
        bt.backtracking(0, heuristic, true);

    }


}
