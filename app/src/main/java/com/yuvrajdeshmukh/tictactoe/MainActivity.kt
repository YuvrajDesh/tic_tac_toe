package com.yuvrajdeshmukh.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var imageButton: ImageButton
    enum class PLAYERS{
        FIRSTPLAYER,
        SECONDPLAYER
    }
    enum class WINNER{
        FIRSTPLAYER1,
        SECONDPLAYER2,
        NONE
    }
    //instance variables
    var player:PLAYERS? = null
    var winner : WINNER? = null
    var player1options :ArrayList<Int> = ArrayList()
    var player2options :ArrayList<Int> = ArrayList()
    var disabledImages :ArrayList<ImageButton?> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player = PLAYERS.FIRSTPLAYER
    }

    fun onBtnPress(view: View) {
        imageButton = view as ImageButton
        var option : Int = 0
        val randomNumber = (Math.random() *9 + 1).toInt()
        when(randomNumber){
            1 ->tableLayout.setBackgroundColor(Color.CYAN)
            2 ->tableLayout.setBackgroundColor(Color.DKGRAY)
            3 ->tableLayout.setBackgroundColor(Color.MAGENTA)
            4 ->tableLayout.setBackgroundColor(Color.BLUE)
            5 ->tableLayout.setBackgroundColor(Color.LTGRAY)
            6 ->tableLayout.setBackgroundColor(Color.GRAY)
            7 ->tableLayout.setBackgroundColor(Color.DKGRAY)
            8 ->tableLayout.setBackgroundColor(Color.TRANSPARENT)
            9 ->tableLayout.setBackgroundColor(Color.YELLOW)
        }
        when(imageButton.id)
        {
            R.id.imageButton1 ->{
                option = 1
            }R.id.imageButton2 ->{
                option = 2
            }R.id.imageButton3 ->{
                option = 3
            }R.id.imageButton4 ->{
                option = 4
            }R.id.imageButton5 ->{
                option = 5
            }R.id.imageButton6 ->{
                option = 6
            }R.id.imageButton7 ->{
                option = 7
            }R.id.imageButton8 ->{
                option = 8
            }
            R.id.imageButton9 ->
            {
                option = 9
            }
        }
        action(option,imageButton)

    }
    fun action(option: Int,imageButton: ImageButton){
        if(player == PLAYERS.FIRSTPLAYER)
        {
            imageButton.setImageResource(R.drawable.x_letter)
            player1options.add(option)
            imageButton.isEnabled = false
            disabledImages.add(imageButton)
            player = PLAYERS.SECONDPLAYER
        }
        else if(player ==PLAYERS.SECONDPLAYER)
        {
            imageButton.setImageResource(R.drawable.o_letter)
            player2options.add(option)
            imageButton.isEnabled = false
            disabledImages.add(imageButton)
            player = PLAYERS.FIRSTPLAYER
        }
        winnerOfGame()


    }
    fun winnerOfGame()
    {
        if(player1options.contains(1)&& player1options.contains(2) && player1options.contains(3))
        {
            winner = WINNER.FIRSTPLAYER1
        }else if(player2options.contains(1)&& player2options.contains(2) && player2options.contains(3))
        {
            winner = WINNER.SECONDPLAYER2
        }else if(player1options.contains(4)&& player1options.contains(5) && player1options.contains(6))
        {
            winner = WINNER.FIRSTPLAYER1
        }else if(player2options.contains(4)&& player2options.contains(5) && player2options.contains(6))
        {
            winner = WINNER.SECONDPLAYER2
        }else if(player1options.contains(7)&& player1options.contains(8) && player1options.contains(9))
        {
            winner = WINNER.FIRSTPLAYER1
        }else if(player2options.contains(7)&& player2options.contains(8) && player2options.contains(9))
        {
            winner = WINNER.SECONDPLAYER2
        }
        else if(player1options.contains(1)&& player1options.contains(4) && player1options.contains(7))
        {
            winner = WINNER.FIRSTPLAYER1
        }else if(player2options.contains(1)&& player2options.contains(4) && player2options.contains(7))
        {
            winner = WINNER.SECONDPLAYER2
        }else if(player1options.contains(2)&& player1options.contains(5) && player1options.contains(8))
        {
            winner = WINNER.FIRSTPLAYER1
        }
        else if(player2options.contains(2)&& player2options.contains(5) && player2options.contains(8))
        {
            winner = WINNER.SECONDPLAYER2
        }else if(player1options.contains(3)&& player1options.contains(6) && player1options.contains(9))
        {
            winner = WINNER.FIRSTPLAYER1
        }
        else if(player2options.contains(3)&& player2options.contains(6) && player2options.contains(9))
        {
            winner = WINNER.SECONDPLAYER2
        } else if(player1options.contains(1)&& player1options.contains(5) && player1options.contains(9))
        {
            winner = WINNER.FIRSTPLAYER1
        }
        else if(player2options.contains(1)&& player2options.contains(5) && player2options.contains(9))
        {
            winner = WINNER.SECONDPLAYER2
        }else if(player1options.contains(3)&& player1options.contains(5) && player1options.contains(7))
        {
            winner = WINNER.FIRSTPLAYER1
        }else if(player2options.contains(3)&& player2options.contains(5) && player2options.contains(7))
        {
            winner = WINNER.SECONDPLAYER2
        }
        if (winner==WINNER.FIRSTPLAYER1)
        {
            createAlert(
                "Player One Wins","Congratulations Player 1",AlertDialog.BUTTON_POSITIVE,"OK",
                false
            )
            return

        }else if(winner==WINNER.SECONDPLAYER2)
        {
            createAlert(
                "Player Two Wins","Congratulations Player 2",AlertDialog.BUTTON_POSITIVE,"OK",
                false
            )
            return
        }
        checkDrawState()

    }
    fun createAlert(title:String,desc:String,whichButtn:Int,buttonText:String,cancellable:Boolean){
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(desc)
        builder.setNegativeButton("RESET"){dialogInterface, which ->
           resetGame()
        }
        builder.setCancelable(cancellable)
        builder.show()

    }
    fun resetGame(){
        player1options.clear()
        player2options.clear()
        disabledImages.clear()
        winner = WINNER.NONE
        player = PLAYERS.FIRSTPLAYER
        imageButton1.setImageResource(R.drawable.tictactoe)
        imageButton2.setImageResource(R.drawable.tictactoe)
        imageButton3.setImageResource(R.drawable.tictactoe)
        imageButton4.setImageResource(R.drawable.tictactoe)
        imageButton5.setImageResource(R.drawable.tictactoe)
        imageButton6.setImageResource(R.drawable.tictactoe)
        imageButton7.setImageResource(R.drawable.tictactoe)
        imageButton8.setImageResource(R.drawable.tictactoe)
        imageButton9.setImageResource(R.drawable.tictactoe)
        imageButton1.isEnabled=true
        imageButton2.isEnabled=true
        imageButton3.isEnabled=true
        imageButton4.isEnabled=true
        imageButton5.isEnabled=true
        imageButton6.isEnabled=true
        imageButton7.isEnabled=true
        imageButton8.isEnabled=true
        imageButton9.isEnabled=true
    }
    fun checkDrawState()
    {
       if(disabledImages.size==9 && winner!=WINNER.FIRSTPLAYER1 && winner!=WINNER.SECONDPLAYER2)
       {
           createAlert(
               "Draw!!","The Game is drawn",AlertDialog.BUTTON_POSITIVE,"OK",
               false
           )
       }
    }

}