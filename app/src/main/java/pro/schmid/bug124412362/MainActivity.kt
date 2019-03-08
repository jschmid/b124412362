package pro.schmid.bug124412362

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
}
