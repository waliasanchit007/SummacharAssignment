package `in`.summachar.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            title=""
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setLogo(R.drawable.summachaar_app_logo)

        }

        val viewModel:MainActivityViewModel = ViewModelProvider(this,MainActivityViewModel.Factory(this.application)).get(MainActivityViewModel::class.java)
        val TopNavigationView = findViewById<BottomNavigationView>(R.id.topNavigationView)
        val navController = findNavController(R.id.fragment)

        TopNavigationView.setupWithNavController(navController)



        //Initializing Click To See new Post Button
        val refreshFeedBtn =  findViewById<Button>(R.id.refresh_feed_btn)

        //Observing if article is fetched from api
        //if TRUE then show Click To See new Post Button
        viewModel.articleFetchedFromApi.observe(this, Observer {
            Log.i("TAG", "inside articleFetched = $it")
            if(it==true) {
                refreshFeedBtn.visibility = View.VISIBLE
            }else refreshFeedBtn.visibility = View.GONE
        })

        //Click Listener for Click To See new Post Button
        refreshFeedBtn.setOnClickListener {
            viewModel.onRefreshButtonClicked()
            it.visibility = View.GONE

        }


    }
}