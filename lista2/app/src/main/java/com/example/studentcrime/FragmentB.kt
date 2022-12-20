package com.example.studentcrime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        view.findViewById<Button>(R.id.przyciskPowrot).setOnClickListener {
            val action = FragmentBDirections.toFragmentA()
            Navigation.findNavController(view).navigate(action)
        }

        //var przewinienie = arguments?.getSerializable("DATA") as Przewinienie
        arguments?.let { a ->
            var przewinienie = FragmentBArgs.fromBundle(a).data
            view.findViewById<TextView>(R.id.przewinienieTytul).text = "Tytuł: " + przewinienie.tytul
            view.findViewById<TextView>(R.id.przewinienieTresc).text = "Treść: " + przewinienie.tresc
            view.findViewById<TextView>(R.id.nrIndeksu).text = "Indeks Studenta: " + przewinienie.nrStudenta.toString()
            var czyRozwiazany = ""
            if (przewinienie.czyJestRozwiazane) {
                czyRozwiazany = "tak"
            }
            else {
                czyRozwiazany = "nie"
            }
            view.findViewById<TextView>(R.id.przewinienieCzyRozwiazane).text = "Czy został rozwiązany: " + czyRozwiazany
        }

        return view
    }


}