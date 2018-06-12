package com.example.sunny.gogleplay.manager;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* author sunny
* creat 2018/6/4 16:14
*/
public class ThreadPoolManager {

    private ThreadPoolProxy threadPoolProxy;
    private ThreadPoolProxy longThreadPool;

    public ThreadPoolManager(){}
    public static ThreadPoolManager mThreadPoolManager = new ThreadPoolManager();//恶汉式设计模式
    public static ThreadPoolManager getInstace(){
        return mThreadPoolManager;
    }

    /**
     * 1.读写文件（本身效率要高于请求服务器）
     * 2.请求服务器
     * 效率最高线程池
     * CPU *2+1
     * @return
     */
    public ThreadPoolProxy createThreadPoolProxy(){
        if(threadPoolProxy == null){
            threadPoolProxy = new ThreadPoolProxy(3,3,5000);
        }
        return threadPoolProxy;
    }

    /**
     * 读写文件线程池
     * @return
     */
    public ThreadPoolProxy createLongThreadPool(){
        if(longThreadPool == null){
            longThreadPool = new ThreadPoolProxy(5,5,5000);
        }
        return longThreadPool;
    }

    public class ThreadPoolProxy{
        ThreadPoolExecutor mThreadPoolExecutor;
        private int corePoolSize;//初始化线程数量
        private int maximumPoolSize;//额外最大在增加几个线程
        private long keepAliveTime;//线程空闲时保存时间

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public void execture(Runnable runnable){
            if(mThreadPoolExecutor == null){
//                参数：4,3参数单位（毫秒）5，等待队列
                mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(10));
            }
            mThreadPoolExecutor.execute(runnable);
        }

        public void cancel(Runnable runnable){
//            mThreadPoolExecutor不为空，不崩溃，不停止
            if(mThreadPoolExecutor != null&& !mThreadPoolExecutor.isShutdown()&& !mThreadPoolExecutor.isTerminated()){
                mThreadPoolExecutor.remove(runnable);
            }
        }
    }
}
