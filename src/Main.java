public class Main {
    public static void main(String[] args) {
        new Schedule();
        Schedule.days = 3;
        Schedule.spots = 2;

        Backtracking bt = new Backtracking(Schedule.lessons, Schedule.rooms);
        //0 - general backtraking, 1 - mrv, 2 - degree
        //fc = true - forward checking is on
        //lcv = true - lcv is on
        int heuristic = 0;

        long startTime = System.currentTimeMillis();
        bt.backtracking(0, heuristic, true, true);
        long time = System.currentTimeMillis() - startTime;

        System.out.println(time);

    }


}
