
public class PrintSchedule {
    Schedule.Lesson[] lessons;
    int[] time;
    int[] rooms;

    PrintSchedule(int[] time, int[] rooms){
        this.lessons = Schedule.lessons;
        this.rooms = rooms;
        this.time = time;
    }

    private String getTime(int i){
        String[] times = {"8:30", "10:00", "11:40", "13:30", "15:00", "16:30", "18:00"};
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        return times[i%Schedule.spots] + " " + days[i/Schedule.spots];
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < lessons.length; i++){
            res += (Schedule.lessons[i].groupId == 0 ? "Lecture" : ("Group " + Schedule.lessons[i].groupId)) + " - " + Schedule.lessons[i].toString() + " - " + Schedule.rooms[rooms[i]] + " - " + getTime(time[i]);
            res +='\n';
        }
        return res + "**********************";
    }
}
