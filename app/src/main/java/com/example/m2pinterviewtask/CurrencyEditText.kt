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

    private val locale: Locale = Locale.US // Force to use US locale for dollar
    private val currency: Currency = Currency.getInstance(locale)
    private var maxLength: Int = 30
    private var value = 0.0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inputType = InputType.TYPE_CLASS_NUMBER
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var cleanString = s.toString().replace("\\D+".toRegex(), "") // Get only digits

                // If max length is exceeded, remove new digit
                if (cleanString.length > maxLength) {
                    cleanString = cleanString.substring(0, cleanString.length - 1)
                }

                // Handle empty string case to avoid NumberFormatException
                val parsed = if (cleanString.isNotEmpty()) cleanString.toDouble() else 0.0

                value = parsed / 100

                // Use US Dollar formatting
                val formatted = NumberFormat.getCurrencyInstance(locale).format(value)

                removeTextChangedListener(this) // Prevent calling this watcher when formatting the text

                setText(formatted)
                setSelection(getEndingSelection())

                addTextChangedListener(this)
            }
        })

        if (text.isNullOrEmpty()) {
            setText("$0.00") // Default display for empty input
        }
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)

        if (selectionEnd != getEndingSelection()) {
            setSelection(getEndingSelection())
        }
    }

    fun setMaxLength(maxLength: Int) {
        this.maxLength = maxLength + 2 // Add number of decimals
    }

    fun getNumericValue(): Double {
        return value
    }

    private fun getEndingSelection(): Int {
        val string = text.toString()

        return if (string.isNotEmpty() && string.last().isDigit()) {
            // Currency symbol is on the left side
            string.length
        } else {
            // Currency symbol is on the right side, the cursor must be before it
            string.replace("[${currency.symbol}\\s]".toRegex(), "").length
        }
    }
}
