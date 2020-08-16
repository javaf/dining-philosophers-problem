import java.util.concurrent.*;

class Philosopher extends Thread {
  int i;
  static int N = 5;
  static Semaphore[] fork;
  static Philosopher[] philosopher;

  Philosopher(int id) {
    i = id;
  }

  public void run() {
    try {
      while(true) {
        System.out.println(i+": thinking");
        Thread.sleep(100);
        int j = (i+1) % N;
        int p = Math.min(i, j);
        int q = Math.max(i, j);
        fork[p].acquire();
        fork[q].acquire();
        System.out.println(i+": eating");
        Thread.sleep(100);
        fork[q].release();
        fork[p].release();
      }
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    fork = new Semaphore[N];
    philosopher = new Philosopher[N];
    for(int i=0; i<N; i++) {
      fork[i] = new Semaphore(1);
      philosopher[i] = new Philosopher(i);
    }
    for(int i=0; i<N; i++) {
      System.out.println(i+": started");
      philosopher[i].start();
    }
  }
}
