package com.example.zvent


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.zvent.databinding.FragmentRegisterBinding
import com.example.zvent.ui.Invitado
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private val invitados: MutableList<Invitado> = mutableListOf(
        Invitado("Carlos Perez","CarlitosP@gmail.com","33678310"),
        Invitado("Maria Rosales","RosalesMaria@gmail.com","45781123"),
        Invitado("Pepita Perez","Pepitap@gmail.com","35672312"),
        Invitado("Oscar Maldonado","Maldonado123@gmail.com","36785641"),
        Invitado("Laura Rodriguez","LauraEmilia@gmail.com","56478930"),
        Invitado("Maria Barrios","Mbarrios@gmail.com","27678910"),
        Invitado("Daniela Collia","ColliaDaniela@gmail.com","56783213"),
        Invitado("Daniel Lopez","LopraDaniel@gmail.com","23567832"),
        Invitado("Pedro Mejicano","Mex@gmail.com","56431188"),
        Invitado("Martin Zuluaga","ZulMartin@gmail.com","78093715")
    )

    lateinit var currentGuest: Invitado
    private var questionIndex = 0
    /*private val numQuestions = Math.min((invitados.size + 1) / 2,3)*/
    private var invitado = Invitado()
    private var contador: String=""
    var yes: Int=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*Implementacion binding*/
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater, R.layout.fragment_register, container, false)
        binding.guest = invitado

        binding.apply {
            binding.invalidateAll()
            questionText1.text = invitados[questionIndex].name
            questionText2.text = "Phone: " + invitados[questionIndex].number
            questionText3.text = "Email: " + invitados[questionIndex].email

        }

        setHasOptionsMenu(true)
        /*var args = arguments?.let { resultsFragmentArgs.fromBundle(it) }
        Toast.makeText(context,
            "contador: ${contador}, yes: ${yes}",
            Toast.LENGTH_LONG).show()*/
        return binding.root
    }

    /* Especificación de la barra de cheque o cruz*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.register_menu,menu)
    }
    /* Comienza la logica en ver que pasa cuando se apacha que boton */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, 10)
        questionText1.text = invitados[questionIndex].name
        questionText2.text = "Phone: " + invitados[questionIndex].number
        questionText3.text = "Email: " + invitados[questionIndex].email
        /*questionIndex += 1*/

        when(item.itemId) {
            R.id.b_si -> {
                yes++
                contador += (invitados[questionIndex].name + ": Confirmed") /*Llena el conteo de quienes si y quienes no*/
                if (questionIndex == 9) { /* Al llegar a 10 automaticamente tira a la siguiente actividad*/
                    view!!.findNavController().navigate(RegisterFragmentDirections
                        .actionRegisterFragmentToResultsFragment(contador,yes))
                    /*questionText1.text = invitados[questionIndex].name
                    questionText2.text = "Phone: " + invitados[questionIndex].number
                    questionText3.text = "Email: " + invitados[questionIndex].email*/
                }
            }
            R.id.b_no -> {
                contador += (invitados[questionIndex].name + ": Missing") /*Llena el conteo de quienes si y quienes no*/
                if (questionIndex == 9) { /* Al llegar a 10 automaticamente tira a la siguiente actividad*/
                    view!!.findNavController().navigate(RegisterFragmentDirections
                        .actionRegisterFragmentToResultsFragment(contador, yes)
                    )
                    /*questionText1.text = invitados[questionIndex].name
                    questionText2.text = "Phone: " + invitados[questionIndex].number
                    questionText3.text = "Email: " + invitados[questionIndex].email*/
                }

            }
        }
        questionIndex++
        return super.onOptionsItemSelected(item)
    }
}
