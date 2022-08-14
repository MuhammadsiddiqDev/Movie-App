package uz.isystem.tmdbapp.ui.main.home

abstract class BaseData {

    companion object {
        val TYPE_SLIDER: Int
            get() = 1

        val TYPE_NOW_PLAYING: Int
            get() = 2

        val TYPE_POPULAR: Int
            get() = 3

        val TYPE_TOP_RATED: Int
            get() = 4

        val TYPE_UPCOMING: Int
            get() = 5

    }

    abstract fun getType(): Int

}