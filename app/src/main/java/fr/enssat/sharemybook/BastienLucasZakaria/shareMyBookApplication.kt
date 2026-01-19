package fr.enssat.sharemybook.BastienLucasZakaria

import android.app.Application
import fr.enssat.sharemybook.BastienLucasZakaria.data.AppContainer
import fr.enssat.sharemybook.BastienLucasZakaria.data.AppDataContainer

class shareMyBookApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    // Il faudra mettre un override devant la fonction
    fun OnCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}