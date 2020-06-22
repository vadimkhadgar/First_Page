package com.mobile.instagramfirstpage.utils

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TrackingData {
    // Duration for which the view has been viewed.
    var viewDuration: Long = 0

    // ID for the view that was viewed (we'll use the position of the item here).
    var viewId: String? = null

    // Percentage of the height visible
    var percentageHeightVisible = 0.0

}

class ViewTracker(private val recyclerView: RecyclerView) {
    // Time from which a particular view has been started viewing.
    private var startTime: Long = 0

    // Time at which a particular view has been stopped viewing.
    private var endTime: Long = 0

    // Flag is required because 'addOnGlobalLayoutListener'
    // is called multiple times.
    // The flag limits the action inside 'onGlobalLayout' to only once.
    private var firstTrackFlag = false

    // ArrayList of view ids that are being considered for tracking.
    private val viewsViewed: ArrayList<Int> = ArrayList()

    // ArrayList of TrackingData class instances.
    private val trackingData: ArrayList<TrackingData> = ArrayList()

    // The minimum amount of area of the list item that should be on
    // the screen for the tracking to start.
    private val minimumVisibleHeightThreshold = 0.0

    // Start the tracking process.
    fun startTracking() {

        // Track the views when the data is loaded into
        // recycler view for the first time.
        recyclerView.viewTreeObserver
            .addOnGlobalLayoutListener(OnGlobalLayoutListener {
                if (!firstTrackFlag) {
                    startTime = System.currentTimeMillis()
                    val firstVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()
                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager)
                            .findLastVisibleItemPosition()
                    analyzeAndAddViewData(
                        firstVisibleItemPosition,
                        lastVisibleItemPosition
                    )
                    firstTrackFlag = true
                }
            })

        // Track the views when user scrolls through the recyclerview.
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)

                // User is scrolling, calculate and store the tracking
                // data of the views that were being viewed
                // before the scroll.
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    endTime = System.currentTimeMillis()
                    for (trackedViewsCount in 0 until viewsViewed.size) {
                        trackingData.add(
                            prepareTrackingData(
                                java.lang.String
                                    .valueOf(
                                        viewsViewed[trackedViewsCount]
                                    ),
                                (endTime - startTime) / 1000
                            )
                        )
                    }

                    // We clear the list of current item positions.
                    // If we don't do this, the items will be tracked
                    // every time the new items are added.
                    viewsViewed.clear()
                }

                // Scrolling has ended, start the tracking
                // process by assigning a start time
                // and maintaining a list of views being viewed.
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    startTime = System.currentTimeMillis()
                    val firstVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager?)
                            ?.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager?)
                            ?.findLastVisibleItemPosition()
                    analyzeAndAddViewData(
                        firstVisibleItemPosition as Int,
                        lastVisibleItemPosition as Int
                    )
                }
            }
        })
    }

    // Track the items currently visible and then stop the tracking process.
    fun stopTracking() {
        endTime = System.currentTimeMillis()
        val firstVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val lastVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        analyzeAndAddViewData(
            firstVisibleItemPosition,
            lastVisibleItemPosition
        )
        for (trackedViewsCount in 0 until viewsViewed.size) {
            trackingData.add(
                prepareTrackingData(
                    java.lang.String.valueOf(
                        viewsViewed[trackedViewsCount]
                    ),
                    (endTime - startTime) / 1000
                )
            )
            viewsViewed.clear()
        }
    }

    private fun analyzeAndAddViewData(
        firstVisibleItemPosition: Int,
        lastVisibleItemPosition: Int
    ) {

        // Analyze all the views
        for (viewPosition in firstVisibleItemPosition..lastVisibleItemPosition) {
            Log.i("View being considered", viewPosition.toString())

            // Get the view from its position.
            val itemView: View = recyclerView.layoutManager
                ?.findViewByPosition(viewPosition) as View

            // Check if the visibility of the view is more than or equal
            // to the threshold provided. If it falls under the desired limit,
            // add it to the tracking data.
            if (getVisibleHeightPercentage(itemView) >=
                minimumVisibleHeightThreshold
            ) {
                viewsViewed.add(viewPosition)
            }
        }
    }

    // Method to calculate how much of the view is visible
    // (i.e. within the screen) wrt the view height.
    // @param view
    // @return Percentage of the height visible.
    private fun getVisibleHeightPercentage(view: View): Double {
        val itemRect = Rect()
        view.getLocalVisibleRect(itemRect)

        // Find the height of the item.
        val visibleHeight: Double = itemRect.height().toDouble()
        val height: Double = view.measuredHeight.toDouble()
        Log.i("Visible Height", visibleHeight.toString())
        Log.i("Measured Height", height.toString())
        val viewVisibleHeightPercentage = visibleHeight / height * 100
        Log.i("Percentage visible", viewVisibleHeightPercentage.toString())
        Log.i("___", "___")
        return viewVisibleHeightPercentage
    }

    // Method to store the tracking data in an instance of "TrackingData" and
    // then returning that instance.
    // @param viewId
    // @param viewDuration in seconds.
    private fun prepareTrackingData(
        viewId: String,
        viewDuration: Long
    ): TrackingData {
        val trackingData = TrackingData()
        trackingData.viewId = viewId
        trackingData.viewDuration = viewDuration
        return trackingData
    }
}