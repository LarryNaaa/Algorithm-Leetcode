

public class testticketsell {
    public static void main(String[] args) {
//        ticketsell t=new ticketsell();
//        Thread t1=new Thread(t,"t1");
//        Thread t2=new Thread(t,"t2");
//        Thread t3=new Thread(t,"t3");
//        t2.start();
//        t1.start();
//        t3.start();
        for(int i = 0; i < 3; i++){
            ticketsell t = new ticketsell();
            Thread t1=new Thread(t,"t"+i);
            t1.start();
        }
    }
}
