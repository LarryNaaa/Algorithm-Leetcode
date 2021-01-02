

public class ticketsell implements Runnable{
    private static int tickets = 30;
    final Object lock =new Object();
    @Override
    public void run() {

        while(tickets>0){
            ticketsell.calculate();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void calculate(){
        if(tickets>0){
            System.out.println(Thread.currentThread().getName() + "finally sell\t" + tickets + "tickets");
            tickets--;
        }
    }


}
