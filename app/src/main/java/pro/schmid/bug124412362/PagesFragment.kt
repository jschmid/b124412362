package pro.schmid.bug124412362

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pages.*
import kotlin.random.Random

class PagesFragment : Fragment() {

    private lateinit var pagesAdapter: DirectoryPagesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        pagesAdapter = DirectoryPagesAdapter()
        viewPager.adapter = pagesAdapter

        val list = mutableListOf<String>()
        for (i in 1..20) {
            list.add("Hello ${Random.nextInt()}")
        }

        pagesAdapter.items = list
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // This is a hack while this bug is fixed: https://issuetracker.google.com/issues/124412362
        // We reset the ViewPager's orientation to the wanted one
        if (savedInstanceState != null) {
            viewPager.orientation = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> ViewPager2.ORIENTATION_HORIZONTAL
                Configuration.ORIENTATION_LANDSCAPE -> ViewPager2.ORIENTATION_VERTICAL
                else -> ViewPager2.ORIENTATION_HORIZONTAL
            }
        }
    }

}