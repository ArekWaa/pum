package com.example.studentcrime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentA : Fragment() {
    var listaPrzewinien = mutableListOf<Przewinienie>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)
        listaPrzewinien.add(Przewinienie("Kradzież", "Kradzież drobnych z automatu.", 1100, false))
        listaPrzewinien.add(Przewinienie("Wulgaryzmy", "Brzydkie słowa.", 1101, false))
        listaPrzewinien.add(Przewinienie("Nieobecności", "Student nie uczestniczy w większości zajęć.", 1102, true))
        listaPrzewinien.add(Przewinienie("Kradzież", "Kradzież plecaka.", 1103, false))
        listaPrzewinien.add(Przewinienie("Przeszkadzanie", "Student rozmawia na wykładach.", 1104, true))
        listaPrzewinien.add(Przewinienie("Włamanie", "Włamanie w nocy do sali uniwersytetu.", 1105, true))
        listaPrzewinien.add(Przewinienie("Gwizdanie", "Studnent gwiżdże na zajęciach.", 1106, true))
        listaPrzewinien.add(Przewinienie("Plucie", "Plucie na korytarzu.", 1107, false))
        listaPrzewinien.add(Przewinienie("Śmiecenie", "Student zostawia śmieci na podłodze.", 1109, false))
        listaPrzewinien.add(Przewinienie("Włamanie do systemu", "Podmiana ocen w dzienniku elektronicznym.", 1110, true))
        listaPrzewinien.add(Przewinienie("Hakerstwo", "Zhakowanie strony uniwersytetu.", 1111, true))
        listaPrzewinien.add(Przewinienie("Obrażanie", "Wyzywanie prowadzącego.", 1112, false))
        listaPrzewinien.add(Przewinienie("Jedzenie podczas zajęć", "Student je obiad na zajęciach.", 1113, true))
        listaPrzewinien.add(Przewinienie("Spóźnianie", "Student spóźnia się na każde zajęcia 20 min.", 1114, false))
        listaPrzewinien.add(Przewinienie("Kradzież", "Kradzież jedzenia kolegi.", 1115, true))
        listaPrzewinien.add(Przewinienie("Bójka", "Zaatakowanie innego studenta.", 1116, true))
        listaPrzewinien.add(Przewinienie("kradzież", "Kradzież 100 zł kolegi.", 1117, false))
        listaPrzewinien.add(Przewinienie("Oczernianie", "Student pisze kłamsta na temat uniwersytetu na blogu.", 1118, false))
        listaPrzewinien.add(Przewinienie("Pożar", "Podpalenie śmietnika.", 1119, false))

        view.findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = RecycleViewAdapter(listaPrzewinien).apply {

            onItemClick = { przewinienie ->
                run {
                    Navigation.findNavController(view).navigate(FragmentADirections.toFragmentB(przewinienie))
                }
            }
        }

        return view
    }


}