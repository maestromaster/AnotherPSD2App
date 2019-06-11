package com.i.homefeature.presentation

import android.os.Bundle
import android.view.View.VISIBLE
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import com.i.homefeature.R
import com.i.homefeature.domain.HomePresenterContract
import com.i.security.model.accountInformation.BalancesInformation
import com.i.security.model.UserData
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity: AppCompatActivity(), HomeView{

    private val presenter: HomePresenterContract by inject {parametersOf(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home )
        loadData()
        retryButton.setOnClickListener { loadData() }
    }

    private fun loadData() {
        presenter.fetch(UserData("accountId", "name", "surname"))
    }

    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = GONE
    }

    override fun navigateToLogin() {
        finish()
    }

    override fun setOtherError() {
        statusText.visibility = VISIBLE
        statusText.text = getString(R.string.unknown_error_home)
        retryButton.visibility = VISIBLE
    }

    override fun hideError() {
        statusText.visibility = GONE
        retryButton.visibility = GONE
    }

    override fun showResult(data: BalancesInformation) {
        statusText.visibility = GONE
        card.visibility = VISIBLE
        iban.text = data.account.iban
        data.balances.firstOrNull()?.let {
            balance.text = "${it.balanceAmount.amount} ${it.balanceAmount.currency}"
        }
    }

    override fun hideResult() {
        statusText.visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}