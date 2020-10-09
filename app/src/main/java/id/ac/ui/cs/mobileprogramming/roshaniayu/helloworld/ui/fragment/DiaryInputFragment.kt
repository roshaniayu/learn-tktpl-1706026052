package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.MainActivity
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.R
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.data.model.Diary
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.viewmodel.DiaryViewModel
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.utils.InjectorUtils

class DiaryInputFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diary_input, container, false)
        initializeUi(view)

        return view
    }

    private fun initializeUi(view: View) {
        val factory = InjectorUtils.provideDiaryViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(DiaryViewModel::class.java)
        val diaryEditText = view.findViewById<EditText>(R.id.editText_diary)
        val nameEditText = view.findViewById<EditText>(R.id.editText_name)
        val addDiaryButton = view.findViewById<Button>(R.id.button_add_diary)
        val readDiaryButton = view.findViewById<Button>(R.id.button_read_diary)

        addDiaryButton.setOnClickListener {
            val diaryString = diaryEditText.text.toString()
            val nameString = nameEditText.text.toString()

            if (diaryString.trim().isEmpty() or nameString.trim().isEmpty()) {
                (activity as MainActivity).hideKeyboard()

                val warningToast = Toast.makeText(activity,"Text field can't be empty!",Toast.LENGTH_SHORT)
                warningToast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 200)
                warningToast.show()
            } else {
                val diary = Diary(diaryEditText.text.toString(), nameEditText.text.toString())
                viewModel.addDiary(diary)

                (activity as MainActivity).hideKeyboard()
            }

            diaryEditText.setText("")
            nameEditText.setText("")
        }

        readDiaryButton.setOnClickListener(View.OnClickListener {
            val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, DiaryFragment() )
            fragmentTransaction?.commit()
        })
    }
}