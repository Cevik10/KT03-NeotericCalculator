package com.hakancevik.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.text.SpannableStringBuilder
import android.view.View
import com.hakancevik.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var selection: SpannableStringBuilder
    private lateinit var exp: Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.displayText.showSoftInputOnFocus = false  // close keyboard

        binding.displayText.setOnClickListener {
            if (getString(R.string.display_text) == binding.displayText.text.toString()) {
                binding.displayText.setText("")
            }
        }


    }


    private fun updateText(strToAdd: String) {

        var oldStr = binding.displayText.text.toString()
        var cursorPos = binding.displayText.selectionStart
        var leftStr = oldStr.substring(0, cursorPos)
        var rightStr = oldStr.substring(cursorPos)

        if (getString(R.string.display_text) == binding.displayText.text.toString()) {
            binding.displayText.setText(strToAdd)
            binding.displayText.setSelection(cursorPos + 1)
        } else {
            binding.displayText.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
            binding.displayText.setSelection(cursorPos + 1)
        }


    }

    fun backspaceButton(view: View) {
        var cursorPos = binding.displayText.selectionStart
        var textLen = binding.displayText.text.length

        if (cursorPos != 0 && textLen != 0) {
            selection = binding.displayText.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            binding.displayText.text = selection
            binding.displayText.setSelection(cursorPos - 1)

        }

    }


    fun zeroButton(view: View) {
        updateText("0")
    }

    fun oneButton(view: View) {
        updateText("1")

    }

    fun twoButton(view: View) {
        updateText("2")

    }

    fun threeButton(view: View) {

        updateText("3")
    }

    fun fourButton(view: View) {
        updateText("4")

    }

    fun fiveButton(view: View) {

        updateText("5")
    }

    fun sixButton(view: View) {
        updateText("6")

    }

    fun sevenButton(view: View) {

        updateText("7")
    }

    fun eightButton(view: View) {
        updateText("8")

    }

    fun nineButton(view: View) {
        updateText("9")

    }


    fun minusAndPosButton(view: View) {
        updateText("-")

    }

    fun dotButton(view: View) {
        updateText(".")

    }

    fun clearButton(view: View) {
        binding.displayText.setText("")

    }

    fun bracketsButton(view: View) {

        var cursorPos = binding.displayText.selectionStart
        var openBracket = 0
        var closedBracket = 0
        var textLen = binding.displayText.text.length

        for (i in 0 until cursorPos) {
            if (binding.displayText.text.toString().substring(i, i + 1).equals("(")) {
                openBracket += 1
            }
            if (binding.displayText.text.toString().substring(i, i + 1).equals(")")) {
                closedBracket += 1
            }
        }

        if (openBracket == closedBracket || binding.displayText.text.toString()
                .substring(textLen - 1, textLen).equals("(")
        ) {
            updateText("(")
        } else if (closedBracket < openBracket && !binding.displayText.text.toString()
                .substring(textLen - 1, textLen).equals("(")
        ) {
            updateText(")")
        }
        binding.displayText.setSelection(cursorPos + 1)


    }

    fun expButton(view: View) {
        updateText("^")
    }

    fun divideButton(view: View) {
        updateText("÷")
    }

    fun multiplyButton(view: View) {
        updateText("×")
    }

    fun subtractButton(view: View) {
        updateText("-")
    }

    fun sumButton(view: View) {
        updateText("+")
    }

    fun equalButton(view: View) {


        var userExp = binding.displayText.text.toString()

        userExp = userExp.replace("÷", "/")
        userExp = userExp.replace("×", "*")

        exp = Expression(userExp)

        var result: String = exp.calculate().toString()
        binding.displayText.setText(result)
        binding.displayText.setSelection(result.length)


    }


}