package tictactoe
// First list which is empty of X or O
val txt = MutableList(9){' '}
fun main() {
// printing first list without change.
    printGame(txt)
// variable to make the loop continue until the game finished
    var i = 0
// list of Int which will be used with add corresponding value that returned from addToCord() to keep tracking entered coordinates.
    val coordinate: MutableList<Int> = mutableListOf()
// Two variable of entering X and O to control game player turn.
    var cX = 0
    var cO = 1
// The main loop of the game that allow user to enter coordinates
    loop@while (i >= 0) {
        print("Enter the coordinates:")
// I use try and catch to know if the user entered numbers or simple text that couldn't be cast to Int.
        try {
// enter coordinate.
            val (x, y) = readLine()!!.split(" ").map { it.toInt() }
 // check if the coordinate in 1 to 3
            if (checkCord(x, y) != null) {
 // check if the coordinate occupied
                if (addToCord(x,y) !in coordinate){
                    // add the new coordinate to the list
                    addToCord(x,y)?.let { coordinate.add(it) }
                    // check the turn of player X or O
                    if(cX < cO){
                        // add X
                        txt[checkCord(x,y)!!]= addX()
                        // increase the turn to flip next turn to O
                        ++cX
                        // print game state
                        printGame(txt)
                        ++i
                        // check if any player get won
                        if(gameWon()){
                            // Break main loop after X win
                            break@loop
                        }else{
                            ++i
                        }
                    }else{
                        txt[checkCord(x,y)!!]= addO()
                        ++cO
                        printGame(txt)
                        if(gameWon()){
                            // Break main loop after O win
                            break@loop
                        }else{
                            ++i
                        }
                    }
                }else {
                    println("This cell is occupied! Choose another one!")
                    ++i
                }
            } else {
                println("Coordinates should be from 1 to 3!")
                ++i
            }
        }catch (E:Exception){
            println("You should enter numbers!")
            ++i
        }
    }
}
// function to check the state of the game
fun gameWon():Boolean {

    var o = 0
    var x = 0
    for(i in txt){
        if (i == 'O'){
            ++o
        } else if (i == 'X'){
            ++x
        }
    }

// checking rows, column and tangent lines which one have three X or O.
    val row1X = txt[0] == 'X' && txt[1] == 'X' && txt[2] == 'X'
    val row2X = txt[3] == 'X' && txt[4] == 'X' && txt[5] == 'X'
    val row3X = txt[6] == 'X' && txt[7] == 'X' && txt[8] == 'X'
    val column1X = txt[0] == 'X' && txt[3] == 'X' && txt[6] == 'X'
    val column2X = txt[1] == 'X' && txt[4] == 'X' && txt[7] == 'X'
    val column3X = txt[2] == 'X' && txt[5] == 'X' && txt[8] == 'X'
    val ray1X = txt[0] == 'X' && txt[4] == 'X' && txt[8] == 'X'
    val ray2X = txt[2] == 'X' && txt[4] == 'X' && txt[6] == 'X'
    val row1O = txt[0] == 'O' && txt[1] == 'O' && txt[2] == 'O'
    val row2O = txt[3] == 'O' && txt[4] == 'O' && txt[5] == 'O'
    val row3O = txt[6] == 'O' && txt[7] == 'O' && txt[8] == 'O'
    val column1O = txt[0] == 'O' && txt[3] == 'O' && txt[6] == 'O'
    val column2O = txt[1] == 'O' && txt[4] == 'O' && txt[7] == 'O'
    val column3O = txt[2] == 'O' && txt[5] == 'O' && txt[8] == 'O'
    val ray1O = txt[0] == 'O' && txt[4] == 'O' && txt[8] == 'O'
    val ray2O = txt[2] == 'O' && txt[4] == 'O' && txt[6] == 'O'
    // count the cells which is doesn't have X or O
    var emptySpace = 0
    for(i in txt) {
        if (i == ' '){
            ++emptySpace
        }
    }
    // state of X win or O win
    val xWin = ray1X || ray2X || row1X || row2X || row3X || column1X || column2X || column3X && (x - o == 1 || o - x ==1)
    val oWin = ray1O || ray2O || row1O || row2O || row3O || column1O || column2O || column3O && (x - o == 1 || o - x ==1)
    // conditions that return the state of the game and print it
    return if (xWin){
        println("X wins")
        true
    }else if (oWin){
        println("O wins")
        true
    }else if (emptySpace == 0 && (x == o || !xWin || !oWin)) {
        println("Draw")
        true
    }else {
        false
    }

}


// function to print game after each play

fun printGame(txt:MutableList<Char>){
    println("---------")
    println("| ${txt[0]} ${txt[1]} ${txt[2]} |")
    println("| ${txt[3]} ${txt[4]} ${txt[5]} |")
    println("| ${txt[6]} ${txt[7]} ${txt[8]} |")
    println("---------")
}
// function return X as Char
fun addX():Char = 'X'
// function return O as Char
fun addO():Char = 'O'
// function to check the input coordinate and return the index for the main list to be added by X or O.
fun checkCord(x:Int, y:Int):Int? {
    return when {
        x==1 && y==1 -> 0
        x==1 && y==2 -> 1
        x==1 && y==3 -> 2
        x==2 && y==1 -> 3
        x==2 && y==2 -> 4
        x==2 && y==3 -> 5
        x==3 && y==1 -> 6
        x==3 && y==2 -> 7
        x==3 && y==3 -> 8
        else -> null
    }
}
// function used to check if the coordinate already entered or not.
fun addToCord(x: Int, y: Int):Int?{
    return when {
        x == 1 && y == 1 -> 1
        x == 1 && y == 2 -> 2
        x == 1 && y == 3 -> 3
        x == 2 && y == 1 -> 4
        x == 2 && y == 2 -> 5
        x == 2 && y == 3 -> 6
        x == 3 && y == 1 -> 7
        x == 3 && y == 2 -> 8
        x == 3 && y == 3 -> 9
        else -> null
    }
}