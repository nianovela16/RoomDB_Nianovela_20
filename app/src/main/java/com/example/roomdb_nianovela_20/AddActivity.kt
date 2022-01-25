package com.example.roomdb_nianovela_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roomdb_nianovela_20.room.Movie
import com.example.roomdb_nianovela_20.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDb(this) }
    private var movieId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupview()
        setupListener()
    }

    private fun setupListener() {
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovie(
                    Movie(
                        0, et_title.text.toString(),
                        et_description.text.toString()
                    )
                )

                finish()
            }
        }
        btn_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().updateMovie(
                    Movie(
                        0, et_title.text.toString(),
                        et_description.text.toString()
                    )
                )
                finish()
            }
        }
    }

    private fun setupview() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                supportActionBar!!.title = "BUAT BARU"
                btn_save.visibility = View.VISIBLE
                btn_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                supportActionBar!!.title = "BACA"
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
            }
            Constant.TYPE_UPDATE -> {
                supportActionBar!!.title = "EDIT"
                btn_save.visibility = View.GONE
                btn_update.visibility = View.VISIBLE
            }
        }
    }
}










