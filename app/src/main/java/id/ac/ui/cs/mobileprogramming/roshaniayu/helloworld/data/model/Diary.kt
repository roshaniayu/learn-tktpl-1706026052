package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model

data class Diary(val feeling: String, val name: String) {
    override fun toString(): String {
        return "\"$feeling\" -$name"
    }
}