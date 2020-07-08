package com.jw.thread;

import net.openhft.affinity.Affinity;
import net.openhft.affinity.AffinityLock;
import net.openhft.affinity.AffinityStrategies;
import net.openhft.affinity.AffinityThreadFactory;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.BitSet;

/**
 * @Description: test T1
 * @Package: com.jw.thread
 * @ClassName: T1
 * @Author: james.guo
 * @Date: 2019/7/2 23:37
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T1 {

	public static void main(String[] args) {
		AffinityThreadFactory atf = new AffinityThreadFactory("atf", AffinityStrategies.DIFFERENT_CORE);

		// acquire a lock for a CPU
		try (AffinityLock al = AffinityLock.acquireLock()) {
			// dto some work while locked to a CPU.
			System.out.println("XXX");
		}

		// Acquiring a CORE lock for a thread
		// You can reserve a whole core. If you have hyper-threading enabled,
		// this will use one CPU and leave it’s twin sun.plugin2.gluegen.runtime.CPU unused.
		try (AffinityLock al = AffinityLock.acquireCore()) {
			// dto some work while locked to a CPU.
			System.out.println("######");
		}

		try (final AffinityLock al = AffinityLock.acquireLock()) {
			System.out.println("Main locked");
			Thread t = new Thread(() -> {
				// In this example, the library will prefer a free CPU on the same Socket as
				// the first thread, otherwise it will pick any free CPU.
				try (AffinityLock al2 = al.acquireLock(AffinityStrategies.SAME_SOCKET, AffinityStrategies.ANY)) {
					System.out.println("Thread-0 locked");
				}
			});
			t.start();
		}

		int threadId = Affinity.getThreadId();
		int cpu = Affinity.getCpu();

		System.out.println(String.format("threadId:%d, cpu:%d.", threadId, cpu));

		BitSet affinity = Affinity.getAffinity();
		affinity.set(0);

		// where n is the cpu you want to run the thread on.
		Affinity.setAffinity(1);

		System.out.println(ToStringBuilder.reflectionToString(affinity));

	}

}
