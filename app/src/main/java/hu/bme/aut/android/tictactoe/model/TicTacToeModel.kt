package hu.bme.aut.android.tictactoe.model

import android.util.Log
import android.widget.Toast
import hu.bme.aut.android.tictactoe.R

object TicTacToeModel {

    const val EMPTY: Byte = 0
    const val CIRCLE: Byte = 1
    const val CROSS: Byte = 2

    var nextPlayer: Byte = CIRCLE

    private var model: Array<ByteArray> = arrayOf(
        byteArrayOf(EMPTY, EMPTY, EMPTY),
        byteArrayOf(EMPTY, EMPTY, EMPTY),
        byteArrayOf(EMPTY, EMPTY, EMPTY))

    fun resetModel() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                model[i][j] = EMPTY
            }
        }
    }

    fun getFieldContent(x: Int, y: Int): Byte {
        return model[x][y]
    }

    private fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) {
            CROSS
        } else {
            CIRCLE
        }
    }

    fun setFieldContent(x: Int, y: Int, content: Byte): Byte {
        changeNextPlayer()
        model[x][y] = content
        return content
    }

    fun checkGameOver() : Boolean{
        for(i in 0 until 3){
            if(rowWinner(i))
                return true
            if(columnWinner(model[i]))
                return true
        }
        if(checkDiagonalAWinner() || checkDiagonalBWinner())
            return true
        if(isDraw())
            return true
        return false
    }

    private fun columnWinner(array : ByteArray) : Boolean {
        if(array[0] == EMPTY)
            return false
        if(array[0] != array[1])
                return false
        if(array[1] != array[2])
            return false
        return true
    }

    private fun rowWinner(numOfCol : Int) : Boolean{
        if(model[0][numOfCol] == EMPTY)
            return false
        if(model[0][numOfCol] != model[1][numOfCol])
            return false
        if(model[1][numOfCol] != model[2][numOfCol])
            return false
        return true
    }

    private fun checkDiagonalAWinner() : Boolean{
        if(model[0][0] != model[1][1] || model[0][0] == EMPTY)
            return false
        if(model[1][1] != model[2][2])
            return false
        return true
    }

    private fun checkDiagonalBWinner() : Boolean{
        if(model[0][2] != model[1][1] || model[0][2] == EMPTY)
            return false
        if(model[1][1] != model[2][0])
            return false
        return true
    }

    private fun isDraw() : Boolean{
        for(i in 0 until 3){
            for(j in 0 until 3){
                if(model[i][j] == EMPTY)
                    return false
            }
        }
        return true
    }
}