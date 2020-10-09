package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model.Diary
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.repository.DiaryRepository

class DiaryViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {
    fun getDiary() = diaryRepository.getDiary()

    fun addDiary(diary: Diary) = diaryRepository.addDiary(diary)
}