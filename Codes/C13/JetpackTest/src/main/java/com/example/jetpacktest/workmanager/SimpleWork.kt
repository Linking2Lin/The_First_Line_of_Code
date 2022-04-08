package com.example.jetpacktest.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class SimpleWork(context: Context,params:WorkerParameters):Worker(context,params) {

    /**
     * 该方法内编写后台任务逻辑
     */
    override fun doWork(): Result {
        Log.d("SimpleWorker", "doWork: do do do ")
        return Result.success()//必须返回任务执行结果
    }

    //构建单次运行的后台任务请求
    val request1 = OneTimeWorkRequest.Builder(SimpleWork::class.java).build()
    //构建周期性运行的后台任务请求，但运行间隔不得少于15分钟
    val request2 = PeriodicWorkRequest.Builder(SimpleWork::class.java,15,TimeUnit.MINUTES).build()
    //延迟5分钟后执行
    val request3 = OneTimeWorkRequest.Builder(SimpleWork::class.java).setInitialDelay(5,TimeUnit.MINUTES).build()
    //给后台任务请求添加标签,这样可以通过标签来取消后台任务请求，当然没有标签的话也可以通过id来取消后台任务请求
    //id取消的话只能取消单个后台任务请求，标签的话可以将同一标签名的所有后台任务请求全部取消掉
    val request4 = OneTimeWorkRequest.Builder(SimpleWork::class.java).setInitialDelay(5,TimeUnit.MINUTES).addTag("simple").build()

    //如果后台任务的doWork返回了Result.retry，那么结合SetbackoffCriteria来重新执行任务，
    // 该方法接收三个参数，第二第三用来指定多久后重新执行任务，第一个用来指定如果再次执行失败，下次重试该以什么样的形式延迟，可选的参数有两个，一个是以线性延迟，一个是以指数延迟
    val request5 = OneTimeWorkRequest.Builder(SimpleWork::class.java).setInitialDelay(5,TimeUnit.MINUTES).addTag("simple")
        .setBackoffCriteria(BackoffPolicy.LINEAR,10,TimeUnit.MINUTES).build()


}