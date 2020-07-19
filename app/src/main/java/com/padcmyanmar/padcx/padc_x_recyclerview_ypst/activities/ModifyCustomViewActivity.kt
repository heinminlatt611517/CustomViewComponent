package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R

class ModifyCustomViewActivity : BaseActivity() {

    companion object{
        fun newIntent(context: Context) : Intent{
            val intent=Intent(context,ModifyCustomViewActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_custom_view)
    }
}