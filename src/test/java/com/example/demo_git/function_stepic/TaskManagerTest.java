package com.example.demo_git.function_stepic;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */

public class TaskManagerTest {

    @Test
    public void taskQueues() {
        List<Queue<Task>> managerTasks = new ArrayList<>();
        managerTasks.add(0, new ArrayDeque<>());
        managerTasks.add(1, new ArrayDeque<>());
        managerTasks.get(0).add(new Task(2000L, 0));
        managerTasks.get(0).add(new Task(1000L, 0));
        managerTasks.get(1).add(new Task(3000L, 1));
        managerTasks.get(1).add(new Task(4000L, 1));
        Task task = managerTasks.stream()
            .filter(o -> !o.isEmpty())
            .map(Queue::peek)
            .findFirst().orElse(null);

        assertThat(task.getNumber()).isEqualTo(2000L);
        assertThat(managerTasks.size()).isEqualTo(2);

    }

}
