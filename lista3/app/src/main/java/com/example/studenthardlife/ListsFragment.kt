package com.example.studenthardlife

import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DataProvider.init(activity as Activity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.listsFragmentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        recyclerView.adapter = RecyclerViewAdapter(DataProvider.getAllItems()) {
            DataProvider.setSelectedItem(it)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_listsFragment_to_deatailsFragment)
        }

        val newTaskButton = view.findViewById<Button>(R.id.listFragmentButton)
        newTaskButton.setOnClickListener {
            val title = "Tytuł"
            val description = "Opis"
            val task = Task(title, description)
            DataProvider.addItem(task)
            DataProvider.setSelectedItem((recyclerView.adapter as RecyclerViewAdapter).itemCount)
            NavHostFragment.findNavController(this).navigate(R.id.action_listsFragment_to_deatailsFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}