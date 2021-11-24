package com.jw.james.thread.t1;

/**
 * guoyy com.jw.thread.t1
 *
 * @Description: ObservableThread
 * @Author: guoyiyong/james
 * @Date: 2019-08-11 11:25
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> taskLifecycle;
    private final Task<T> task;
    private Cycle cycle;

    public ObservableThread(TaskLifecycle<T> taskLifecycle, Task<T> task) {
        this.taskLifecycle = taskLifecycle;
        this.task = task;
    }

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    @Override
    public void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (taskLifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.taskLifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.taskLifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.taskLifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.taskLifecycle.onError(currentThread());
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

}
