package pro.schmid.bug124412362

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.directory_page.*
import kotlin.math.ceil

class DirectoryPagesAdapter : RecyclerView.Adapter<DirectoryPagesAdapter.ViewHolder>() {

    private val itemsPerPage = 6

    var items: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.directory_page, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ceil(items.size.toDouble() / itemsPerPage).toInt()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val startIndex = position * itemsPerPage
        val endIndex = if (startIndex + itemsPerPage < items.size) startIndex + itemsPerPage else items.size

        val sublist = items.subList(startIndex, endIndex)

        holder.bindItem(sublist)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val entriesAdapter = DirectoryEntriesAdapter()

        init {
            entriesRecyclerView.setHasFixedSize(true)
            entriesRecyclerView.adapter = entriesAdapter
            entriesRecyclerView.layoutManager =
                SpanningLinearLayoutManager(containerView.context, maxItems = itemsPerPage)
        }

        fun bindItem(list: List<String>) {
            entriesAdapter.submitList(list)
        }
    }
}
