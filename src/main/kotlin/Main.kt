package converter

import java.math.BigInteger

const val DIGITS_OUT = 5
const val FIRST_MSG = "Enter two numbers in format: {source base} {target base} (To quit type /exit)"
const val SECOND_MSG = "Enter number in base %s to convert to base %s (To go back type /back)"


fun main() {

    while (true) {
        println(FIRST_MSG)
        when (val ans1 = readLine()!!.lowercase()) {
            "/exit" -> break
            else -> {
                val (source, target) = ans1.split(" ", limit = 2)
                while (true) {
                    println(SECOND_MSG.format(source, target))
                    when (val ans2 = readLine()!!.lowercase()) {
                        "/back" -> break
                        else -> {
                            val inStrParts = ans2.trim().split(".", limit = 2)
                            val intPartInStr = inStrParts[0]
                            var decPartInStr = if (inStrParts.size > 1) inStrParts[1] else ""

                            val intPart = if (intPartInStr.isBlank()) {
                                BigInteger.ZERO
                            } else {
                                BigInteger(intPartInStr, source.toInt())
                            }
                            val intPartOutStr = intPart.toString(target.toInt())

                            if (decPartInStr.isBlank())
                                println("Conversion result: $intPartOutStr")
                            else {
                                decPartInStr = decPartInStr.trimEnd('0')
                                if (decPartInStr.isBlank()) {
                                    println("Conversion result: $intPartOutStr.00000")
                                } else {
                                    val powSource = BigInteger(source).pow(decPartInStr.length)
                                    val powTarget = BigInteger(target).pow(DIGITS_OUT)
                                    val num = BigInteger(decPartInStr, source.toInt()).multiply(powTarget).divide(powSource)
                                    val decPartOutStr = num.toString(target.toInt()).padStart(DIGITS_OUT, '0')
                                    println("Conversion result: $intPartOutStr.$decPartOutStr")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}