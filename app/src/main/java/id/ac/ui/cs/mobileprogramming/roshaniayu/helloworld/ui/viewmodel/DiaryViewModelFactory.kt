package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.repository.DiaryRepository

class DiaryViewModelFactory(private val diaryRepository: DiaryRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiaryViewModel(
            diaryRepository
        ) as T
    }
}