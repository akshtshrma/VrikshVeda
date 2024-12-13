// HomeFragment.kt or MainActivity.kt
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapters.LibraryAdapter
import com.example.vrikshveda.Library
import com.example.vrikshveda.R

class LibFragment : Fragment(R.layout.fragment_lib) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewArticles)

        val library = listOf(
            Library(R.drawable.lib_1, "Neem", "Azadirachta indica"),
            Library(R.drawable.lib_2, "Tulsi", "Ocimum sanctum"),
            Library(R.drawable.lib_3, "Aloe Vera", "Aloe barbadensis miller"),
            Library(R.drawable.lib_4, "Mango", "Mangifera indica"),
            Library(R.drawable.lib_1, "Neem", "Azadirachta indica"),
            Library(R.drawable.lib_2, "Tulsi", "Ocimum sanctum"),
            Library(R.drawable.lib_3, "Aloe Vera", "Aloe barbadensis miller"),
            Library(R.drawable.lib_4, "Mango", "Mangifera indica")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = LibraryAdapter(library)
    }
}
