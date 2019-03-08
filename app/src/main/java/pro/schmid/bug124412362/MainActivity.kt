package pro.schmid.bug124412362

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var pagesAdapter: DirectoryPagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagesAdapter = DirectoryPagesAdapter()
        viewPager.adapter = pagesAdapter

        val list = mutableListOf<String>()
        for (i in 1..20) { list.add("Hello ${Random.nextInt()}")}

        pagesAdapter.items = list
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

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
