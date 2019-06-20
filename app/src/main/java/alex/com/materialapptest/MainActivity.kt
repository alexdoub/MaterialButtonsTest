package alex.com.materialapptest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
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

    fun derp(view: View) {
        println("derp")
    }
}
