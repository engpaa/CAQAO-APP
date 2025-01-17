package com.example.caqao

import android.graphics.Color
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.caqao.models.CaqaoApiStatus
import com.example.caqao.network.CacaoDetection
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("caqaoApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: CaqaoApiStatus?) {
    when (status) {
        CaqaoApiStatus.LOADING -> {
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CaqaoApiStatus.ERROR -> {
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {}
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<CacaoDetection>?) {

    val adapter = recyclerView.adapter as CacaoGridAdapter
    adapter.submitList(data)

}

@BindingAdapter("cacaoColorBarChart")
fun bindCacaoColorBarchart(barChart: BarChart, cacaoDetection: CacaoDetection?) {
    var barList = ArrayList<BarEntry>()
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    barList.add(BarEntry(0f, cacaoDetection?.veryDarkBrown?.toFloat() ?: 0f))
    barList.add(BarEntry(1f, cacaoDetection?.brown?.toFloat() ?: 0f))
    barList.add(BarEntry(2f, cacaoDetection?.partlyPurple?.toFloat() ?: 0f))
    barList.add(BarEntry(3f, cacaoDetection?.totalPurple?.toFloat() ?: 0f))
    // sort the entries by decreasing value
    barList.sortByDescending { it.y }
    barDataSet = BarDataSet(barList, "Cacao Colors")
    barData = BarData(barDataSet)
    barDataSet.setColors(
        Color.rgb(56,28,0), // very dark brown
        Color.rgb(150,75,0), // brown
        Color.rgb(156,68,172), // partly purple
        Color.rgb(102,4,131), // total purple
    )

    barChart.data = barData
    barDataSet.valueTextColor= Color.BLACK
    barDataSet.valueTextSize = 15f
    barChart.animateXY(500,500, Easing.EaseInCubic)
    barChart.description.isEnabled = false
    barChart.setDrawGridBackground(false)
    val rightYAxis: YAxis = barChart.axisRight!!
    rightYAxis.isEnabled = false

    val xAxis = barChart.xAxis
    val categories = arrayOf("Very Dark Brown", "Brown", "Partly Purple", "Total Purple")
    xAxis.valueFormatter = IndexAxisValueFormatter(categories)
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setGranularity(1f);
    xAxis.setGranularityEnabled(true);
}

@BindingAdapter("cacaoDefectBarChart")
fun bindCacaoDefectBarchart(barChart: BarChart, cacaoDetection: CacaoDetection?) {
    var barList = ArrayList<BarEntry>()
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    barList.add(BarEntry(0f, cacaoDetection?.slaty?.toFloat() ?: 0f))
    barList.add(BarEntry(1f, cacaoDetection?.mouldy?.toFloat() ?: 0f))
    barList.add(BarEntry(2f, cacaoDetection?.insectInfested?.toFloat() ?: 0f))
    barList.add(BarEntry(3f, cacaoDetection?.germinated?.toFloat() ?: 0f))
    // sort the entries by decreasing value
    barList.sortByDescending { it.y }
    barDataSet = BarDataSet(barList, "Cacao Defects")
    barData = BarData(barDataSet)
    barDataSet.setColors(
        Color.rgb(118,134,146), // slaty
        Color.rgb(109,157,92),  // mouldy
        Color.rgb(255,255,255), // insect infested
        Color.rgb(173,141,111), // germinated
    )
    barChart.data = barData
    barDataSet.valueTextColor= Color.BLACK
    barDataSet.valueTextSize = 15f
    barChart.animateXY(500,500, Easing.EaseInCubic)
    barChart.description.isEnabled = false
    barChart.setDrawGridBackground(false)
    val rightYAxis: YAxis = barChart.axisRight!!
    rightYAxis.isEnabled = false

    val xAxis = barChart.xAxis
    val categories = arrayOf("Slaty", "Mouldy", "Insect Infested", "Germinated")
    xAxis.valueFormatter = IndexAxisValueFormatter(categories)
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setGranularity(1f);
    xAxis.setGranularityEnabled(true);
}

@BindingAdapter("fissuringGradeBarChart")
fun bindFissuringGradeBarChart(barChart: BarChart, cacaoDetection: CacaoDetection?) {

    var barList = ArrayList<BarEntry>()
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    barList.add(BarEntry(0f, cacaoDetection?.g1?.toFloat() ?: 0f))
    barList.add(BarEntry(1f, cacaoDetection?.g2?.toFloat() ?: 0f))
    barList.add(BarEntry(2f, cacaoDetection?.g3?.toFloat() ?: 0f))
    barList.add(BarEntry(3f, cacaoDetection?.g4?.toFloat() ?: 0f))
    // sort the entries by decreasing value
    barList.sortByDescending { it.y }
    barDataSet = BarDataSet(barList, "Fissuring Grades")
    barData = BarData(barDataSet)
    barChart.data = barData
    barDataSet.valueTextColor= Color.BLACK
    barDataSet.valueTextSize = 15f
    barChart.animateXY(500,500, Easing.EaseInCubic)
    barChart.description.isEnabled = false
    barChart.setDrawGridBackground(false)
    val rightYAxis: YAxis = barChart.axisRight!!
    rightYAxis.isEnabled = false

    val xAxis = barChart.xAxis
    val categories = arrayOf("G1", "G2", "G3", "G4")
    xAxis.valueFormatter = IndexAxisValueFormatter(categories)
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setGranularity(1f);
    xAxis.setGranularityEnabled(true);
}