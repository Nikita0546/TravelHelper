package com.example.travelhelper

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.map.VisibleRegionUtils
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError

class map : AppCompatActivity(), UserLocationObjectListener, Session.SearchListener, CameraListener{
    lateinit var mapview: MapView
    lateinit var probkibut: Button
    lateinit var locationmapkit: UserLocationLayer
    lateinit var searchEdit: EditText
    lateinit var searchManager: SearchManager
    lateinit var searchSession: Session

    private fun sumbitQuery(query:String){
        searchSession = searchManager.submit(query, VisibleRegionUtils.toPolygon(mapview.map.visibleRegion), SearchOptions(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("0cb32755-6324-4d6b-ac89-5115c6a24d35")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_map)
        mapview = findViewById(R.id.mapview)
        mapview.map.move(CameraPosition(Point(35.681729, 139.753927), 11.0f, 0.0f, 0.0f),
        Animation(Animation.Type.SMOOTH, 10f), null)
        var mapkit: MapKit = MapKitFactory.getInstance()
        requestLocationPermission()
        var probki = mapkit.createTrafficLayer(mapview.mapWindow)
        probki.isTrafficVisible = false
        probkibut = findViewById(R.id.probkibut)
        probkibut.setOnClickListener {
            if (probki.isTrafficVisible == false){
                probki.isTrafficVisible = true
                probkibut.setBackgroundColor(R.drawable.circle)
            }
            else{
                probki.isTrafficVisible = false
                probkibut.setBackgroundColor(R.drawable.round)
            }
        }
        locationmapkit = mapkit.createUserLocationLayer(mapview.mapWindow)
        locationmapkit.isVisible = true
        locationmapkit.setObjectListener(this)
        SearchFactory.getInstance()
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)
        mapview.map.addCameraListener(this)
        searchEdit = findViewById(R.id.searh_edit)
        searchEdit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                sumbitQuery(searchEdit.text.toString())
            }
            false
        }
    }
    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        }
    }


    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapview.onStart()
    }

    override fun onStop() {
        mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {
        locationmapkit.setAnchor(
            PointF((mapview.width *0.5).toFloat(), (mapview.height *0.5).toFloat()),
            PointF((mapview.width *0.5).toFloat(), (mapview.height *0.83).toFloat()),
        )
        userLocationView.arrow.setIcon(ImageProvider.fromResource(this, R.drawable.arrow))
        val picIcon = userLocationView.pin.useCompositeIcon()
        picIcon.setIcon("icon", ImageProvider.fromResource(this, R.drawable.gps), IconStyle().
        setAnchor(PointF(0f,0f))
            .setRotationType(RotationType.NO_ROTATION).setScale(1f)
        )
        picIcon.setIcon("pin", ImageProvider.fromResource(this, R.drawable.nothing),
            IconStyle().setAnchor(PointF(0.5f, 0.5f)).setRotationType(RotationType.ROTATE).setZIndex(1f).setScale(0.5f))
        userLocationView.accuracyCircle.fillColor = Color.BLUE and -0x66000001
    }

    override fun onObjectRemoved(p0: UserLocationView) {
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
    }

    override fun onSearchResponse(response: Response) {

    }


    override fun onSearchError(error: Error) {
        var errorMessage = "Неизвестная ошибка!"
        if (error is RemoteError){
            errorMessage = "Беспроводная ошибка!"
        }else if (error is NetworkError){
           errorMessage = "Проблема с интернетом!"
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onCameraPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        cameraUpdateReason: CameraUpdateReason,
        finished: Boolean
    ) {
        if (finished){
            sumbitQuery(searchEdit.text.toString())
        }
    }
}

