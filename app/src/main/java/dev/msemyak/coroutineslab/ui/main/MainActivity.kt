package dev.msemyak.coroutineslab.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dev.msemyak.coroutineslab.R
import dev.msemyak.coroutineslab.data.model.Country
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainPresenter by inject()

    private var rvListAdapter: RVAdapterCountry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoad.setOnClickListener {
            tvLoad.visibility = View.INVISIBLE
            pbMain.visibility = View.VISIBLE
            presenter.loadData()
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun showData(countries: List<Country>) {
        pbMain.visibility = View.INVISIBLE

        if (rvListAdapter == null) {
            rvMain.layoutManager = LinearLayoutManager(this)

            val dividerItemDecoration = DividerItemDecoration(rvMain.context, DividerItemDecoration.VERTICAL)
            rvMain.addItemDecoration(dividerItemDecoration)

            rvListAdapter = RVAdapterCountry(countries, this)
            rvMain.adapter = rvListAdapter
        } else {
            rvListAdapter?.setNewData(countries)
        }

    }

    override fun showToast(message: String) {
        Snackbar.make(clRoot, message, Snackbar.LENGTH_LONG).show()
    }
}
