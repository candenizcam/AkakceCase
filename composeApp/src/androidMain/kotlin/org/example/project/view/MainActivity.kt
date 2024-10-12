package org.example.project.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import org.example.project.R
import org.example.project.viewmodel.KtorViewModel

/** Main class for the app,
 * Horizontal Scroller is implemented with ViewPager2
 * Vertical Scroller is implemented with RecyclerView
 * Detail page is implemented using a ViewFlipper between main and detail page
 */
class MainActivity : ComponentActivity() {
    private lateinit var ktorRequest: KtorViewModel
    private val horizontalScrollerLimit = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)
        // I locked the rotation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // starts ViewModel
        ktorRequest = KtorViewModel()



        // starts pages
        doDetailPage()
        doHorizontalScroll()
        doVerticalGrid()
    }


    private fun flipToDetails(index: Int){
        /** when a flip command is called (detail page is opened) data from id is called and page is altered
         */
        ktorRequest.oneData((index+1).toString()) { it2 ->
            findViewById<TextView>(R.id.product_detail_title).text = it2.title
            findViewById<TextView>(R.id.product_detail_price).text = "${"%.2f".format(it2.price)}$"
            findViewById<TextView>(R.id.product_detail_category).text = it2.category
            findViewById<TextView>(R.id.product_detail_detail).text = it2.description
            findViewById<TextView>(R.id.product_detail_rating).text = "Rated: ${it2.rating.rate}/5 by ${it2.rating.count} users."
            findViewById<ImageView>(R.id.product_detail_image).load(it2.image)



            val vf = findViewById<View>(R.id.viewFlipper) as ViewFlipper
            vf.showNext()
        }
    }

    private fun doHorizontalScroll(){
        /** Builds horizontal scroller
         *
         */
        val viewPager: ViewPager2 = findViewById(R.id.viewpager)

        // data for horizontal scroller is called, and applied to the declared function
        ktorRequest.manyData(horizontalScrollerLimit){ it->
            // adapter is generated with the relevant data
            val adapter = HorizontalScrollAdapter(
                it.toTypedArray()
            ) { a ->
                flipToDetails(a)
            }

            viewPager.adapter = adapter
            TabLayoutMediator(findViewById(R.id.into_tab_layout), findViewById(R.id.viewpager)) { tab, position -> }.attach() //The Magical Line

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                // This method is triggered when there is any scrolling activity for the current page
                // we may change this in the future
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                // triggered when you select a new page
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                // triggered when there is
                // scroll state will be changed
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
        }
    }


    private fun doVerticalGrid(){
        // two column grid, works similar to horizontal grid (it also uses adapter)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        // data is adjusted
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        ktorRequest.manyData { it->
            val customAdapter = VerticalScrollAdapter(it.toTypedArray()){ a ->
                flipToDetails(a)
            }
            recyclerView.adapter = customAdapter

        }

    }

    private fun doDetailPage(){
        // detail page is built (but it will be filled when flipped by pressing a particular item)
        val b = findViewById<Button>(R.id.return_button)
        b.setOnClickListener {
            val vf = findViewById<View>(R.id.viewFlipper) as ViewFlipper
            vf.showPrevious()
        }
    }


}
