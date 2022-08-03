package com.palesampe.booktracker

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mikhaellopez.circularprogressbar.CircularProgressBar


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var index = 0
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

        val bundle: Bundle? = getActivity()?.intent?.extras
        index = bundle?.getInt("UserIndex") as Int
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var progress = view.findViewById<CircularProgressBar>(R.id.progressID)


        var recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        val txtGoal = view.findViewById<TextView>(R.id.txtGoal)


        Toast.makeText(view.context, index.toString(), Toast.LENGTH_LONG).show()
        var max = 100f

        var dbBook = DatabaseHandler(requireContext())
        var dbUser = DatabaseHandler(requireContext())
        var bookData = dbBook.readBookData()
        var userData = dbUser.readUserData()

        val titles = mutableListOf<String>()
        val authors = mutableListOf<String>()
        val itemAdapter = recyclerAdapter(requireContext(), titles,authors)
        recycler.adapter = itemAdapter

        var checkedBooks = 0F
        updateProgress(progress,checkedBooks,max)

        for (i in 0..(bookData.size-1)){
            titles.add(bookData[i].title)
            authors.add(bookData[i].author)
        }

        txtGoal.text = checkedBooks.toInt().toString()+"/"+userData[index].yearGoal.toString()
        checkedBooks = 5f

        fab.setOnClickListener() {var selectedItem = itemAdapter.selected
            var intent = Intent(getActivity(), addBook::class.java)
            startActivity(intent)
        }
        progress.setOnClickListener() {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(view.context)
            builder.setTitle("NUMBER OF PAGES")

            val input = EditText(view.context)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            builder.setView(input)

            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                var goal = input.text.toString()
                val status = dbUser.updateUser(
                    index + 1, UserModel(
                        userData[index].name, userData[index].password,
                        userData[index].email, userData[index].phone,
                        goal.toInt(), userData[index].achieved
                    )
                )
                if (status > -1){
                    Toast.makeText(view.context, "GOAL UPDATED", Toast.LENGTH_LONG).show()
                    txtGoal.text = checkedBooks.toInt().toString()+"/"+goal
                    max = goal.toFloat()
                    updateProgress(progress,checkedBooks,max)
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        }
        return view
    }

    fun updateProgress(progress: CircularProgressBar,checkedBooks: Float, max: Float){
        progress.apply{
            var progress = checkedBooks/max
            setProgressWithAnimation ((100f*progress), 1000)
            progressBarWidth = 5f
            backgroundProgressBarWidth = 10f
            progressBarColor = Color.rgb(190,140,99)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}