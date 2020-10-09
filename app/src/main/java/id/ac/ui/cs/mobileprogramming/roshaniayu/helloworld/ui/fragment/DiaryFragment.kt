package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.R
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.ui.viewmodel.DiaryViewModel
import id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld.utils.InjectorUtils


class DiaryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diary, container, false)
        initializeUi(view)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, DiaryInputFragment() )
                fragmentTransaction?.commit()
            }
        })
    }

    private fun initializeUi(view: View) {
        val factory = InjectorUtils.provideDiaryViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(DiaryViewModel::class.java)
        val diaryTextView = view.findViewById<TextView>(R.id.textView_diary)

        viewModel.getDiary().observe(this, Observer { diaryInputs ->
            val stringBuilder = StringBuilder()
            if (diaryInputs.isNotEmpty()) {
                diaryInputs.forEach { diary ->
                    stringBuilder.append("$diary\n\n")
                }
            } else {
                stringBuilder.append("There's no input yet")
            }

            diaryTextView.text = stringBuilder.toString()
        })

        diaryTextView.movementMethod = ScrollingMovementMethod()
    }
}