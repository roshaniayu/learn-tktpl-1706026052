package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model

class DummyDatabase private constructor() {
    var diaryDao =
        DummyDiaryDao()
        private set

    companion object {
        @Volatile private var instance: DummyDatabase? = null

        fun getInstance() = instance
            ?: synchronized(this) {
            instance
                ?: DummyDatabase()
                    .also { instance = it }
        }
    }
}