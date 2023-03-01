package com.example.webanttrainee.presentation.ui.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.webanttrainee.R
import com.example.webanttrainee.databinding.ActivityMainBinding
import com.example.webanttrainee.presentation.ui.base.BaseViewModel
import com.example.webanttrainee.presentation.ui.fragments.popular.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val topLevelDestinations = setOf(R.id.newFragment, R.id.popularFragment)
    private val compositeDisposable = CompositeDisposable()

    private val vm by viewModels<MainActivityViewModel>()

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestinations + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(topLevelDestinations)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        val observable = Observable.create<String> { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    return false
                }
            })
        }
            .map { text -> text.lowercase().trim() }
            .debounce(250, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotBlank() }
            .distinctUntilChanged()
            .subscribe { text ->
                Log.d("TAG", "subscriber: $text")
//                vm.getGifsByPhrase(text)
            }

        compositeDisposable.add(observable)
        return true
    }

    override fun onBackPressed() {
        if (isStartDestination(navController.currentDestination)) {
            super.onBackPressed()
        } else {
            navController.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean = (navController.navigateUp()) || super.onSupportNavigateUp()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}