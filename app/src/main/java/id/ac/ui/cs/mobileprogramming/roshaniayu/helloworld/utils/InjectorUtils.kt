package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.utils

import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.repository.DiaryRepository
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model.DummyDatabase
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.viewmodel.DiaryViewModelFactory

object InjectorUtils {
    fun provideDiaryViewModelFactory(): DiaryViewModelFactory {
        val diaryRepository = DiaryRepository.getInstance(
            DummyDatabase.getInstance().diaryDao)

        return DiaryViewModelFactory(
            diaryRepository
        )
    }
}