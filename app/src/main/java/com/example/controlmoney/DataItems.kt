package com.example.controlmoney

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.lang.Exception

class DataItems {
 val colorsParameters = listOf(
  // Синие
  ColorParameters(Color.parseColor("#BBDEFB"), true),
  ColorParameters(Color.parseColor("#90CAF9")),
  ColorParameters(Color.parseColor("#64B5F6")),
  ColorParameters(Color.parseColor("#42A5F5")),
  ColorParameters(Color.parseColor("#2196F3")),
  ColorParameters(Color.parseColor("#1E88E5")),
  ColorParameters(Color.parseColor("#1976D2")),
  ColorParameters(Color.parseColor("#1565C0")),
  ColorParameters(Color.parseColor("#0D47A1")),
  ColorParameters(Color.parseColor("#2A2A5F")),
  // Бирюзовые
  ColorParameters(Color.parseColor("#80D8FF")),
  ColorParameters(Color.parseColor("#40C4FF")),
  ColorParameters(Color.parseColor("#00B0FF")),
  ColorParameters(Color.parseColor("#0091EA")),
  // Зеленые
  ColorParameters(Color.parseColor("#C8E6C9")),
  ColorParameters(Color.parseColor("#A5D6A7")),
  ColorParameters(Color.parseColor("#81C784")),
  ColorParameters(Color.parseColor("#66BB6A")),
  ColorParameters(Color.parseColor("#4CAF50")),
  ColorParameters(Color.parseColor("#43A047")),
  ColorParameters(Color.parseColor("#388E3C")),
  ColorParameters(Color.parseColor("#2E7D32")),
  ColorParameters(Color.parseColor("#1B5E20")),
  // Сине-зеленые
  ColorParameters(Color.parseColor("#B2DFDB")),
  ColorParameters(Color.parseColor("#80CBC4")),
  ColorParameters(Color.parseColor("#4DB6AC")),
  ColorParameters(Color.parseColor("#26A69A")),
  ColorParameters(Color.parseColor("#009688")),
  ColorParameters(Color.parseColor("#00897B")),
  ColorParameters(Color.parseColor("#00796B")),
  ColorParameters(Color.parseColor("#00695C")),
  ColorParameters(Color.parseColor("#004D40")),
  // Фиолетовые
  ColorParameters(Color.parseColor("#F3E5F5")),
  ColorParameters(Color.parseColor("#E1BEE7")),
  ColorParameters(Color.parseColor("#CE93D8")),
  ColorParameters(Color.parseColor("#BA68C8")),
  ColorParameters(Color.parseColor("#AB47BC")),
  ColorParameters(Color.parseColor("#9C27B0")),
  // Розовые
  ColorParameters(Color.parseColor("#FF80AB")),
  ColorParameters(Color.parseColor("#FF4081")),
  ColorParameters(Color.parseColor("#F50057")),
  ColorParameters(Color.parseColor("#C51162")),
  // Оранжевые
  ColorParameters(Color.parseColor("#FFCC80")),
  ColorParameters(Color.parseColor("#FFAB40")),
  ColorParameters(Color.parseColor("#FF9100")),
  ColorParameters(Color.parseColor("#FF6D00")),
  ColorParameters(Color.parseColor("#FF5722")),
  ColorParameters(Color.parseColor("#F4511E")),
  ColorParameters(Color.parseColor("#E64A19")),
  ColorParameters(Color.parseColor("#D84315")),
  ColorParameters(Color.parseColor("#BF360C")),
  ColorParameters(Color.parseColor("#FF7043")),
  // Желтые
  ColorParameters(Color.parseColor("#FFFF00")),
  ColorParameters(Color.parseColor("#FFF176")),
  ColorParameters(Color.parseColor("#FFEE58")),
  ColorParameters(Color.parseColor("#FFEB3B")),
  ColorParameters(Color.parseColor("#FDD835")),
  ColorParameters(Color.parseColor("#FBC02D")),
  ColorParameters(Color.parseColor("#F9A825")),
  ColorParameters(Color.parseColor("#F57F17")),
  ColorParameters(Color.parseColor("#FFD740")),
  ColorParameters(Color.parseColor("#FFC400")),
  ColorParameters(Color.parseColor("#FFAB00")),
  ColorParameters(Color.parseColor("#FF9800")),
  ColorParameters(Color.parseColor("#FFA726")),
  ColorParameters(Color.parseColor("#FB8C00")),
  ColorParameters(Color.parseColor("#F57C00")),
  ColorParameters(Color.parseColor("#EF6C00")),
  ColorParameters(Color.parseColor("#E65100")),
  ColorParameters(Color.parseColor("#FFEBEE")),
  ColorParameters(Color.parseColor("#FFCDD2")),
  ColorParameters(Color.parseColor("#EF9A9A")),
  ColorParameters(Color.parseColor("#E57373")),
  ColorParameters(Color.parseColor("#EF5350")),
  ColorParameters(Color.parseColor("#F44336")),
  ColorParameters(Color.parseColor("#E53935")),
  ColorParameters(Color.parseColor("#D32F2F")),
  ColorParameters(Color.parseColor("#C62828")),
  ColorParameters(Color.parseColor("#B71C1C")),
  ColorParameters(Color.parseColor("#FF5252")),
  ColorParameters(Color.parseColor("#FF1744")),
  ColorParameters(Color.parseColor("#D50000")),
  // Черный, белый, серый
  ColorParameters(Color.parseColor("#000000")), // Черный
  ColorParameters(Color.parseColor("#FFFFFF")), // Белый
  ColorParameters(Color.parseColor("#BDBDBD")), // Серый
 )

 val icons = listOf(
  ItemParameters(true, R.drawable.cafe),
  ItemParameters(false, R.drawable.car),
  ItemParameters(false, R.drawable.cinema),
  ItemParameters(false, R.drawable.circus),
  ItemParameters(false, R.drawable.delivery),
  ItemParameters(false, R.drawable.gym),
  ItemParameters(false, R.drawable.home),
  ItemParameters(false, R.drawable.ad),
  ItemParameters(false, R.drawable.bullseye),
  ItemParameters(false, R.drawable.casino),
  ItemParameters(false, R.drawable.christmas),
  ItemParameters(false, R.drawable.casino_),
  ItemParameters(false, R.drawable.casino_slot),
  ItemParameters(false, R.drawable.child),
  ItemParameters(false, R.drawable.clothes),
  ItemParameters(false, R.drawable.credit),
  ItemParameters(false, R.drawable.desert),
  ItemParameters(false, R.drawable.dress),
  ItemParameters(false, R.drawable.games),
  ItemParameters(false, R.drawable.hockey),
  ItemParameters(false, R.drawable.hunter),
  ItemParameters(false, R.drawable.iphone),
  ItemParameters(false, R.drawable.perfume),
  ItemParameters(false, R.drawable.plane),
  ItemParameters(false, R.drawable.plant),
  ItemParameters(false, R.drawable.repair),
  ItemParameters(false, R.drawable.skate),
  ItemParameters(false, R.drawable.security_camera),
  ItemParameters(false, R.drawable.skate_boy),
  ItemParameters(false, R.drawable.subway),
  ItemParameters(false, R.drawable.soap),
  ItemParameters(false, R.drawable.suitcase),
  ItemParameters(false, R.drawable.museum)
 )

  val currencies = mutableListOf(
 CurrencyParameters("BYN"),
 CurrencyParameters("RUB", true),
 CurrencyParameters("USD"),
 CurrencyParameters("EUR"),
 CurrencyParameters("JPY"),
 CurrencyParameters("GBP"),
 CurrencyParameters("AUD"),
 CurrencyParameters("CAD"),
 CurrencyParameters("CHF"),
 CurrencyParameters("CNY"),
 CurrencyParameters("SEK"),
 CurrencyParameters("NZD")
 )



 data class ItemParameters(
  var isSelected: Boolean,
  val image: Int,
 )
 data class ColorParameters (
  val color: Int,
  var chooseFlag: Boolean = false,
  )
 data class CurrencyParameters (
  val nameCurrency: String,
  var chooseFlag: Boolean = false,
  var prices: Double = 1.0
 )
 data class ItemFinanceParameters(
  val name: String,
  var amount: Double,
  val color: Int,
  var image: Int,
  var currency: String,
  var convertCurrency: Double = amount
 )
}