package com.georgcantor.newsproject.util

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.georgcantor.newsproject.R

fun AppCompatActivity.openFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.frameContainer, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val context = this as AppCompatActivity
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun Context.getScreenSize(): Int {
    return when (this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
        Configuration.SCREENLAYOUT_SIZE_XLARGE -> 4
        Configuration.SCREENLAYOUT_SIZE_UNDEFINED -> 3
        Configuration.SCREENLAYOUT_SIZE_LARGE -> 3
        Configuration.SCREENLAYOUT_SIZE_NORMAL -> 2
        Configuration.SCREENLAYOUT_SIZE_SMALL -> 2
        else -> 2
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

//fun LottieAnimationView.showAnimation() {
//    this.visibility = View.VISIBLE
//    this.playAnimation()
//    this.loop(true)
//}
//
//fun LottieAnimationView.showSingleAnimation(speed: Float) {
//    val animation = this
//    this.visibility = View.VISIBLE
//    this.playAnimation()
//    this.repeatCount = 0
//    this.speed = speed
//    this.addAnimatorListener(object : Animator.AnimatorListener {
//        override fun onAnimationRepeat(p0: Animator?) {
//        }
//
//        override fun onAnimationEnd(p0: Animator?) {
//            animation.gone()
//        }
//
//        override fun onAnimationCancel(p0: Animator?) {
//        }
//
//        override fun onAnimationStart(p0: Animator?) {
//        }
//    })
//}
//
//fun LottieAnimationView.hideAnimation() {
//    this.loop(false)
//    this.gone()
//}

fun Context.shortToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.longToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    connectivityManager?.let {
        val networkInfo = it.activeNetworkInfo
        networkInfo?.let { info ->
            if (info.isConnected) return true
        }
    }

    return false
}

fun Context.showDialog(
    message: CharSequence,
    function: () -> (Unit)
) {
    val builder = AlertDialog.Builder(this)
        .setMessage(message)
        .setNegativeButton("no") { _, _ ->
        }
        .setPositiveButton("yes") { _, _ ->
            function()
        }

    val dialog: AlertDialog = builder.create()
    dialog.show()
}

fun Context.loadImage(
    url: String,
    view: ImageView
) {

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .thumbnail(0.1F)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean = false

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean = false
        })
        .into(view)
}