package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.repository

import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model.Diary
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model.DummyDiaryDao

class DiaryRepository private constructor(private val diaryDao: DummyDiaryDao){
    fun addDiary(diary: Diary) {
        diaryDao.addDiary(diary)
    }

    fun getDiary() = diaryDao.getDiary()

    companion object {
        @Volatile private var instance: DiaryRepository? = null

        fun getInstance(diaryDao: DummyDiaryDao) = instance
            ?: synchronized(this) {
            instance
                ?: DiaryRepository(
                    diaryDao
                )
                    .also { instance = it }
        }
    }
}