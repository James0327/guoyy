package com.jw.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * guoyy com.jw.thread
 *
 * @Description: com.jw.thread.JLock
 * @Author: guoyiyong/james
 * @Date: 2019-12-08 23:59
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public final class JLock {

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }
    }

    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }
}
