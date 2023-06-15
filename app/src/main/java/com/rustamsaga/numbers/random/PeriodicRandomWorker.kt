package com.rustamsaga.numbers.random

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rustamsaga.numbers.numbers.domain.RandomNumberRepository

class PeriodicRandomWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = try {
        val repository = (applicationContext as ProvidePeriodicRepository)
            .providePeriodicRepository()
        repository.randomNumberFact()
        Result.success()
    } catch (e: Exception) {
        Result.retry()
    }
}

interface ProvidePeriodicRepository {
    fun providePeriodicRepository(): RandomNumberRepository
}