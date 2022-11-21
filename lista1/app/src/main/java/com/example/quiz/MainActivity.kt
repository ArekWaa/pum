package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

const val EXTRA_MESSAGE = "pl.edu.uwr.pum.explicitintentkotlin.MESSAGE"
const val EXTRA_QUESTION = "pl.edu.uwr.pum.explicitintentkotlin.QUESTION"

class MainActivity : AppCompatActivity() {
    companion object {
        var question_index = 0
        var user_points = 0
        var correct_answers_count = 0
        var cheat_count = 0
        var questions_list = mutableListOf<QuizQuestions>(
            QuizQuestions("Druga zasada dynamiki Newtona:\nJeśli w pewnym układzie odniesienia ciało pozostaje w spoczynku lub porusza się ruchem jednostajnym prostoliniowym, to na ciało to nie działają żadne siły lub wszystkie siły się równoważą.", false),
            QuizQuestions("Termodynamika – dział fizyki zajmujący się badaniem energetycznych efektów wszelkich przemian fizycznych i chemicznych, które wpływają na zmiany energii wewnętrznej analizowanych układów. Bada przede wszystkim przemiany cieplne, lecz także efekty energetyczne reakcji chemicznych, przemian z udziałem jonów, przemiany fazowe, a nawet przemiany jądrowe i energii elektrycznej.", true),
            QuizQuestions("Kwazary to najjaśniej świecące obiekty we wszechświecie", true),
            QuizQuestions("Rozpraszanie Rayleigha - zjawisko rozpraszania cząsteczek o rozmiarach mniejszych od długości fali rozpraszanego światła.", true),
            QuizQuestions("Czas zależy od prędkości poruszania się obiektu.", true),
            QuizQuestions("Prędkość dźwięku w powietrzu wynosi około 340km/s.", false),
            QuizQuestions("Tonik świeci w świetle ultrafioletowym na pomarańczowo.", false),
            QuizQuestions("Moc = Natężenie * Napięcie", true),
            QuizQuestions("Przyspieszenie ziemskie jest dokładnie takie same we Wrocławiu i Warszawie.", false),
            QuizQuestions("Temperatura Słońca to 10432K.", false),
        )
    }
    lateinit var question_text_view: TextView
    lateinit var true_button: Button
    lateinit var false_button: Button
    lateinit var cheat_button: Button
    lateinit var summary_text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question_text_view = findViewById(R.id.question)
        true_button = findViewById(R.id.true_button)
        false_button = findViewById(R.id.false_button)
        cheat_button = findViewById(R.id.oszukaj_button)
        summary_text_view = findViewById(R.id.summary)

        displayQuestion()

    }

    fun startSecondActivity(view: View) {
        if (!questions_list[question_index].has_been_cheated) {
            questions_list[question_index].has_been_cheated = true
            user_points -= 15
            cheat_count++
        }
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, questions_list[question_index].answer)
            putExtra(EXTRA_QUESTION, questions_list[question_index].qusetion)
        }
        startActivity(intent)
    }

    fun onTrueButtonClick(view: View) {
        checkAnswer(true)
    }

    fun onFalseButtonClick(view: View) {
        checkAnswer(false)
    }

    fun checkAnswer(answer: Boolean) {
        var question_answer = questions_list[question_index].answer
        if (question_answer == answer)  {
            user_points += 10
            correct_answers_count++
        }
        question_index++
        if (question_index >= questions_list.size) showSummary()
        else displayQuestion()
    }

    fun displayQuestion() {
        question_text_view.text = questions_list[question_index].qusetion
    }

    fun showSummary() {
        question_text_view.visibility = View.GONE
        true_button.visibility = View.GONE
        false_button.visibility = View.GONE
        cheat_button.visibility = View.GONE
        summary_text_view.visibility = View.VISIBLE
        var summary = "Punkty: " + user_points + "\nPoprawne odpowiedzi: " + correct_answers_count + "\nLiczba oszustw: " + cheat_count
        summary_text_view.text = summary
    }

}