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

class Main {
  static Thread[] philosopher;
  static Semaphore[] fork;
  static int N = 5;
  // philosopher: all N philosophers
  // fork: N forks placed between them
  // N: number of philosophers

  // 1. Think (sleep 100ms)
  // 2. Pick left fork (i) (or wait)
  // 3. Pick right fork (i+1) (or wait)
  // 4. Eat (sleep 100ms)
  // 5. Put back right fork
  // 6. Put back left fork
  // ...
  // EXCEPT the last philospher, who instead
  // picks up his right fork first; this is
  // what helps us to avoid deadlock.
  static Thread philosopher(int i) {
    return new Thread(() -> {
      try {
      while(true) {
        log(i+": thinking");
        Thread.sleep(100);
        int j = (i+1) % N;
        int p = Math.min(i, j);
        int q = Math.max(i, j);
        fork[p].acquire();
        fork[q].acquire();
        log(i+": eating");
        Thread.sleep(100);
        fork[q].release();
        fork[p].release();
      }
      }
      catch (InterruptedException e) {}
      log("ERROR: "+i+" stopped");
    });
  }

  // 1. Forks are placed
  // 2. Philosophers are seated
  // 3. All philosophers now get to work
  public static void main(String[] args) {
    log(N+" philosophers get to work ...");
    philosopher = new Thread[N];
    fork = new Semaphore[N];
    for(int i=0; i<N; i++) {
      philosopher[i] = philosopher(i);
      fork[i] = new Semaphore(1);
    }
    for(int i=0; i<N; i++)
      philosopher[i].start();
  }

  static void log(String x) {
    System.out.println(x);
  }
}
