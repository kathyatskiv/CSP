import java.util.Arrays;

public class Schedule {
    static int days;
    static int spots;
    public static Lesson[] lessons;
    public static Room[] rooms;
    public static Teacher[] teachers;
    public static Student[] students;
    public static Subject[] subjects;


    Schedule() {
        lessons = new Lesson[]{
                new Lesson(0,new int[]{0,1,2,3,4,5},0, 0,0),
                new Lesson(0,new int[]{0,1},0, 1,0),
                new Lesson(1,new int[]{3,4},1, 2, 0),
                new Lesson(3,new int[]{2,5},1, 3, 0),
                new Lesson(2,new int[]{0,1,2,3,4,5},3, 0, 1),
                new Lesson(2,new int[]{0,1,2,3,4,5},2,0, 2),
                new Lesson(3,new int[]{0,1,2},2,1, 1),
                new Lesson(2,new int[]{3,4,5},2,2, 1),
        };

        rooms = new Room[]{
                new Room(102,1,10),
                new Room(107,1,'a',3),
                new Room(108,1,3),
                new Room(217,3,10)
        };

        teachers = new Teacher[]{
                new Teacher("Ivan","Ivanenko","Ivanov"),
                new Teacher("Petro", "Petrenko", "Petrov"),
                new Teacher("Sydor", "Sydorenko", "Sydorov"),
                new Teacher("Vasyl", "Vasylenko", "Vasyliev")
        };

        students = new Student[]{
                new Student("Kate", "Yatskiv", "Bohdanivna"),
                new Student("Anna", "Kostyuk", "Andriivna"),
                new Student("Kyrylo", "Komonov", "Vitalyiovich"),
                new Student("Mark", "Godnyi", "Pavlovich"),
                new Student("Maryna", "Zhukovska", "Sergyivna"),
                new Student("Daria", "Harashchuk", "Sergyivna"),
        };

        subjects = new Subject[]{
                new Subject("Database"),
                new Subject("Basics of OOP"),
                new Subject("English")
        };
    }


    static class Lesson{
        public static int maxId = 0;
        public int id;

        int teacherId;
        int[] studentsIds;
        int roomId;
        int groupId;
        int subjectId;

        Lesson(int teacherId, int[] studentsIds, int roomId, int groupId, int subjectId){
            this.id = maxId++;
            this.teacherId = teacherId;

            this.studentsIds = new int[studentsIds.length];
            for(int i = 0; i < studentsIds.length; i++)
                this.studentsIds[i] = studentsIds[i];

            this.roomId = roomId;
            this.groupId = groupId;
            this.subjectId = subjectId;
        }

        @Override
        public String toString() {
            return subjects[subjectId] + " - " + teachers[teacherId];
        }
    }

    static class Teacher{
        public static int maxId = 0;
        public int id;
        private final String name;
        private final String surname;
        private final String middlename;

        public String getName(){return name;}
        public String getSurname(){return surname;}
        public String getMiddlename(){return middlename;}

        @Override
        public String toString() {
            return getName() + " " + getSurname() + " " + getMiddlename();
        }

        Teacher(String name, String surname, String middlename){
            this.id = maxId++;
            this.name = name;
            this.surname = surname;
            this.middlename = middlename;
        }
    }

    static class Student{
        public static int maxId = 0;
        public int id;
        private final String name;
        private final String surname;
        private final String middlename;

        public final String getName(){return name;}
        public final String getSurname(){return surname;}
        public final String getMiddlename(){return middlename;}

        @Override
        public String toString() {
            return getName() + " " + getSurname() + " " + getMiddlename();
        }

        Student(String name, String surname, String middlename){
            this.id = maxId++;
            this.name = name;
            this.surname = surname;
            this.middlename = middlename;
        }
    }

    static class Room{
        public static int maxId=0;
        public static int defaultAmount = 50;
        public int id;
        private final int num;
        private final Character prefix;
        private final int building;
        private final int amount;

        public int getNum(){return num;}
        public Character getPrefix(){return prefix;}
        public int getBuilding(){return building;}
        public int getAmount(){return amount;}

        public static void setDefaultAmount(int defaultAmount) {
            Room.defaultAmount = defaultAmount;
        }

        @Override
        public String toString() {
            return getBuilding()  + "-" + getNum() + getPrefix();
        }

        Room(int num, int building){
            this.id = maxId++;
            this.num = num;
            this.prefix = '\0';
            this.building = building;
            this.amount = defaultAmount;
        }

        Room(int num, int building, Character prefix){
            this.id = maxId++;
            this.num = num;
            this.prefix = prefix;
            this.building = building;
            this.amount = defaultAmount;
        }

        Room(int num, int building, int amount){
            this.id = maxId++;
            this.num = num;
            this.prefix = '\0';
            this.building = building;
            this.amount = amount;
        }

        Room(int num, int building, Character prefix, int amount){
            this.id = maxId++;
            this.num = num;
            this.prefix = prefix;
            this.building = building;
            this.amount = amount;
        }
    }

    static class Subject{
        public static int maxId = 0;
        public int id;
        private String subject;

        public String getSubject() {
            return subject;
        }

        Subject(String subject){
            this.id = maxId++;
            this.subject = subject;
        }

        @Override
        public String toString() {
            return this.subject;
        }
    }
}
