/**
 * Extension function to the 'Char' class
 * that checks whether the character is a
 * letter, digit or a punctuation mark
 */
fun Char.isLetterOrDigitOrPM(): Boolean {
    return this.isLetterOrDigit() || "!?,.:;'".contains(this)
}

fun main() {
    var tryAgain = true
    // While user wants to keep using the program, let them do so
    while (tryAgain) {
        print(
            "Please enter the text down below.\n" +
                    "To stop input, enter 'END_INPUT'.\n"
        )

        // Reading text
        var text = ""
        do {
            val inputStr = readLine()
            if (inputStr != "END_INPUT")
                text = text.plus(inputStr + '\n')
        } while (inputStr != "END_INPUT")

        // Reading length
        var lineLength: Int
        do {
            print(
                "Please enter the length of the line\n" +
                        "Note: length can't be less than 1 or greater than 500.\n"
            )
            lineLength = readLine()!!.toInt()
        } while (lineLength < 1 || lineLength > 500)

        // Reading alignment type
        var alignmentStr: String
        do {
            print(
                "Please enter the type of alignment you'd like\n" +
                        "Available types of alignment: LEFT, RIGHT, CENTER and JUSTIFIED.\n"
            )
            alignmentStr = readLine()!!
        } while (Alignment.values().all { it.name != alignmentStr.uppercase() })
        // The actual alignment parameter we get after receiving the name of alignment type
        val alignment = Alignment.values().find { it.name == alignmentStr.uppercase() }!!

        // Aligning text
        val ta = TextAlignerLab1(text)
        ta.alignText(lineLength, alignment)
        print(ta.text + '\n')

        // Checking if user wants to continue
        var answer:String
        do {
            print(
                "Would you like to continue?\n" +
                        "Enter 'Yes' to continue, 'No' to stop\n"
            )
            answer = readLine()!!
        } while (answer != "Yes" && answer != "No")
        tryAgain = answer == "Yes"
    }
}
