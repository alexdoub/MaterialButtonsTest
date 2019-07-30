package alex.com.materialapptest

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.button.MaterialButton
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigInteger
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

//        val item = menu!!.findItem(R.id.switchForActionBar) as SwitchCompat
//        item.setOnCheckedChangeListener { buttonView, isChecked ->
//            println("Checked: ${isChecked}")
//        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        toggleButtons()
        return super.onOptionsItemSelected(item)
    }

    fun toggleButtons() {
        val root = findViewById<LinearLayout>(R.id.da_root)
        for (i in 0..root.childCount) {
            val child = root.getChildAt(i)
            if (child is Button) {
                child.isEnabled = !child.isEnabled
            } else if (child is MaterialButton) {
                child.isEnabled = !child.isEnabled
            }
        }
    }

    fun hi(view: View) {
        println("hi")
    }

    fun hi2(view: View) {
        println("hi")
    }

    //RESULTS: If you're already on the main thread your block will synchronously run the subscribe block. Else it is scheduled by the handler
    fun rxExampleTest() {
        //every second print, do work then print
        //see if main thread runs immediately (yes)
        //see if secondary thread is blocked at all (no?)

        val daScheduler = AndroidSchedulers.mainThread()

        println("@@ START!!!!!!!!!!!!!!!!!!!")
        Observable.interval(4, TimeUnit.SECONDS)
            .observeOn(daScheduler)
                .subscribe {
                println("@@ kick off")
                val before = System.currentTimeMillis()
                Completable.create {
                    work()
                    println("@@ Work Done: " + (System.currentTimeMillis() - before) + ". Current Thread:" + Thread.currentThread())
                    it.onComplete()
                }
//                    .subscribeOn(daScheduler)
                    .subscribe()

                println("@@ Delay    : " + (System.currentTimeMillis() - before) + ". Current Thread:" + Thread.currentThread())
            }
    }

    fun work() {
        var bigint = BigInteger("2")
        while (bigint.toString().length < 2000) {
            bigint = bigint.times(BigInteger("2"))
        }
    }


}
