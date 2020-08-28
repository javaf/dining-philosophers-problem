import java.util.concurrent.*;

// There are 5 philosophers sitting around a
// table. Each philosopher thinks, or when hungry
// eats. Each philosopher has a bowl of noodles,
// but there are only 5 forks between each
// philosopher. However, in order to eat, a
// philosopher needs both forks. A deadlock occurs
// when all philosophers pick up one fork, but are
// waiting for the other fork to be put down.

// One idea to solve the dependency cycle is to
// alter the order in which the last philosopher
// picks up forks.

class Philosopher extends Thread {
  int i;
  static int N = 5;
  static Semaphore[] fork;
  static Philosopher[] philosopher;
  // i: each philosopher's id
  // N: number of philosophers
  // fork: N forks placed between them
  // philosopher: all N philosophers

  Philosopher(int id) {
    i = id;
  }

  // Each philosoper:
  // 1. thinks (sleep 100ms)
  // 2. picks left fork (i) (or waits)
  // 3. picks right fork (i+1) (or waits)
  // 4. eats (sleep 100ms)
  // 5. puts back right fork
  // 6. puts back left fork
  // EXCEPT the last philospher, who instead
  // picks up his right fork first; this is
  // what helps us to avoid deadlock.
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

  // 1. forks are placed
  // 2. philosophers are seated
  // 3. all philosophers now get to work
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
