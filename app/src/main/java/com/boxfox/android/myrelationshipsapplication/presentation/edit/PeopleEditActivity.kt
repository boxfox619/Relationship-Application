package com.boxfox.android.myrelationshipsapplication.presentation.edit

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import com.boxfox.android.myrelationshipsapplication.R
import com.boxfox.android.myrelationshipsapplication.presentation.BaseActivity
import com.boxfox.android.myrelationshipsapplication.presentation.AddressSearchDialog
import kotlinx.android.synthetic.main.activity_people_edit.*


class PeopleEditActivity : BaseActivity<PeopleEditPresneter>(), PeopleEditView {

    override val presenter: PeopleEditPresneter = PeopleEditPresneter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_edit)
        tv_people_edit_address.setOnClickListener{showAddressPicker()}
        btn_people_edit_family_add.setOnClickListener{addFamilyItem()}
        btn_people_edit_episode_add.setOnClickListener{addEpisodeItem()}
    }

    fun addFamilyItem(){
        val view = this.layoutInflater.inflate(R.layout.view_family_edit_item, null)
        val typeSpinner = view.findViewById<Spinner>(R.id.spinnet_family_edit_type)
        val spinnerItems = arrayOf(getString(R.string.family_husband), getString(R.string.family_wife), getString(R.string.family_son), getString(R.string.family_daughter))
        val adapter = ArrayAdapter<String>(this, R.layout.view_family_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.setAdapter(adapter)
        view.findViewById<ImageView>(R.id.iv_family_edit_delete).setOnClickListener {layout_people_edit_family.removeView(view)}
        layout_people_edit_family.addView(view, layout_people_edit_family.childCount - 1)
    }

    fun addEpisodeItem(){
        val view = this.layoutInflater.inflate(R.layout.view_episode_edit_item, null)
        view.findViewById<TextView>(R.id.tv_episode_edit_date).setOnClickListener{it -> presenter.onDateFieldClicked(it as TextView)}
        view.findViewById<ImageView>(R.id.iv_episode_edit_delete).setOnClickListener {layout_people_edit_family.removeView(view)}
        layout_people_edit_episode.addView(view, layout_people_edit_episode.childCount - 1)
    }

    override fun showDatePicker(listener: DatePickerDialog.OnDateSetListener) {
        val dialog = DatePickerDialog(this, listener, 2013, 10, 22)
        dialog.show()
    }

    override fun showAddressPicker(){
        val dialog = AddressSearchDialog(this)
        dialog.setOnDismissListener {
            tv_people_edit_address.setText(dialog.getAddress())
        }
        dialog.show()
    }
}
