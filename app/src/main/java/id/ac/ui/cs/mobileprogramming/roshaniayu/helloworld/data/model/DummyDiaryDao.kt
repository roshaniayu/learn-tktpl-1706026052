package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model.Diary

class DummyDiaryDao {
    private val diaryList = mutableListOf<Diary>()
    private val diaryInputs = MutableLiveData<List<Diary>>()

    init {
        diaryInputs.value = diaryList
    }

    fun addDiary(diary: Diary) {
        diaryList.add(diary)
        diaryInputs.value = diaryList
    }

    fun getDiary() =  diaryInputs as LiveData<List<Diary>>
}