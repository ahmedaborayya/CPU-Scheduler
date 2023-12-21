
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        Scanner pn = new Scanner(System.in);
        int AT, BT;
        String PN;
        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();
        
        
        CPUScheduler fcfs = new FirstComeFirstServe();
        CPUScheduler sjf = new ShortestJobFirst();
        CPUScheduler srt = new ShortestRemainingTime();
        CPUScheduler psn = new PriorityNonPreemptive();
        CPUScheduler psp = new PriorityPreemptive();
        CPUScheduler rr = new RoundRobin();
        
        for (int i = 0; i < n; i++) {
            System.out.print("proces Name: ");
            PN = pn.nextLine();
            System.out.print("Arrival Time For " + PN + " : ");
            AT = scanner.nextInt();
            System.out.print("Brust Time For " + PN + " : ");
            BT = scanner.nextInt();
            fcfs.add(new Row(PN, AT, BT));
            sjf.add(new Row(PN, AT, BT));
            srt.add(new Row(PN, AT, BT));
            psn.add(new Row(PN, AT, BT));
            psp.add(new Row(PN, AT, BT));
            rr.add(new Row(PN, AT, BT));
        }
        
        System.out.print("Enter Time Quantum: ");
        int TQ = scanner.nextInt();
        rr.setTimeQuantum(TQ);
        
        fcfs.process();
        sjf.process();
        srt.process();
        psn.process();
        psp.process();
        rr.process();
        
        System.out.println("-----------------FCFS----------------");
        display(fcfs);
        
        System.out.println("-----------------SJF-----------------");
        display(sjf);
        
        System.out.println("-----------------SRT-----------------");
        display(srt);
        
        System.out.println("-----------------PSN-----------------");
        display(psn);
        
        System.out.println("-----------------PSP-----------------");
        display(psp);
        
        System.out.println("-----------------RR------------------");
        display(rr);
        
        
    }
    
    public static void display(CPUScheduler object)
    {
        System.out.println("Process\tAT\tBT\tWT\tTAT");

        for (Row row : object.getRows())
        {
            System.out.println(row.getProcessName() + "\t" + row.getArrivalTime() + "\t" + row.getBurstTime() + "\t" + row.getWaitingTime() + "\t" + row.getTurnaroundTime());
        }
        
        System.out.println();
        
        for (int i = 0; i < object.getTimeline().size(); i++)
        {
            List<Event> timeline = object.getTimeline();
            System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");
            
            if (i == object.getTimeline().size() - 1)
            {
                System.out.print(timeline.get(i).getFinishTime());
            }
        }
        
        System.out.println("\n\nAverage WT: " + object.getAverageWaitingTime() + "\nAverage TAT: " + object.getAverageTurnAroundTime());
    }
}
