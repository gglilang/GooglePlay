package com.lang.googleplay.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 利用线程池管理线程
 * Created by Lang on 2015/7/25.
 */
public class ThreadManager {
    private ThreadPoolProxy longPool;
    private ThreadPoolProxy shortPool;

    public ThreadManager() {
    }

    private static ThreadManager instance = new ThreadManager();

    public static ThreadManager getInstance() {
        return instance;
    }

    // 联网比较耗时
    // 读取本地文件
    // cpu的核数*2+1
    public synchronized ThreadPoolProxy createLongPool() {
        if (longPool == null) {
            longPool = new ThreadPoolProxy(5, 5, 5000L);
        }
        return longPool;
    }

    public synchronized ThreadPoolProxy createShortPool() {
        if (longPool == null) {
            shortPool = new ThreadPoolProxy(5, 5, 5000L);
        }
        return shortPool;
    }

    public class ThreadPoolProxy {
        private ThreadPoolExecutor pool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long time) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.time = time;
        }

        /**
         * 执行任务
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (pool == null) {
                // 创建线程池
                /**
                 * 1.线程里面管理多少个线程
                 * 2.如果排队满了，额外开的线程数
                 * 3.如果线程池没有要执行的任务，存活多久
                 * 4.时间单位
                 * 5.如果线程池里管理的线程都已经用完了，剩下的任务临时存到LinkedBlockingQueue对象中排队
                 */
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, time, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10));
            }
            // 调用线程池，执行异步任务
            pool.execute(runnable);
        }

        /**
         * 取消任务
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (pool != null && !pool.isShutdown() && !pool.isTerminated()) {
                pool.remove(runnable);
            }
        }
    }
}
