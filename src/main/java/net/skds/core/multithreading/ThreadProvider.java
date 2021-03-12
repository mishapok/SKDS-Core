package net.skds.core.multithreading;

import java.util.function.Function;

import net.skds.core.api.multithreading.ITaskRunnable;

public class ThreadProvider {

	public static int PROCESSORS = Runtime.getRuntime().availableProcessors();
	public static UniversalWorkerThread[] THREADS = UniversalWorkerThread.create(PROCESSORS);

	
	public static void doSyncFork(Function<Integer, ITaskRunnable> sup) {

		for (UniversalWorkerThread t : THREADS) {
			t.forkSync(sup);
		}
	}

	public static void waitForStop() throws InterruptedException {
		for (UniversalWorkerThread t : THREADS) {
			t.waitForJoin();
		}
	}
}