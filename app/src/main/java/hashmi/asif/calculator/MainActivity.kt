package hashmi.asif.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    // TODO: This give (functionality to  number button) to Add number
    fun onDigit(view: View)
    {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    // TODO: This give (functionality to  clear button) to clear All thing on text-view
    fun onClear(view: View)
    {
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false

    }

    // TODO: here, we add functionality of decimal point number (dikkat :: multiple decimal occur {eg. 1.23.53})
    fun onDecimalPoint(vew: View)
    {
        if(lastNumeric && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    // TODO:  functionality of operator
    fun onOperator(view: View)
    {
//        if(lastNumeric && !isOperatorAdded(tvInput.text.to)
        tvInput?.text?.let {

            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                    lastNumeric = false
                    lastDot = false
                }
        }
    }

//this function give (false) --> if number starts with {-}
//              give (True) --> if first number is not negative and number contain {/,*,-,+}
    private fun isOperatorAdded(value: String):Boolean
    {
        return if(value.startsWith("-")){
            false
        }
        else{   value.contains("/") ||
                value.contains("*") ||
                value.contains("-") ||
                value.contains("+")
        }
    }

    // TODO: equal functionality
    fun onEqual(view: View)
    {
        if(lastNumeric)
        {
            var tvValue = tvInput?.text.toString()

            //now we want to ignore starting -ve value
            var prefix = ""

            try
            {
                if(tvValue.startsWith("-"))
                {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-"))
                {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]     // number-1
                    var  two = splitValue[1]     // number-2

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.contains("+"))
                {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]     // number-1
                    var  two = splitValue[1]     // number-2

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("*"))
                {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]     // number-1
                    var  two = splitValue[1]     // number-2

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/"))
                {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]     // number-1
                    var  two = splitValue[1]     // number-2

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }
            catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String
    {
        var value = result
        if(result.contains(".0"))
            value = value.substring(0, value.length-2)

        return value
    }


}

