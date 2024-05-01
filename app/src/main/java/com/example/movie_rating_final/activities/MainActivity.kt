package com.example.movie_rating_final.activities

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movie_rating_final.adapters.SliderAdapter
import com.example.movie_rating_final.Domain.SliderItems
import com.example.movie_rating_final.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var slideHandler: Handler
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var recyclerViewBestMovies: RecyclerView
    private lateinit var recyclerViewUpcoming: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setupSlider()
        setupRecyclerViews()
    }

    private fun setupSlider() {
        val sliderItems = getSliderItems()

        viewPager2 = findViewById(R.id.viewPagerSlider)
        sliderAdapter = SliderAdapter(sliderItems, viewPager2)
        viewPager2.adapter = sliderAdapter
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0)?.overScrollMode = View.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)

        slideHandler = Handler()
        slideHandler.postDelayed(sliderRunnable, 2000)
    }

    private fun setupRecyclerViews() {
        recyclerViewBestMovies = findViewById(R.id.view1)
        recyclerViewUpcoming = findViewById(R.id.view2)
        recyclerViewCategory = findViewById(R.id.view3)

        val layoutManagerBestMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerUpcoming = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerCategory = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerViewBestMovies.layoutManager = layoutManagerBestMovies
        recyclerViewUpcoming.layoutManager = layoutManagerUpcoming
        recyclerViewCategory.layoutManager = layoutManagerCategory

        // Create an instance of ImageItemDecoration with desired spacing for the right side
        val itemDecoration = ImageItemDecoration(resources.getDimensionPixelSize(R.dimen.item_spacing))

        // Add the item decoration to each RecyclerView
        recyclerViewBestMovies.addItemDecoration(itemDecoration)
        recyclerViewUpcoming.addItemDecoration(itemDecoration)
        recyclerViewCategory.addItemDecoration(itemDecoration)

        // Set adapters for each RecyclerView
        val adapterBestMovies = SliderAdapter(getSliderItems(), viewPager2)
        val adapterUpcoming = SliderAdapter(getSliderItems(), viewPager2)
        val adapterCategory = SliderAdapter(getSliderItems(), viewPager2)

        recyclerViewBestMovies.adapter = adapterBestMovies
        recyclerViewUpcoming.adapter = adapterUpcoming
        recyclerViewCategory.adapter = adapterCategory
    }

    private fun getSliderItems(): MutableList<SliderItems> {
        return mutableListOf(
            SliderItems(R.drawable.wide),
            SliderItems(R.drawable.wide1),
            SliderItems(R.drawable.wide3)
        )
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(sliderRunnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        slideHandler.removeCallbacks(sliderRunnable)
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun initView() {
        viewPager2 = findViewById(R.id.viewPagerSlider)
        recyclerViewBestMovies = findViewById(R.id.view1)
        recyclerViewUpcoming = findViewById(R.id.view2)
        recyclerViewCategory = findViewById(R.id.view3)
    }
}
