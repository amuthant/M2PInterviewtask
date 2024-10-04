package com.example.m2pinterviewtask

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class CurrencyEditText : AppCompatEditText {

    private val locale: Locale = Locale.US
    private val currency: Currency = Currency.getInstance(locale)
    private var maxLength: Int = 30
    private var value = 0.0
    private var isFormatting: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isFormatting) return

                isFormatting = true
                var cleanString = s.toString().replace("[^\\d.]".toRegex(), "")

                val dotIndex = cleanString.indexOf(".")
                val integerPart: String
                var decimalPart: String = ""

                if (dotIndex != -1) {
                    integerPart = cleanString.substring(0, dotIndex)
                    if (cleanString.length > dotIndex + 1) {
                        decimalPart = cleanString.substring(dotIndex + 1).take(2)
                    }
                } else {
                    integerPart = cleanString
                }

                if (integerPart.length > maxLength) {
                    cleanString = integerPart.substring(0, maxLength)
                }

                val formattedIntegerPart = if (integerPart.isNotEmpty()) {
                    NumberFormat.getIntegerInstance(locale).format(integerPart.toLong())
                } else {
                    "0"
                }

                val formattedValue = if (decimalPart.isNotEmpty()) {
                    "$formattedIntegerPart.$decimalPart"
                } else if (dotIndex != -1) {
                    "$formattedIntegerPart."
                } else {
                    formattedIntegerPart
                }

                removeTextChangedListener(this)
                setText("$" + formattedValue)
                setSelection(formattedValue.length + 1)
                addTextChangedListener(this)

                isFormatting = false
            }
        })

        if (text.isNullOrEmpty()) {
            setText("$0")
            setSelection(2)
        }
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)

        if (selectionEnd != getEndingSelection()) {
            setSelection(getEndingSelection())
        }
    }

    fun getNumericValue(): Double {
        return value
    }

    private fun getEndingSelection(): Int {
        val string = text.toString()

        return if (string.isNotEmpty() && string.last().isDigit()) {
            string.length
        } else {
            string.replace("[${currency.symbol}\\s]".toRegex(), "").length
        }
    }
}
