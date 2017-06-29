package tech.redroma.yoching.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.plattysoft.leonids.ParticleSystem
import tech.redroma.yoching.R
import tech.redroma.yoching.R.drawable
import tech.redroma.yoching.exoBlack
import tech.redroma.yoching.extensions.findView
import tyrantgit.explosionfield.ExplosionField

class TestActivity : AppCompatActivity()
{

    lateinit var first: TextView
    lateinit var second: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        first = findView(R.id.textView)
        second = findView(R.id.textView2)

        listOf(first, second).onEach { it.typeface = exoBlack() }

        first.postDelayed({ explode() }, 100)

    }

    fun explode()
    {
        val explode = Runnable {
            //            val explosion = ExplosionField.attach2Window(this)
//            explosion.explode(second)
            ParticleSystem(this, 200, drawable.particle_black, 900)
                    .setSpeedModuleAndAngleRange(0.01f, 0.3f, 180, 360)
                    .setScaleRange(0.2f, 0.4f)
                    .setFadeOut(700)
                    .oneShot(second, 75)
        }

        YoYo.with(Techniques.BounceInDown)
                .duration(400)
                .playOn(first)

        first.postDelayed(explode, 100)
    }
}
