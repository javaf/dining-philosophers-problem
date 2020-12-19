There are 5 philosophers sitting around a table. Each
philosopher thinks, or when hungry eats. Each
philosopher has a bowl of noodles, but there are only
5 forks between each philosopher. However, in order
to eat, a philosopher needs both forks. A deadlock
occurs when all philosophers pick up one fork, but
are waiting for the other fork to be put down. 

One idea to solve the dependency cycle is to alter the
order in which the last philosopher picks up forks. 

> **Course**: [Concurrent Data Structures], Monsoon 2020<br>
> **Taught by**: Prof. Govindarajulu Regeti
<br>

[Concurrent Data Structures]: https://github.com/iiithf/concurrent-data-structures

```java
philosoper(i):
1. Think (sleep 100ms)
2. Pick left fork (i) (or wait)
3. Pick right fork (i+1) (or wait)
4. Eat (sleep 100ms)
5. Put back right fork
6. Put back left fork
...
EXCEPT the last philospher, who instead
picks up his right fork first; this is
what helps us to avoid deadlock.
```

```bash
## OUTPUT
5 philosophers get to work ...
0: thinking
1: thinking
2: thinking
4: thinking
3: thinking
0: eating
2: eating
0: thinking
4: eating
2: thinking
1: eating
4: thinking
3: eating
0: eating
1: thinking
3: thinking
2: eating
0: thinking
4: eating
...
```

See [Main.java] for code, and [repl.it] for output.

[Main.java]: https://repl.it/@wolfram77/dining-philosophers-problem#Main.java
[repl.it]: https://dining-philosophers-problem.wolfram77.repl.run


### references

- [Deadlock 3: Dining Philosophers :: Jacob Schrum](https://www.youtube.com/watch?v=_ruovgwXyYs)
- [The dining philosophers problem Solution in java :: Hasitha Chandula](https://medium.com/@hasitha.chandula/the-dining-philosophers-problem-solution-in-java-223daf103c2)
