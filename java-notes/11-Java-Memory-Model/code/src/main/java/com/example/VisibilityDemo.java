package com.example;

public class VisibilityDemo {

    // Your Mission:
    // The 'stopped' variable is written to by the main thread,
    // but the change is not guaranteed to be seen by the worker thread.
    // This can cause the worker thread to loop forever.
    //
    // 1. Add the 'volatile' keyword to the 'stopped' field declaration.
    // 2. Run the program. It should now reliably stop after about 1 second.
    //
    // Note: Without 'volatile', this program *might* still terminate on some
    // systems due to luck, but 'volatile' provides the formal guarantee.
    static class Worker extends Thread {
        private boolean stopped = false;

        @Override
        public void run() {
            while (!stopped) {
                // This is a busy-wait loop. In a real application,
                // you'd do actual work here.
            }
            System.out.println("Worker thread has stopped.");
        }

        public void stopWorker() {
            System.out.println("Main thread is setting stopped = true");
            this.stopped = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();

        // Let the worker run for a second
        Thread.sleep(1000);

        // Tell the worker to stop
        worker.stopWorker();

        // Wait for the worker to finish
        worker.join();

        System.out.println("Main thread has finished.");
    }
}
