package pro.schmid.bug124412362

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round

class SpanningLinearLayoutManager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0, val maxItems: Int = 1) : LinearLayoutManager(context, attrs, defStyleAttr, defStyleRes) {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(c: Context?, attrs: AttributeSet?): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(c, attrs))
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return spanLayoutSize(super.generateLayoutParams(lp))
    }

    override fun canScrollHorizontally() = false

    override fun canScrollVertically() = false

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
        when (orientation) {
            HORIZONTAL -> layoutParams.width = round(horizontalSpace / maxItems.toDouble()).toInt()
            VERTICAL -> layoutParams.height = round(verticalSpace / maxItems.toDouble()).toInt()
        }
        return layoutParams
    }

    private val horizontalSpace
        get() = width - paddingStart - paddingEnd

    private val verticalSpace
        get() = height - paddingTop - paddingBottom
}
