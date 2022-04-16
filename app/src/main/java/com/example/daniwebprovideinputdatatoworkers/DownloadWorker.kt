package com.example.daniwebprovideinputdatatoworkers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

const val WORKER_INPUT_KEY_URL = "0"

class DownloadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val url = inputData.getString(WORKER_INPUT_KEY_URL)

        return if (url != null){
            Result.success(inputData)
        } else {
            Result.failure()
        }
    }

}