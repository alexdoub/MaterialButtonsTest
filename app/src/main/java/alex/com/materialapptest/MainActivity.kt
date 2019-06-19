package alex.com.materialapptest

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()


        val everySecond = Observable.interval(2, TimeUnit.SECONDS)
//        val andThenOne = everySecond.delay(500, TimeUnit.MILLISECONDS)
//        Observable.merge(everySecond, andThenOne)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                toggleButtons()
            }
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
}
