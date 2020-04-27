package com.example.zvent
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.zvent.databinding.FragmentResultsBinding

/* BASADO EN EL CODELAB DE ANDROID TRIVIA */
class resultsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        /*Especificacion de que fragment inflar*/
        val binding: FragmentResultsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_results, container, false)
        /*Referencia de que pasa cuando apachan el boton de restart*/
        binding.restart.setOnClickListener { view: View ->
            view.findNavController().navigate(resultsFragmentDirections.actionResultsFragmentToRegisterFragment())
        }
        /*Tomando la info utilizando safe args*/
        val args = resultsFragmentArgs.fromBundle(arguments!!)
        var contador: String = args.contador
        binding.registrados.text="Invitados: 10"
        binding.confirmados.text="Confirmados: ${args.yes}"
        /* Al presionar el boton se despliega el TOAST con los invitados*/
        binding.restart2.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        {
            Toast.makeText(context, contador, Toast.LENGTH_SHORT).show()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    /* Basado en el lab de Android Trivia esto se utiliza para el boton de share*/
    private fun getShareIntent() : Intent {
        val args = resultsFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, args.contador)
        return shareIntent
    }
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
    /*Levanta el menu de share que se encuentra aparte en la carpeta de menu*/
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.results_menu, menu)
    }

    /*Opcion de share*/
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}

private fun NavController.navigate(actionResultsFragmentToRegisterFragment: Any) {

}
