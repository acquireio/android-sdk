package com.acquiresdk.activities.ui.settings

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.acquiresdk.R
import com.acquiresdk.utils.ToolbarUtils
import com.lyc.colorpicker.HueBarView
import com.lyc.colorpicker.SVColorPickerView
import java.lang.String

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false) as View
        val activity = getActivity() as Activity

        val toolbarInstance = ToolbarUtils()
        toolbarInstance.setToolbar(
            activity,
            root,
            getString(R.string.title_settings),
            enableBack = false,
            enableSearch = false,
            enableVideo = false,
            enableAudio = false
        )

        val llSMasking: LinearLayout = root.findViewById(R.id.llSMasking)
        llSMasking.addView(getColorView(activity));
        val llSFabTextColor: LinearLayout = root.findViewById(R.id.llSFabTextColor)
        llSFabTextColor.addView(getColorView(activity));
        val llSFabButtonColor: LinearLayout = root.findViewById(R.id.llSFabButtonColor)
        llSFabButtonColor.addView(getColorView(activity));

        return root
    }

    fun getColorView(activity: Context): View {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.item_colors, null)

        val imgSColor1: ImageView = view.findViewById(R.id.imgSColor1)
        imgSColor1.setColorFilter(Color.parseColor("#123399"), PorterDuff.Mode.SRC_ATOP)

        val imgSColor2: ImageView = view.findViewById(R.id.imgSColor2)
        imgSColor2.setColorFilter(Color.parseColor("#FF4651"), PorterDuff.Mode.SRC_ATOP)

        val imgSColor3: ImageView = view.findViewById(R.id.imgSColor3)
        imgSColor3.setColorFilter(Color.parseColor("#FFBB00"), PorterDuff.Mode.SRC_ATOP)

        val imgSColor4: ImageView = view.findViewById(R.id.imgSColor4)
        imgSColor4.setColorFilter(Color.parseColor("#29B68D"), PorterDuff.Mode.SRC_ATOP)

        val imgSColor5: ImageView = view.findViewById(R.id.imgSColor5)
        imgSColor5.setColorFilter(Color.parseColor("#5C61F4"), PorterDuff.Mode.SRC_ATOP)

        val imgSColor6: ImageView = view.findViewById(R.id.imgSColor6)
        imgSColor6.setOnClickListener {
            showDialog(activity)
        }

        return view;
    }

    fun showDialog(ctx: Context) {
        val dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_color_picker)

        val pv: SVColorPickerView = dialog.findViewById(R.id.pickerDiaColor) as SVColorPickerView
        val hbv: HueBarView = dialog.findViewById(R.id.hbv) as HueBarView
        val etColorHex: EditText = dialog.findViewById(R.id.etColorHex) as EditText
        val etColorR: EditText = dialog.findViewById(R.id.etColorR) as EditText
        val etColorG: EditText = dialog.findViewById(R.id.etColorG) as EditText
        val etColorB: EditText = dialog.findViewById(R.id.etColorB) as EditText

        pv.setColorPickerViewListener(object : SVColorPickerView.ColorPickerViewListener {
            override fun onColorChange(newColor: Int, colorHSV: FloatArray) {
                setColor(etColorHex, etColorR, etColorG, etColorB, newColor)
            }
        })

        hbv.currentHue = pv.hue
        hbv.setOnHueBarChangeListener(object : HueBarView.OnHueBarChangeListener {
            override fun onHueChange(newHue: Float) {
                pv.hue = newHue
            }
        })
        val currentColor = pv.getCurrentColor()
        setColor(etColorHex, etColorR, etColorG, etColorB, currentColor)

        val btColorSave: Button = dialog.findViewById(R.id.btColorSave) as Button
        btColorSave.setOnClickListener { dialog.dismiss() }
        dialog.setCanceledOnTouchOutside(true);
        dialog.show()
        val window = dialog.window as Window
        window.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    fun setColor(
        etColorHex: EditText,
        etColorR: EditText,
        etColorG: EditText,
        etColorB: EditText,
        currentColor: Int
    ) {
        val hexColor = String.format("#%06X", 0xFFFFFF and currentColor)
        etColorHex.setText(hexColor)

        etColorR.setText(String.valueOf(Color.red(currentColor)))
        etColorG.setText(String.valueOf(Color.green(currentColor)))
        etColorB.setText(String.valueOf(Color.blue(currentColor)))
    }
}