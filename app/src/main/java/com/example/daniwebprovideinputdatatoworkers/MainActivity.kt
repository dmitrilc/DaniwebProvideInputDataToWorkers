package com.example.daniwebprovideinputdatatoworkers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_startWorker)

        val workManager = WorkManager.getInstance(applicationContext)

        val data = Data.Builder()
            .putString(WORKER_INPUT_KEY_URL, "daniweb.com")
            .build()

        val downloadWorkRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setInputData(data)
            .build()

        val processDataWorkRequest = OneTimeWorkRequestBuilder<ProcessDataWorker>()
            .build()

        val postProcessDataWorker = OneTimeWorkRequestBuilder<PostProcessWorker>()
            .build()

        button.setOnClickListener {
            workManager
                .beginWith(downloadWorkRequest)
                .then(processDataWorkRequest)
                .then(postProcessDataWorker)
                .enqueue()
        }
    }
}