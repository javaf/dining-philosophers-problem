There are 5 philosophers sitting around a table. Each philosopher thinks, or when hungry eats. Each philosopher has a bowl of noodles, but there are only 5 forks between each philosopher. However, in order to eat, a philosopher needs both forks. A deadlock occurs when all philosophers pick up one fork, but are waiting for the other fork to be put down. 

One idea to solve the dependency cycle is to alter the order in which the last philosopher picks up forks. 

```java
Each philosoper:
1. Thinks (sleep 100ms)
2. Picks left fork (i) (or waits)
3. Picks right fork (i+1) (or waits)
4. Eats (sleep 100ms)
5. Puts back right fork
6. Puts back left fork
...
EXCEPT the last philospher, who instead
picks up his right fork first; this is
what helps us to avoid deadlock.
```

See [Philosopher.java] for code, and [repl.it] for output.

[Philosopher.java]: https://repl.it/@wolfram77/dining-philosophers-problem#Philosopher.java
[repl.it]: https://dining-philosophers-problem.wolfram77.repl.run


### references

- [Deadlock 3: Dining Philosophers :: Jacob Schrum](https://www.youtube.com/watch?v=_ruovgwXyYs)
- [The dining philosophers problem Solution in java :: Hasitha Chandula](https://medium.com/@hasitha.chandula/the-dining-philosophers-problem-solution-in-java-223daf103c2)
