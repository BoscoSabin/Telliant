package com.tellient.tellianttask.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.tellient.tellianttask.R
import kotlinx.android.synthetic.main.actionbar_layout.*
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tellient.tellianttask.adapter.CategoryAdapter
import com.tellient.tellianttask.adapter.ReportAdapter
import com.tellient.tellianttask.adapter.SliderAdapter
import com.tellient.tellianttask.util.ResourceLoader
import android.support.v4.content.ContextCompat
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation


class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupViewPager()
        setupCategoryRecycler()
        setupReportRecycler()
        initListener()
        setupNavigationBottom()
    }

    private fun initListener() {
        ivPrevious.setOnClickListener(this)
        ivNext.setOnClickListener(this)

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp)
    }

    private lateinit var mSliderAdapter: SliderAdapter

    fun setupViewPager() {
        mSliderAdapter = SliderAdapter(supportFragmentManager, ResourceLoader.initPatientData(applicationContext))
        viewpager.adapter = mSliderAdapter
        viewpager.addOnPageChangeListener(this)
        ivPrevious.alpha = 0.2f
    }

    fun setupCategoryRecycler() {

        val layoutManager = FlexboxLayoutManager(applicationContext)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.justifyContent = JustifyContent.CENTER
        rvCategory.setLayoutManager(layoutManager)

        val categoryAdapter = CategoryAdapter(ResourceLoader.getCategoryList(applicationContext), applicationContext)
        rvCategory.adapter = categoryAdapter
    }

    fun setupReportRecycler() {

        val layoutManager = LinearLayoutManager(applicationContext)
        rvResult.setLayoutManager(layoutManager)

        val categoryAdapter = ReportAdapter(ResourceLoader.getReportData(applicationContext), applicationContext)
        rvResult.adapter = categoryAdapter
    }

    override fun onClick(view: View?) {
        ivPrevious.alpha = 0.2f
        ivNext.alpha = 0.2f
        when (view?.id) {
            R.id.ivPrevious -> {
                viewpager.currentItem = --viewpager.currentItem
                ivNext.alpha = 1f
                if (viewpager.currentItem >= 1)
                    ivPrevious.alpha = 1f


            }
            R.id.ivNext -> {
                viewpager.currentItem = ++viewpager.currentItem
                ivPrevious.alpha = 1f
                if (viewpager.currentItem < mSliderAdapter.count - 1)
                    ivNext.alpha = 1f
            }
        }
    }

    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(pos: Int) {
        ivPrevious.alpha = 0.2f
        ivNext.alpha = 0.2f
        if (pos > 0) {
            ivPrevious.alpha = 1f
        }
        if (pos < mSliderAdapter.count - 1) {
            ivNext.alpha = 1f
        }
    }

    private fun setupNavigationBottom() {
        val item1 = AHBottomNavigationItem(R.string.title_home, R.drawable.ic_home_black_24dp, R.color.white)
        val item2 = AHBottomNavigationItem(R.string.title_consultations, R.drawable.ic_dashboard_black_24dp, R.color.white)
        val item3 = AHBottomNavigationItem(R.string.med_rec, R.drawable.ic_content_paste_black_24dp, R.color.white)
        val item4 = AHBottomNavigationItem(R.string.user, R.drawable.ic_person_black_24dp, R.color.white)
        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)
        bottomNavigation.addItem(item3)
        bottomNavigation.addItem(item4)
        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(applicationContext, R.color.colorAccent)
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW)
        bottomNavigation.setBehaviorTranslationEnabled(false)
        bottomNavigation.setAccentColor(ContextCompat.getColor(applicationContext, R.color.white))
        bottomNavigation.setInactiveColor(ContextCompat.getColor(applicationContext, R.color.inactiveColor))
        bottomNavigation.currentItem=1
    }
}
