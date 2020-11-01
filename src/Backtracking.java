import java.util.Arrays;

public class Backtracking {
    private int[] scheduleTime;
    private int[] scheduleRoom;
    private Boolean[] visited;
    private Boolean[] visitedRooms;

    private Schedule.Lesson[] lessons;
    private Schedule.Room[] rooms;

    public int[][] adjacencyMatrix;
    private  int[][] timeAdjencyMatrix;

    private int[][] schedules;

    Backtracking(Schedule.Lesson[] lessons, Schedule.Room[] rooms){
        this.lessons = new Schedule.Lesson[lessons.length];
        for(int i = 0; i < lessons.length; i++)
            this.lessons[i] = lessons[i];

        this.rooms = new Schedule.Room[rooms.length];
        for(int i = 0; i < rooms.length; i++)
            this.rooms[i] = rooms[i];

        setAdjacencyMatrix();

        visited = new Boolean[lessons.length];
        for(int i = 0; i < lessons.length; i++) visited[i] = false;

        visitedRooms = new Boolean[lessons.length];
        for(int i = 0; i < lessons.length; i++) visitedRooms[i] = false;

        scheduleTime = new int[lessons.length];
        scheduleRoom = new int[lessons.length];

    }

    private void setAdjacencyMatrix(){
        adjacencyMatrix = new int[lessons.length][lessons.length];
        for(int i = 0; i < lessons.length; i++){
            for(int j = 0; j < lessons.length; j++){
                if(lessons[i].teacherId == lessons[j].teacherId)
                    adjacencyMatrix[i][j] = 1;
                else if(!substruct(lessons[i].studentsIds,lessons[j].studentsIds))
                    adjacencyMatrix[i][j] = 1;
                else
                    adjacencyMatrix[i][j] = 0;
            }
        }
    }

    private Boolean substruct(int[] x, int[] y){
        for(int i = 0; i < x.length; i++)
            for(int j = 0; j < y.length; j++)
                if(x[i] == y[j]) return false;
        return true;
    }

    private void setTimeAdjencyMatrix(){
        timeAdjencyMatrix = new int[lessons.length][lessons.length];
        for(int i = 0; i < lessons.length; i++){
            for(int j = 0; j < lessons.length; j++){
                if(scheduleTime[i] == scheduleTime[j])
                    timeAdjencyMatrix[i][j] = 1;
            }
        }
    }

    private void backtrackRooms(int v){
        if(allVisitedRooms()){
            PrintSchedule ps = new PrintSchedule(scheduleTime, scheduleRoom);
            System.out.println(ps);
            return;
        }
        for(int i = 0; i < rooms.length; i++){
            if(lessons[v].studentsIds.length > rooms[i].getAmount()) continue;
            if(checkRoom(i,v)){
                scheduleRoom[v] = i;
                visitedRooms[v] = true;
                backtrackRooms(v+1);
            }
        }
    }

    boolean allVisitedRooms(){
        for(int i = 0; i < visitedRooms.length; i++)
            if(!visitedRooms[i]) return false;
        return true;
    }

    private Boolean checkRoom(int r,int v){
        for(int i = 0; i < visitedRooms.length; i++){
            if(!visitedRooms[i]) continue;
            if(timeAdjencyMatrix[i][v] == 1 && scheduleRoom[i] == r ) return false;
        }

        return true;
    }

    public void backtracking(int v, int h){
        if(allVisited()){
            setTimeAdjencyMatrix();

//            for(int i = 0; i < timeAdjencyMatrix.length; i++)
//            System.out.println(Arrays.toString(timeAdjencyMatrix[i]));

            backtrackRooms(0);
            return;
        }

        if(h == 3){
            int maxNeightborVal = 0;
            int val = 0;
            for(int i = 0; i < Schedule.days*Schedule.spots; i++)
                if(checkVisited(i,v)){
                    if(maxNeightborVal < lcv(i,v)){
                        maxNeightborVal = lcv(i,v);
                        val = i;
                    }
                }

            scheduleTime[v] = val;
            visited[v] = true;
            backtracking(v+1, h);

        }
        else
            for(int i = 0; i < Schedule.days*Schedule.spots; i++){
                if(checkVisited(i,v)){
                    scheduleTime[v] = i;
                    visited[v] = true;

                    switch (h){
                        case 0: backtracking(v+1, h);break;
                        case 1: backtracking(mrv(), h); break;
                        case 2: backtracking(degreeHeuristic(), h); break;
                    }

                }
            }

    }

    private Boolean checkVisited(int t,int v){
        for(int i = 0; i < visited.length; i++)
            if(visited[i] && adjacencyMatrix[i][v] == 1 && scheduleTime[i] == t) return false;
        return true;
    }

    private Boolean allVisited(){
        for(int i = 0; i < visited.length; i++)
            if(!visited[i]) return false;
        return true;
    }


    //DegreeHeuristic
    private int degreeHeuristic(){
        int maxVal = 0;
        int maxInd = 0;
        for(int i = 0; i < visited.length; i++)
            if(!visited[i] && countDegree(i) > maxVal){
                maxInd = i;
                maxVal = countDegree(i);
            }
        return maxInd;
    }

    private int countDegree(int v){
        int counter = 0;
        for(int i = 0; i < adjacencyMatrix[v].length; i++){
            if(adjacencyMatrix[v][i] == 1) counter++;
        }
        return counter;
    }

    //MRV
    private int mrv(){
        int minVal = Schedule.days*Schedule.spots + 1;
        int minInd = 0;

        for(int i = 0; i < visited.length; i++){
            if(visited[i]) continue;
            int counter = 0;
            for(int j = 0; j < Schedule.spots*Schedule.days;j++)
                if(checkVisited(j,i)) counter++;

            if(minVal > counter){
                minInd = i;
                minVal = counter;
            }
        }

        return minInd;
    }

    //LCV
    private int lcv(int t, int v){
        int temp = scheduleTime[v];

        int counter = 0;
        scheduleTime[v] = t;
        if(v == visited.length-1) return 0;
        else{
            for(int i = 0; i < Schedule.days*Schedule.spots; i++)
                if(checkVisited(i,v+1)) counter++;
        }

        scheduleTime[v] = temp;
        return counter;
    }

    //forward checking

}
