package com.example.controlmoney

import android.graphics.Color
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.ColorsAdapter
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.IconsAdapter

class DataStyleItems {
    val colors = listOf(
        ColorsAdapter.ColorParameters(Color.parseColor("#2A2A5F"), true),
        ColorsAdapter.ColorParameters(Color.parseColor("#3D5AFE"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#536DFE"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#1E88E5"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#1976D2"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#1565C0"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#0D47A1"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#42A5F5"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#64B5F6"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#90CAF9"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#90CAF9"), false),
        ColorsAdapter.ColorParameters(Color.parseColor("#BBDEFB"), false)
    )
     val icons = listOf(
        IconsAdapter.ItemParameters(true, R.drawable.cafe),
        IconsAdapter.ItemParameters(false, R.drawable.car),
        IconsAdapter.ItemParameters(false, R.drawable.cinema),
        IconsAdapter.ItemParameters(false, R.drawable.circus),
        IconsAdapter.ItemParameters(false, R.drawable.delivery),
        IconsAdapter.ItemParameters(false, R.drawable.gym),
        IconsAdapter.ItemParameters(false, R.drawable.home),
        IconsAdapter.ItemParameters(false, R.drawable.museum)
    )
}