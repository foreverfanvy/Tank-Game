package Learning.Threads;

public class CPU {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cpunumber = runtime.availableProcessors();
        System.out.println("CPU number: " + cpunumber);
    }
}
