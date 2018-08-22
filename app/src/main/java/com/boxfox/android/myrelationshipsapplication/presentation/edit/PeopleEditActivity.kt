package com.boxfox.android.myrelationshipsapplication.presentation.edit

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import com.boxfox.android.myrelationshipsapplication.R
import com.boxfox.android.myrelationshipsapplication.entity.Family
import com.boxfox.android.myrelationshipsapplication.entity.People
import com.boxfox.android.myrelationshipsapplication.entity.Story
import com.boxfox.android.myrelationshipsapplication.presentation.BaseActivity
import com.boxfox.android.myrelationshipsapplication.presentation.AddressSearchDialog
import kotlinx.android.synthetic.main.activity_people_edit.*
import java.util.*


class PeopleEditActivity : BaseActivity<PeopleEditPresneter>(), PeopleEditView {

    override val presenter: PeopleEditPresneter = PeopleEditPresneter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_edit)
        presenter.init(intent.getIntExtra("peopleId", -1))
        tv_people_edit_address.setOnClickListener { showAddressPicker() }
        btn_people_edit_family_add.setOnClickListener { addFamilyItem(null) }
        btn_people_edit_episode_add.setOnClickListener { addEpisodeItem(null) }
        btn_people_edit_check.setOnClickListener { presenter.save() }
    }

    fun addFamilyItem(family: Family?): View {
        val view = this.layoutInflater.inflate(R.layout.view_family_edit_item, null)
        val typeSpinner = view.findViewById<Spinner>(R.id.spinnet_family_edit_type)
        val spinnerItems = arrayOf(getString(R.string.family), getString(R.string.family_parent), getString(R.string.family_couple), getString(R.string.family_children), getString(R.string.family_brother), getString(R.string.family_sisters), getString(R.string.family_etc))
        val adapter = ArrayAdapter<String>(this, R.layout.view_family_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.setAdapter(adapter)
        view.findViewById<ImageView>(R.id.iv_family_edit_delete).setOnClickListener { layout_people_edit_family.removeView(view) }
        layout_people_edit_family.addView(view, layout_people_edit_family.childCount)
        if (family != null) {
            typeSpinner.setSelection(spinnerItems.indexOf(family.relationship))
            view.findViewById<EditText>(R.id.et_family_edit_name).setText(family.name)
            view.findViewById<EditText>(R.id.et_family_edit_age).setText(family.age.toString())
            view.findViewById<EditText>(R.id.et_family_edit_desc).setText(family.desc)
        }
        return view
    }

    fun addEpisodeItem(episode: Story?): View {
        val view = this.layoutInflater.inflate(R.layout.view_episode_edit_item, null)
        view.findViewById<TextView>(R.id.tv_episode_edit_date).setOnClickListener { it -> presenter.onDateFieldClicked(it as TextView) }
        view.findViewById<ImageView>(R.id.iv_episode_edit_delete).setOnClickListener { layout_people_edit_episode.removeView(view) }
        layout_people_edit_episode.addView(view, layout_people_edit_episode.childCount)
        if (episode != null) {
            view.findViewById<TextView>(R.id.tv_episode_edit_date).text = episode.date
            view.findViewById<EditText>(R.id.et_episode_edit_content).setText(episode.content)
        }
        return view
    }

    override fun showDatePicker(listener: DatePickerDialog.OnDateSetListener) {
        val currentDT = Calendar.getInstance()
        val dialog = DatePickerDialog(this, listener, currentDT.get(Calendar.YEAR), currentDT.get(Calendar.MONTH), currentDT.get(Calendar.DAY_OF_MONTH))
        dialog.show()
    }

    override fun showAddressPicker() {
        val dialog = AddressSearchDialog(this)
        dialog.setOnDismissListener {
            tv_people_edit_address.setText(dialog.getAddress())
        }
        dialog.show()
    }

    override fun initData(data: People) {
        et_people_edit_name.setText(data.name)
        et_people_edit_age.setText(data.age.toString())
        et_people_edit_phone.setText(data.phone)
        tv_people_edit_address.text = data.address
        data.familys.forEach { addFamilyItem(it) }
        data.storys.forEach { addEpisodeItem(it) }
    }

    override fun getData(): People {
        var age = 0
        if (et_people_edit_age.text.length > 0) {
            age = et_people_edit_age.text.toString().toInt()
        }
        return People().apply {
            this.name = et_people_edit_name.text.toString()
            this.age = age
            this.phone = et_people_edit_phone.text.toString()
            this.address = tv_people_edit_address.text.toString()
            this.familys = getFamilys()
            this.storys = getEpisodes()
        }
    }

    fun getFamilys(): List<Family> {
        val familys = ArrayList<Family>()
        for (i in 0 until layout_people_edit_family.childCount) {
            val familyEditView = layout_people_edit_family.getChildAt(i)
            var age = 0
            if (familyEditView.findViewById<EditText>(R.id.et_family_edit_age).text.length > 0) {
                age = familyEditView.findViewById<EditText>(R.id.et_family_edit_age).text.toString().toInt()
            }
            familys.add(Family().apply {
                this.name = familyEditView.findViewById<EditText>(R.id.et_family_edit_name).text.toString()
                this.desc = familyEditView.findViewById<EditText>(R.id.et_family_edit_desc).text.toString()
                this.age = age
                this.relationship = familyEditView.findViewById<Spinner>(R.id.spinnet_family_edit_type).selectedItem.toString()
            })
        }
        return familys
    }

    fun getEpisodes(): List<Story> {
        val episodes = ArrayList<Story>()
        for (i in 0 until layout_people_edit_episode.childCount) {
            val episodeEditView = layout_people_edit_episode.getChildAt(i)
            episodes.add(Story().apply {
                date = episodeEditView.findViewById<TextView>(R.id.tv_episode_edit_date).text.toString()
                content = episodeEditView.findViewById<EditText>(R.id.et_episode_edit_content).text.toString()
            })
        }
        return episodes
    }
}
